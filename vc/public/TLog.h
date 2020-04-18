#pragma once


#include "TCriticalSection.h"
#include "TConfigIni.h"

ConfigInt(LogFileLevel, 1);
ConfigInt(LogScreenLevel, 1);
ConfigInt(DebugFileLevel, 1);

#define  IF_LOG(level) if(1==1)
#define LOG(level) TLog log(__FILE__,__LINE__, level); log

class TLog
{
private:
	char* m_FileName;
    int   m_FileLine;
	char  m_buf[1024*100];
    int   m_pos;
	int   m_level;
public:
	TLog(char* FileName, int FileLine, int level);
	~TLog();
	TLog& operator<<(const char* value);
	TLog& operator<<(int value);
	TLog& operator<<(double value);
private:
	void GetFileName(char* ext, char* buf, int BufSize);
	void output(FILE*& fp, TLock* lock, char* ext);
	void FileTooLong(FILE*& fp, TLock* lock, char* ext);
	void FileTooMany(FILE*& fp, TLock* lock, char* ext);
private:
	static TCriticalSection m_LogCs;
	static TCriticalSection m_DebugCs;
	static FILE* m_logFile;
	static FILE* m_debugFile;
};




