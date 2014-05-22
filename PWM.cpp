#include <string.h>
#include <fstream>
#include <iostream>
#include "PWM.h"
using namespace std;
#define MAX 64

int PWM::setDuty(unsigned int PWMPin, char* duty, unsigned int PWMSecond)
{
	//Access the PWM
	FILE* FileHandle = NULL;
		char setValue[7], PWMValue[MAX];
		sprintf(PWMValue, "/sys/devices/ocp.2/pwm_test_P8_%d.%d/duty", PWMPin, PWMSecond);
		if ((FileHandle = fopen(PWMValue, "rb+")) == NULL)
    	{
        	printf("Cannot open duty handle. \n");
        	return 1;
    	}
    	strcpy(setValue, duty);
		fwrite(&setValue, sizeof(char), 7, FileHandle);
		fclose(FileHandle);
		return 0;
}

int PWM::setDutyP9(unsigned int PWMPin, char* duty, unsigned int PWMSecond)
{
	//Access the PWM
	FILE* FileHandle = NULL;
		char setValue[7], PWMValue[MAX];
		sprintf(PWMValue, "/sys/devices/ocp.2/pwm_test_P9_%d.%d/duty", PWMPin, PWMSecond);
		if ((FileHandle = fopen(PWMValue, "rb+")) == NULL)
    	{
        	printf("Cannot open duty handle P9. \n");
        	return 1;
    	}
    	strcpy(setValue, duty);
		fwrite(&setValue, sizeof(char), 7, FileHandle);
		fclose(FileHandle);
		return 0;
}

char* PWM::getDuty(unsigned int PWMPin, unsigned int PWMSecond)
{
	FILE* FileHandle = NULL;
		char setValue[7], PWMValue[MAX];
		sprintf(PWMValue, "/sys/devices/ocp.2/pwm_test_P8_%d.%d/duty", PWMPin, PWMSecond);
		if ((FileHandle = fopen(PWMValue, "r")) == NULL)
    	{
        	printf("Cannot open duty handle for reading. \n");
    	}
		fread(&setValue, sizeof(char), 7, FileHandle);
		fclose(FileHandle);
		return setValue;
}
int PWM::setPeriod(unsigned int PWMPin, char* period, unsigned int PWMSecond)
{
	FILE* FileHandle = NULL;
		char setValue[8], PWMValue[MAX];
		sprintf(PWMValue, "/sys/devices/ocp.2/pwm_test_P8_%d.%d/period", PWMPin, PWMSecond);
		if ((FileHandle = fopen(PWMValue, "rb+")) == NULL)
    	{
        	printf("Cannot open period handle. \n");
        	return 1;
    	}
    	strcpy(setValue, period);
		fwrite(&setValue, sizeof(char), 8, FileHandle);
		fclose(FileHandle);
		return 0;
}
char* PWM::getPeriod(unsigned int PWMPin)
{
	FILE* FileHandle = NULL;
		char setValue[7], PWMValue[MAX];
		sprintf(PWMValue, "/sys/devices/ocp.2/pwm_test_P8_%d.12/period", PWMPin);
		if ((FileHandle = fopen(PWMValue, "r")) == NULL)
    	{
        	printf("Cannot open period handle for reading. \n");
    	}
		fread(&setValue, sizeof(char), 7, FileHandle);
		return setValue;
		fclose(FileHandle);
}
int PWM::getAnal(unsigned int AnalPin)
{
	FILE* FileHandle = NULL;
		//unsigned int AnalPin = 1;
		int newVal;
		char setValue[4], AnalValue[MAX];
		sprintf(AnalValue, "/sys/devices/ocp.2/helper.11/AIN%d", AnalPin);
		if ((FileHandle = fopen(AnalValue, "r")) == NULL)
    	{
        	printf("Cannot open analog handle for reading. \n");
    	}
		fread(&setValue, sizeof(char), 4, FileHandle);
		newVal = atoi(setValue);
		cout << "The setValue is: " << newVal << endl;
		cout << "The AnalValue is: " << AnalValue << endl;
		fclose(FileHandle);
		return newVal;
		//fclose(FileHandle);

}
