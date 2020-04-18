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
	TConfigIni(char* name, T defaultYalue)
	{
		m_name = name;
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
	inline const T& get(){return m_value;};
private:
	char* m_name;
private:
	T  m_value;
};

template<>
class TConfigIni<std::string>
{
public:
	TConfigIni(char* name, const char* defaultValue)
	{
		m_name = name;
		m_value = defaultValue;
		//从配置文件中读取值
		ReadIni(name, &m_value);
	}
public:
	~TConfigIni(void)
	{
		WriteIni(m_name, &m_value);
	}
public:
	inline const char* get()
	{
		return m_value.c_str();
	};
private:
	char* m_name;
private:
	std::string  m_value;
};


#define ConfigInt(name, value)  class name{public:static inline int get(){static TConfigIni<int> ini(#name, value); return ini.get();}};
#define ConfigDouble(name, value)  class name{public:static inline double get(){static TConfigIni<double> ini(#name, value); return ini.get();}};
#define ConfigString(name, value)  class name{public:static inline const char* get(){static TConfigIni<std::string> ini(#name, value); return ini.get();}};

