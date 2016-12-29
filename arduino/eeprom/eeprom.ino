#include <EEPROM.h>


void setup() {
  Serial.begin(9600);
}

void loop() {
//  EEPROM.write(0, 0);
    byte value = EEPROM.read(0);
    Serial.print("Value: ");
    Serial.println(value);
    EEPROM.write(0, value + 1);

    delay(1000);
}
