#pragma once
#include <map>
#include <string>

class TReg
{
	
public:
	std::string m_name;
	void* m_ptr;
	virtual void loadFromFile(const char* appName, const char* iniFileName) = 0;
	virtual void save2File(const char* appName, const char* iniFileName) = 0;
};

class TInt64Reg : public TReg
{
public:
	virtual void loadFromFile(const char* appName, const char* iniFileName);
	virtual void save2File(const char* appName, const char* iniFileName);
};
	
class TRegConfigIni
{
private:
	static std::map<std::string, TReg*>  m_map;
public:
	TRegConfigIni(__int64* addr, const char* name, const char* FileName, int FileLine);
	~TRegConfigIni(void);
	static void loadFromFile();
	static void save2File();
};

class TRegConfigIniLoadFromFile
{
public:
	TRegConfigIniLoadFromFile();
	~TRegConfigIniLoadFromFile();
};