#include "TConfigIni.h"
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <ut.h>
#pragma warning(disable:4996)

char iniFileName[1024*100]={0};
char appName[1024*100] = {0};

static class FindiniFileName
{
public:
	FindiniFileName()
	{
		GetModuleFileName((HMODULE)0, iniFileName, sizeof(iniFileName));
		char* pos1 = strrchr(iniFileName, '\\');
		char* pos2 = strrchr(pos1, '.');
		if(0 != pos2)
		{
			*pos2 = 0;
		}
		strcpy(appName, pos1 + 1);
		sprintf(pos2, ".ini");
		//找到可执行文件名
	}
}InitFindiniFileName;

void ReadIni(const char* name, int* address)
{
	char buf[1024*100]={0};
	if(GetPrivateProfileString(appName,name, "", buf, sizeof(buf)-10,  iniFileName) <= 0)
	{
		return;
	}
	*address  = atoi(buf);
}

void WriteIni(const char* name, int* address)
{
	char buf[1024*100];
	sprintf(buf, "%d", (*address));
	WritePrivateProfileString(appName, name, buf, iniFileName);
}

void ReadIni(const char* name, double* address)
{
	char buf[1024*100]={0};
	if(GetPrivateProfileString(appName,name, "", buf, sizeof(buf)-10,  iniFileName) <= 0)
	{
		return;
	}
	*address = atof(buf);
}

void WriteIni(const char* name, double* address)
{
	char buf[1024*100];
	sprintf(buf, "%f", (*address));
	WritePrivateProfileString(appName, name, buf, iniFileName);
}

void ReadIni(const char* name,std::string* address)
{
	char buf[1024*100]={0};
	if(GetPrivateProfileString(appName,name, "", buf, sizeof(buf)-10,  iniFileName) <= 0)
	{
		return;
	}
	*address = buf;
}

void WriteIni(const char* name, std::string* address)
{
	char buf[1024*100];
	sprintf(buf, "%s", (*address).c_str());
	WritePrivateProfileString(appName, name, buf, iniFileName);
}

UT_TEST(IntIni)
{
	int value = 1;
	WriteIni("ut_Int", &value);
	int oldValue = value;
	value++;
	int newValue = value;

    ReadIni("ut_Int", &value);
	if(value != oldValue)
	{
		return -1;
	}
	value = newValue;
	WriteIni("ut_Int", &value);
	value = oldValue;
	ReadIni("ut_Int", &value);
	if(value != newValue)
	{
		return -1;
	}
	return 1;
}

UT_TEST(DoubleIni)
{
	double value = 1;
	WriteIni("ut_double", &value);
	double oldValue = value;
	value++;
	double newValue = value;

    ReadIni("ut_double", &value);
	if(value != oldValue)
	{
		return -1;
	}
	value = newValue;
	WriteIni("ut_double", &value);
	value = oldValue;
	ReadIni("ut_double", &value);
	if(value != newValue)
	{
		return -1;
	}
	return 1;
}


UT_TEST(StringIni)
{
	std::string value = "1234";
	WriteIni("ut_sring", &value);
	std::string oldValue = value;
	value = "4321";
	std::string newValue = value;

    ReadIni("ut_sring", &value);
	if(value != oldValue)
	{
		return -1;
	}
	value = newValue;
	WriteIni("ut_sring", &value);
	value = oldValue;
	ReadIni("ut_sring", &value);
	if(value != newValue)
	{
		return -1;
	}
	return 1;
}