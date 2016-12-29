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

#define EEPROM_ADDR 0
#define FINGER_STATE 1
#define BUTTON_STATE 2

#define CONFIDENCE_THRESHOLD 10

#define BUTTON_WAIT_DELAY 10000 // Milliseconds
#define REGISTER_BUTTON 4 // Digital PIN
#define RESET_BUTTON 5 // Digital PIN

#define MAX_FINGERPRINTS 255

#define ID 5

#define RING_ACTION 0
#define REGISTER_ACTION 1

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

int lastAddedFinger;
int lastError;

SoftwareSerial mySerial(2, 3);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

void setup() {
    currentState = FINGER_STATE;
    lastTick = -1;
    
    pinMode(REGISTER_BUTTON, INPUT);
    pinMode(RESET_BUTTON, INPUT);
    
    Serial.begin(9600);
    finger.begin(57600);
    Serial.print("EEPROM: ");
    Serial.println(EEPROM.read(0));
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
    int resetButton = digitalRead(RESET_BUTTON);
    if(resetButton == HIGH) {
        // Reset the fingers and set the state to 'FINGER_STATE'
        resetFingers();
        currentState = FINGER_STATE;
        return;
    }
    
    switch(currentState) {
         case FINGER_STATE:
             fingerState();
             break;
         case BUTTON_STATE:
             buttonState();
             break;          
    }
}

/**
 * The finger state has a few tasks to perform.
 * 1) Wait for a finger to be present
 * 2a) If that's the case, get the ID of the finger
 * 2b) If it's not the case, set the state to 'BUTTON_STATE'
 * 3) Send the ID to the server, let the server handle the white- and blacklist
 */
void fingerState() {
    // Block until a finger is present
    while(!hasFinger());
    Serial.println("Got a finger");
    
    // If the finger is not in the system, set the state to 'BUTTON_STATE'
    if(!fingerInSystem()) {
        Serial.println("Finger is not in the system");
        currentState = BUTTON_STATE;
        lastTick = millis();
        sendId(-1);
        return;
    }
    
    // While checking if the finger is registered in the system,
    // the id and confidence are set inside the 'finger' object
    Serial.print("Sent ");
    Serial.print(finger.fingerID);
    Serial.println(" to the server");
    sendId(finger.fingerID);
    delay(2000);
}

/**
 * The button state will wait for 10 seconds for either button to be presed.
 * If the reset button is pressed, reset all the fingerprints
 * If the register button is pressed, register the fingerprint on the last id + 1
 * After waiting is over, the state will be 'FINGER_STATE'
 * TODO: Get the last ID
 */
void buttonState() {
    long curTick = millis();
    
    if(inButtonState(curTick)) {
        int registerButton = digitalRead(REGISTER_BUTTON);
        if(registerButton == HIGH) {
            Serial.println("Register button pressed");
            // TODO: Show a green LED
            // Register the finger and set the state to 'FINGER_STATE'
            if(registerFinger()) {
                Serial.print("Successfully registered #");
                Serial.println(lastAddedFinger);
                sendRegisterId(lastAddedFinger);
            } else {
                Serial.print("Error while registering finger, error=");
                Serial.println(lastError);
                 // Set state to ERROR_STATE?   
            }
            currentState = FINGER_STATE;
        }
    } else {
        // If the waiting is over, set the state back to 'FINGER_STATE'
        currentState = FINGER_STATE;
        Serial.println("Put back to FINGER_STATE");
    }
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
        return false;
    }
    
    code = finger.fingerFastSearch();
    // If the system fails, add some logging at this place
    if(code != FINGERPRINT_OK) {
        return false;
    }
    
    return finger.confidence > CONFIDENCE_THRESHOLD;
}

/**
 * Check if there's a finger on the sensor. 
 */
bool hasFinger() {
    code = finger.getImage();
    return code == FINGERPRINT_OK;
}

/**
 * Check if the program is allowed to be in the button state.
 */
bool inButtonState(int cur) {
    return cur - lastTick < BUTTON_WAIT_DELAY;
}

void resetFingers() {
//    EEPROM.write(EEPROM_ADDR, MAX_FINGERPRINTS);
//    
//    for (int i = 0; i < MAX_FINGERPRINTS; i ++) {
//        Serial.print("Deleting #");
//        Serial.println(i);
//        finger.deleteModel(i);   
//    }
}

bool registerFinger() {
    Serial.println("Trying to register finger");
    // Block until a finger is present
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
    return lastError == FINGERPRINT_OK;
}

byte getNextID() {
    byte val = EEPROM.read(EEPROM_ADDR);
    if(val >= MAX_FINGERPRINTS) {
        val = -1;
    }
    
    return val + 1;
}

void sendId(int id) {
    data.id = ID;
    data.action = RING_ACTION;
    data.value = id;
    Serial.print("Sending ID: ");
    Serial.println(id);
}

void sendRegisterId(int id) {
    data.id = ID;
    data.action = REGISTER_ACTION;
    data.value = id;
}
