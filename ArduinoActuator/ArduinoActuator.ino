// Pin
// 2 -> DHT11
// 
// 4 -> Motion
// 
// 
//
// 8 -> Light
// 9 -> Projector pwm


// Control
// Pin 12 -> Aircon
// Pin 8 -> Light
// Pin 9 -> Projector ( Servo ) PWM

// Sensor Collect
// Pin 2 -> DHT11
// Pin 4 -> Motion 


#include <Servo.h>
#include <dht11.h>

String serialString;
Servo projServo;

// DHT11
dht11 DHT;
#define DHT11_PIN 2

// motion sensor
int MotionPin = 4; // choose the input pin (for PIR sensor)
int pirState = LOW; // we start, assuming no motion detected
int val = 0; // variable for reading the pin status


// control
int lightPin = 8;
int airconPin = 12;
int projPin = 9;

// state
#define PROJON 1
#define PROJOFF 0
int projState;

int controlList[3] = {lightPin, airconPin, projPin};


// function List
void OnLight();
void OffLight();
void OnAircon();
void OffAircon();
void OnProjector();
void OffProjector();



void setup(){
  Serial.begin(9600);
  projServo.attach(projPin);
  projServo.write(0);
  projState = PROJOFF;
  serialString = "";

  pinMode(MotionPin, INPUT); // declare sensor as input

  pinMode(lightPin, OUTPUT);
  pinMode(airconPin, OUTPUT);
}

void loop(){

  serialString = "";
  

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

  val = digitalRead(MotionPin); // read input value
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


  int index = 0;

  while(Serial.available()){
    
      char ONOFF = Serial.read();
      
      switch(index){
        case 0:
          if(ONOFF == 'O')
          {
            OnLight();
            }
          else if(ONOFF == 'X')
          {
            OffLight();
            }
        break;

        case 1:
          if(ONOFF == 'O')
          {
            OnAircon();
            }
          else if(ONOFF == 'X')
          {
            OffAircon();
            }
        break;

        case 2:
           if(ONOFF == 'O')
          {
            OnProjector();
            }
          else if(ONOFF == 'X')
          {
            OffProjector();
            }
        break;

        }

        index++;

        if(index == 3){break;}
    
    }

  delay(1000);
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

  // Check change state
  if(projState == PROJON){
    return;
    }
  
  // Control Servomoter
  int pos;
  for(pos = 0; pos <= 180; pos += 1) // goes from 0 degrees to 180 degrees 
  {                                  // in steps of 1 degree 
    projServo.write(pos);              // tell servo to go to position in variable 'pos' 
    delay(15);                       // waits 15ms for the servo to reach the position 
  } 

  projState = PROJON;
}
void OffProjector(){

  // Check change state
  if(projState == PROJOFF){
    return;
    }
    
  int pos;
    for(pos = 180; pos>=0; pos-=1)     // goes from 180 degrees to 0 degrees 
  {                                
    projServo.write(pos);              // tell servo to go to position in variable 'pos' 
    delay(15);                       // waits 15ms for the servo to reach the position 
  } 

  // Control Servomoter

  projState = PROJOFF;
}

