//Leds del porcentaje
int led1 = 2;
int led2 = 3;
int led3 = 4;
int led4 = 5;
//Falta el 7 segmentos
int buzzer = 9;
//25, 50, 75, 100 porciento
String a = "a";
String b = "b";
String c = "c";
String d = "d";
//Reiniciar las luces
String reset = "reset";  
//El pedido esta listo
String R = "r";
//Nuevo pedido
String N = "n";
//Timers para que funcione el buzzer y no suene a cada rato
long previousMillis = 0;
long interval = 500;

void setup() {
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  Serial.begin(9600);
}
void loop() {
 
  if(Serial.available()>0){
    unsigned long currentMillis = millis();
    String cad = Serial.readString();
    if(a.compareTo(cad)==0){
      digitalWrite(led1, HIGH);
    }
    if(b.compareTo(cad)==0){
    digitalWrite(led2, HIGH);
    }
    if(c.compareTo(cad)==0){
     digitalWrite(led3, HIGH);
    }
    if(d.compareTo(cad)==0){
      digitalWrite(led4, HIGH);
    }
    if(reset.compareTo(cad)==0){
      digitalWrite(led1, LOW);
      digitalWrite(led2, LOW);
      digitalWrite(led3, LOW);
      digitalWrite(led4, LOW);
    }
    if(N.compareTo(cad)==0){
      tone(buzzer, 2000);
      delay(1000);
      noTone(buzzer);
      previousMillis = currentMillis;
    }
    if(R.compareTo(cad)==0){
      tone(buzzer, 2500);
      delay(500);
      tone(buzzer, 2000);
      delay(1000);
      noTone(buzzer);
      previousMillis = currentMillis;
    }
    else if(currentMillis - previousMillis > interval){ 
      noTone(buzzer);
      digitalWrite(led, LOW); 
    }
  }
}
