#include <iostream>
using namespace std;

class PWM {
public:
	int setDuty(unsigned int, char*, unsigned int);
	int setDutyP9(unsigned int, char*, unsigned int);
	char* getDuty(unsigned int, unsigned int);
	int setPeriod(unsigned int, char*, unsigned int);
	char* getPeriod(unsigned int);
	int getAnal(unsigned int);
	int setPolarity();
	int getPolarity();
private:
};
