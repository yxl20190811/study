#pragma once
#include <string>

void ReadIni(const char* name, int* address);
void WriteIni(const char* name, int* address);

void ReadIni(const char* name, double* address);
void WriteIni(const char* name, double* address);

void ReadIni(const char* name, std::string* address);
void WriteIni(const char* name, std::string* address);

template<class T>
class TConfigIni
{
public:
	TConfigIni(char* name, T defaultYalue, char* FileName, int FileLine)
	{
		m_name = name;
		m_FileName = FileName;
		m_FileLine = FileLine;
		m_value = defaultYalue;
		//从配置文件中读取值
		ReadIni(name, &m_value);
	}
public:
	~TConfigIni(void)
	{
		WriteIni(m_name, &m_value);
	}
public:
	inline T get(){return m_value;};
private:
	char* m_name;
	char* m_FileName;
	int   m_FileLine;
private:
	T  m_value;
};

#define ConfigIni(type, name, value)  inline type name(){static TConfigIni<type> ini(#name, value, __FILE__,__LINE__); return ini.get();};
