/**
 * http://glitter.jemoeders.website/bartimeus/Project/src/develop/docs/designs/fingerprint_sensor_flow.png
 */
#include <EEPROM.h>
#include <Adafruit_Fingerprint.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24_config.h>
#include <RF24.h>

#define CONFIDENCE_THRESHOLD 10
#define BUTTON_WAIT_DELAY 10000 // Milliseconds
#define MAX_FINGERPRINTS 255

#define EEPROM_ADDR 0

#define REGISTER_BUTTON 4 // Digital PIN
#define RESET_BUTTON    5 // Digital PIN

#define RING_ACTION 0
#define REGISTER_ACTION 1

#define OK_PIN          7
#define ERROR_PIN       6

#define EEPROM_ADDR 0
#define START_STATE     0
#define RESET_STATE     1
#define REGISTER_STATE  2
#define FINGER_STATE    3

#define FLASH_DELAY     200

// Change this with the ID: ModWillie/generate/
#define ID 25037 // E8E65B69

RF24 radio(9, 10);
const byte pipe[6] = "00001";

typedef struct {
    int id;
    int action;
    int value;
}
ChannelData;

ChannelData data;

int currentState;
int code;
long lastTick;
long curTick;

int lastAddedFinger;
int lastError;

SoftwareSerial mySerial(2, 3);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

void setup() {
    currentState = START_STATE;
    data.id = ID;
    radio.begin();
    radio.setRetries(15, 15);
    radio.setPALevel(RF24_PA_MAX);
    radio.openWritingPipe(pipe);
    radio.stopListening();
    
    pinMode(REGISTER_BUTTON, INPUT);
    pinMode(RESET_BUTTON, INPUT);

    pinMode(OK_PIN, OUTPUT);
    pinMode(ERROR_PIN, OUTPUT);

    digitalWrite(OK_PIN, LOW);
    digitalWrite(ERROR_PIN, LOW);

    Serial.begin(9600);
    finger.begin(57600);

    Serial.println("Validating fingerprint sensor");
    // Check if the fingerprint sensor is connected and valid
    if (finger.verifyPassword()) {
        Serial.println("Found fingerprint sensor!");
    } else {
        Serial.println("Did not find fingerprint sensor :(");
        while (1);
    }
}

void loop() {
    curTick = millis();
    lastError = -1;
    hideLed(ERROR_PIN);
    
    if(currentState == START_STATE) {
        showLed(OK_PIN);
        
        if(isButtonPressed()) {
            if(resetPressed()) {
                resetFingers();
            }
            
            if(registerPressed()) {
                showLed(ERROR_PIN);
                delay(100); 
            }
        }
    
        if(hasFinger()) {
            currentState = FINGER_STATE;
        }
    } else if(currentState == REGISTER_STATE) {
         registerState();
    } else if(currentState == FINGER_STATE) {
        fingerState();
    }
    delay(100);
}

void flashPin(int pin, int count) {
    for(int i = 0; i < count; i ++) {
        showLed(pin);
        delay(FLASH_DELAY);
        hideLed(pin);
        delay(FLASH_DELAY);
    }
    delay(FLASH_DELAY);
}

void registerState() {
    if(registerPressed()) {
        flashPin(OK_PIN, 2);
        
        Serial.println("Register button pressed!");
        if(registerFinger()) {
            Serial.print("Successfully registered #");
            Serial.println(lastAddedFinger);
            sendRegisterId(lastAddedFinger);

            flashPin(OK_PIN, 3);
            while(hasFinger());
            
            currentState = START_STATE;
        }
    } else {
        if(curTick - lastTick > BUTTON_WAIT_DELAY) {
            currentState = START_STATE;
        }
    }
}

/**
 * The finger state has a few tasks to perform.
 * 1) Check if the finger is in teh system
 * 2a) If that's the case, get the ID of the finger
 * 2b) If it's not the case, set the state to 'BUTTON_STATE'
 * 3) Send the ID to the server, let the server handle the white- and blacklist
 */
void fingerState() {
    hideLed(OK_PIN);
    
    // If the finger is not in the system, set the state to 'BUTTON_STATE'
    if(!fingerInSystem()) {
        Serial.println("Finger is not in the system");
        currentState = REGISTER_STATE;
        lastTick = millis();

        // Tell the server that we've an unkown person at the door
        sendId(-1);
        
        // Halt the program until a finger was removed
        while(hasFinger()) {}
        delay(500);
        return;
    }

    // While checking if the finger is registered in the system,
    // the id and confidence are set inside the 'finger' object
    Serial.print("Sent ");
    Serial.print(finger.fingerID);
    Serial.println(" to the server");
    sendId(finger.fingerID);

    flashPin(OK_PIN, 3);
    while(hasFinger());
    
    
    currentState = START_STATE;
}

