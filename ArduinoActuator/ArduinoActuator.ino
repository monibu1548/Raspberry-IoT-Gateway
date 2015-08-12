// Pin
// A0
// A1
// A2
// A3
// A4
// A5


// Control
// Pin 12 -> Aircon
// Pin 8 -> Light





#include <dht11.h>

String serialString;

// DHT11
dht11 DHT;
#define DHT11_PIN 2

// motion sensor
int inputPin = 4; // choose the input pin (for PIR sensor)
int pirState = LOW; // we start, assuming no motion detected
int val = 0; // variable for reading the pin status


// control
int lightPin = 8;
int airconPin = 12;

void setup(){
  Serial.begin(9600);

  serialString = "";

  pinMode(inputPin, INPUT); // declare sensor as input

  pinMode(lightPin, OUTPUT);
  pinMode(airconPin, OUTPUT);
}

void loop(){

  serialString = "";
  digitalWrite(lightPin, HIGH);
  

  int chk;
  chk = DHT.read(DHT11_PIN);    // READ DATA


  switch (chk){
  case DHTLIB_OK:  
    //
    break;
  case DHTLIB_ERROR_CHECKSUM: 
    Serial.print("Checksum error,t"); 
    break;
  case DHTLIB_ERROR_TIMEOUT: 
    Serial.print("Time out error,t"); 
    break;
  default: 
    Serial.print("Unknown error,t"); 
    break;
  }
  // DISPLAT DATA
  //Serial.print(DHT.humidity,1);
  //Serial.print(" ");
  //Serial.print(DHT.temperature,1);

  serialString += DHT.humidity;
  serialString += " ";
  serialString += DHT.temperature;
  serialString += " ";


  // motion sensor

  val = digitalRead(inputPin); // read input value
  if (val == HIGH)
  {
    //Serial.print("O");
    serialString += "O";
  }
  else
  {
    //Serial.print("X");
    serialString += "X";
  }

  //finish

  Serial.println(serialString);
  delay(2000);
}

void OnLight(){
  // Pin 8 -> Right
  digitalWrite(lightPin, HIGH);
}
void OffLight(){
  // Pin 8 -> Right
  digitalWrite(lightPin, LOW);
}
void OnAircon(){
  // Pin 12 -> Aircon
  digitalWrite(airconPin, HIGH);
}
void OffAircon(){
  // Pin 12 -> Aircon
  digitalWrite(airconPin, LOW);
}
void OnProjector(){
  // Control Servomoter
}
void OffProjector(){
  // Control Servomoter
}
