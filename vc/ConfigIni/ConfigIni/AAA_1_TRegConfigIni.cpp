#include "TRegConfigIni.h"
#include <windows.h>

std::map<std::string, TReg*>   TRegConfigIni::m_map;

TRegConfigIni::TRegConfigIni(__int64* value, const char* name, const char* FileName, int FileLine)
{
	TInt64Reg* reg = new TInt64Reg();
	reg->m_ptr = (void*)value;
	reg->m_name = name;
	m_map[name] = reg;
}

TRegConfigIni::~TRegConfigIni(void)
{
}

void TInt64Reg::loadFromFile(const char* appName, const char* iniFileName)
{
	char buf[1024*100]={0};
	if(GetPrivateProfileString(appName, m_name.c_str(), "", buf, sizeof(buf)-10,  iniFileName) > 0)
	{
		__int64 v = _atoi64(buf);
		*((__int64*)m_ptr) = v;
	}
	
}
void TInt64Reg::save2File(const char* appName, const char* iniFileName)
{
	char buf[1024*100];
	_i64toa((*(__int64*)m_ptr), buf, sizeof(buf));
	WritePrivateProfileString(appName, m_name.c_str(), buf, iniFileName);
}

TRegConfigIniLoadFromFile::TRegConfigIniLoadFromFile()
{
	TRegConfigIni::loadFromFile();
}
TRegConfigIniLoadFromFile::~TRegConfigIniLoadFromFile()
{
	TRegConfigIni::save2File();
}

void TRegConfigIni::save2File()
{
	char buf[1024*100];
	//找到exe的path
	GetModuleFileName((HMODULE)0, buf, sizeof(buf));
	//找到ini文件名
	char* pos = strrchr(buf, '.');
	if(0 == pos)
	{
		strcat(buf, ".ini");
	}
	else
	{
		sprintf(pos, ".ini");
	}
	//从文件中读取
	for(std::map<std::string, TReg*>::iterator it = m_map.begin();
		m_map.end() != it; ++it)
	{
		it->second->save2File("config", buf);
	}
}


void TRegConfigIni::loadFromFile()
{
	char buf[1024*100];
	//找到exe的path
	GetModuleFileName((HMODULE)0, buf, sizeof(buf));
	//找到ini文件名
	char* pos = strrchr(buf, '.');
	if(0 == pos)
	{
		strcat(buf, ".ini");
	}
	else
	{
		sprintf(pos, ".ini");
	}
	//从文件中读取
	for(std::map<std::string, TReg*>::iterator it = m_map.begin();
		m_map.end() != it; ++it)
	{
		it->second->loadFromFile("config", buf);
	}
}
