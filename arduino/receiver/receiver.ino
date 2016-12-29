#include <SPI.h>
#include <nRF24L01.h>
#include <RF24_config.h>
#include <RF24.h>


RF24 radio(9, 10);
const byte pipe[6] = "00001";

typedef struct {
    int id;
    int action;
    int value;
}
ChannelData;

ChannelData data;

#define ON_PIN 6
#define OK_PIN 7

void setup() {
    while(!Serial);
    Serial.begin(9600);
    radio.begin();

    pinMode(ON_PIN, OUTPUT);
    pinMode(OK_PIN, OUTPUT);
    
    radio.openReadingPipe(1, pipe);
    radio.setPALevel(RF24_PA_MAX);

    radio.startListening();
} 

void loop() {
    digitalWrite(ON_PIN, HIGH);
    if(radio.available()) {
        digitalWrite(OK_PIN, HIGH);
        radio.read(&data, sizeof(data));
        // {
        //      "id": 0,
        //      "action": 0|1,
        //      "value": 1
        // }

        Serial.print("{");
        Serial.print("\"id\": ");
        Serial.print(data.id);
        Serial.print(", \"action\": ");
        Serial.print(data.action);
        Serial.print(", \"value\": ");
        Serial.print(data.value);
        Serial.println("}");
        
        digitalWrite(OK_PIN, LOW);
    }
}
