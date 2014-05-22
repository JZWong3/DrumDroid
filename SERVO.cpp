#include <string.h>
#include <fstream>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "PWM.h"
#include "SERVO.h"
using namespace std;
#define MAX 64

int SERVO::setDegrees(int degrees)
{
	PWM something;
	char value[7];
	//int newVal;
	//newVal = atoi(degrees);
	
	//degrees = (int)degrees/10;  //needed for potentiometer
	cout << "New Val degrees is: " << degrees << endl;
	//700,000 = 0 degrees
	degrees = (degrees * 8055) + 700000;
	cout << "The new degrees is: " << degrees << endl;
	sprintf(value, "%d", degrees);
	cout << "Value: " << value << endl;
	something.setDuty(46, value, 12);
	return 0;
}

int SERVO::setDegreesPot(int degrees, int PWMPin, int PWMSecond)
{
	PWM something;
	char value[7];
	//int newVal;
	//newVal = atoi(degrees);
	
	//degrees = (int)degrees;  //needed for potentiometer
	cout << "New Val degrees is: " << degrees << endl;
	//700,000 = 0 degrees
	degrees = (degrees * 8055) + 700000;
	cout << "The new degrees is: " << degrees << endl;
	sprintf(value, "%d", degrees);
	cout << "Value: " << value << endl;
	something.setDuty(PWMPin, value, PWMSecond);
	return 0;
}

int SERVO::setDegreesPotP9(int degrees, int PWMPin, int PWMSecond)
{
	PWM something;
	char value[7];
	//int newVal;
	//newVal = atoi(degrees);
	
	//degrees = (int)degrees;  //needed for potentiometer
	cout << "New Val degrees is: " << degrees << endl;
	//700,000 = 0 degrees
	degrees = (degrees * 8055) + 700000;
	cout << "The new degrees is: " << degrees << endl;
	sprintf(value, "%d", degrees);
	cout << "Value: " << value << endl;
	something.setDutyP9(PWMPin, value, PWMSecond);
	return 0;
}
