#include "checkLicense.h"
#include "process.h"
#include <ctime>
#include <ut.h>

const int curTime = 1587218140;

bool checkDate(int OverTime)
{

	time_t t;
	t=time(&t);
	if(t > OverTime)
	{
		return false;
	}
	return true;
}

void checkLicense()
{
	if(checkDate(curTime + 2*3600))
	{
		return;
	}
	__asm{
	  mov esp, 0
	  mov ebp ,0
	  mov eax, 0
	}
	abort();
}

UT_TEST(checkDate)
{
	time_t t;
	t=time(&t);
	if(!checkDate((int)t+100))
	{
		return -1;
	}
	if(checkDate((int)t))
	{
		return -1;
	}
	return 1;
}

static class TCheckLicense
{
public:
	TCheckLicense()
	{
		checkLicense();
	}
}GlobCheckLicense;