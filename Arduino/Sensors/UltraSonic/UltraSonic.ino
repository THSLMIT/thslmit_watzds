const int pingPin = 7;
int calibrationTime = 30;   

int approachingsInARow = 0;
int notApproachingsInARow = 0;

void setup(){

  Serial.begin(9600);
  pinMode(pingPin,OUTPUT);

}



void loop(){

 

  if(approachingSensor()){
    
    approachingsInARow++;
    if(approachingsInARow >= 2){
        Serial.println("Approaching docking station");
        notApproachingsInARow = 0;
    }else{
        Serial.println("Possibly approaching docking station"); 
    }

  }else{
    notApproachingsInARow++;

      if(notApproachingsInARow >=2){
         Serial.println("Not appraoching docking station");
         approachingsInARow = 0;
      }else{
         Serial.println("Reading ... ");
      }
      
      
      

  }

  delay(1000);

}  





long measure(){

  long duration, cm;

  pinMode(pingPin, OUTPUT);

  digitalWrite(pingPin, LOW);

  delayMicroseconds(2);

  digitalWrite(pingPin, HIGH);

  delayMicroseconds(5);

  digitalWrite(pingPin, LOW);

  

  pinMode(pingPin, INPUT);

  duration = pulseIn(pingPin, HIGH);

  

  cm = microsecondsToCentimeters(duration);

  

  Serial.print(cm);

  Serial.print("cm");

  Serial.println();

  return cm; 

    

  //delay(100);

}



long microsecondsToCentimeters(long microseconds){

  return microseconds/29/2;

}

boolean approachingSensor(){

  boolean vtest = false;

  long p1;

  long p2;

  Serial.print("d1 = ");

  p1 = measure();

  delay(2000);

  Serial.print("d2 = ");

  p2 = measure();

  long distance = p2-p1;

  if(distance < 0){

    vtest = true;

  } 
  
 

  return vtest;

}