bool registerFinger() {
    Serial.println("Trying to register finger");
    // Block until a finger is present

    showLed(OK_PIN);
    while(!hasFinger());
    
    Serial.println("Got finger");
    
    // User Experience > Best practise,
    // So we can't ask the user to lift the finger and press it again, so just take two 'screenshots' closely after each other
    code = finger.image2Tz(1);
    if (code != FINGERPRINT_OK) {
        lastError = code;
         return false;
    }
    delay(50);
    
    while(!hasFinger());
    
    Serial.println("Got finger");
    
    code = finger.image2Tz(2);
    if (code != FINGERPRINT_OK) {
        lastError = code;
         return false;
    }
    
    Serial.println("Converted");
    
    // Create a model of the finger, allowing it to be stored in the chip
    code = finger.createModel();
    // If the code doesn't equal 'FINGERPRINT_OK' the highest probability would be that the fingerprints did not match.
    if(code != FINGERPRINT_OK) {
        return false;
    }
    
    Serial.println("Created model");
    lastAddedFinger = getNextID();
    Serial.print("FINGER: ");
    Serial.println(lastAddedFinger);
    code = finger.storeModel(lastAddedFinger);
    EEPROM.write(EEPROM_ADDR, lastAddedFinger);
    lastError = code;
    hideLed(OK_PIN);
    return lastError == FINGERPRINT_OK;
}

void resetFingers() {
    hideLed(OK_PIN);

    int state = 0;
    
    EEPROM.write(EEPROM_ADDR, MAX_FINGERPRINTS);
    
    for (int i = 0; i < MAX_FINGERPRINTS; i ++) {
        finger.deleteModel(i);

        if(i % 5 == 0) {
            if(state == 0) {
                showLed(ERROR_PIN);
                state = 1;
            } else {
                hideLed(ERROR_PIN);
                state = 0;
            }
        }
    }

    hideLed(ERROR_PIN);
    showLed(OK_PIN);
}

byte getNextID() {
    byte val = EEPROM.read(EEPROM_ADDR);
    if(val >= MAX_FINGERPRINTS) {
        val = -1;
    }
    
    return val + 1;
}

/**
 * Check if the finger that is currently on the sensor is in the system. 
 * The system does not know if the user is in a whitelist or blacklist,
 * this is something the server should handle.
 */
bool fingerInSystem() {
    // Tell the fingerprint sensor to convert the fingerprint into a format that it can read
    code = finger.image2Tz();
    // If the system fails, add some logging at this place
    if(code != FINGERPRINT_OK) {
        lastError = code;
        return false;
    }
    
    code = finger.fingerFastSearch();
    // If the system fails, add some logging at this place
    if(code != FINGERPRINT_OK) {
        lastError = code;
        return false;
    }
    
    return finger.confidence > CONFIDENCE_THRESHOLD;
}

/**
 * Sets the output to HIGH to the given pin
 */
void showLed(int led) {
    digitalWrite(led, HIGH);
}

/**
 * Sets the output to LOW to the given pin
 */
void hideLed(int led) {
    digitalWrite(led, LOW);
}


/**
 * Check if there's a finger on the sensor. 
 */
bool hasFinger() {
    code = finger.getImage();
    return code == FINGERPRINT_OK;
}

/**
 * Check if either the register or reset button was pressed
 */
bool isButtonPressed() {
    return registerPressed() || resetPressed();
}

/**
 * Check if the register button is pressed
 */
bool registerPressed() {
    return digitalRead(REGISTER_BUTTON) == HIGH;
}

/**
 * Check if the reset button is pressed
 */
bool resetPressed() {
    return digitalRead(RESET_BUTTON) == HIGH;
}

void sendId(int id) {
    data.action = RING_ACTION;
    data.value = id;
    Serial.print("Sending ID: ");
    Serial.println(id);
    radio.write(&data, sizeof(data));
}

void sendRegisterId(int id) {
    data.action = REGISTER_ACTION;
    data.value = id;
    Serial.print("Sending Register ID: ");
    Serial.println(id);
    radio.write(&data, sizeof(data));
}

