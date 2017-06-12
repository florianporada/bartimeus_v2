/*
 * PIR sensor tester
 */
#include <SoftwareSerial.h>
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24_config.h>
#include <RF24.h>
#include <StopWatch.h>

// Change this with the ID: ModWillie/generate/
#define ID 9362 // FB126367

RF24 radio(9, 10);
const byte pipe[6] = "00001";

StopWatch MySW;
StopWatch SWarray[5];

typedef struct {
	  int id;
    int action;
    int value;
}
ChannelData;

ChannelData data;
 
int inputPin = 2;               // choose the input pin (for PIR sensor)
int pirState = LOW;             // we start, assuming no motion detected
int val = 0;                    // variable for reading the pin status
bool firstTime = true;          // variable detect motion first time without waiting waiting for 10 seconds of no motion


void setup() {
  pinMode(inputPin, INPUT);  // declare sensor as input
  data.id = ID;
  radio.begin();
  radio.setRetries(15, 15);
  radio.setPALevel(RF24_PA_MAX);
  radio.openWritingPipe(pipe);
  radio.stopListening();
  
  Serial.begin(9600);
  SWarray[0].start();
}
 
void loop(){
  MySW.start();
  val = digitalRead(inputPin);  // read input value
  if (val == HIGH) {            // check if the input is HIGH
    if (pirState == LOW) {      
      Serial.println("Motion detected!");
      if (firstTime || (MySW.elapsed()/1000) > 10) {
        sendData();
        firstTime = false;
      } 
      MySW.reset();
      pirState = HIGH;
    }
  } else {
    if (pirState == HIGH){
      // We only want to print on the output change, not state
      pirState = LOW;
    }
  }  
}

void sendData() {
    data.action = 2;
    data.value = 2;
    Serial.print("Sending ID: ");
    Serial.println(data.value);
    radio.write(&data, sizeof(data));    
}
