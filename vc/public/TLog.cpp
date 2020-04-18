#include "TLog.h"
#include <windows.h>
#include "TGuard.h"
#pragma warning(disable:4996)


const int MaxFileSize = 10000;
const __int64 MaxDirSize = 3*MaxFileSize;


TCriticalSection TLog::m_LogCs;
TCriticalSection TLog::m_DebugCs;
FILE* TLog::m_logFile = NULL;
FILE* TLog::m_debugFile = NULL;




TLog::TLog(char* FileName, int FileLine, int level)
{
	m_FileName = FileName;
	m_FileLine = FileLine;
	m_level = level;
	m_pos = 0;
	m_pos += sprintf(m_buf + m_pos, "\r\n");
	if(NULL == m_logFile)
	{
		TGuard guard(&m_LogCs);
		if(NULL == m_logFile)
		{
			char buf[1024*100];
			GetFileName("log", buf, sizeof(buf));
			m_logFile = fopen(buf, "wb");
		}
	}
	if( NULL == m_debugFile)
	{

		TGuard guard(&m_LogCs);
		if( NULL == m_debugFile)
		{
			char buf[1024*100];
			GetFileName("debug", buf, sizeof(buf));
			m_debugFile = fopen(buf, "wb");
		}
	}
}

TLog::~TLog()
{
	
	if(0 != (LogScreenLevel() & m_level)) printf("%s", m_buf);

	

	SYSTEMTIME st;
	::GetLocalTime(&st);
	m_pos += sprintf(m_buf + m_pos, "    [%04d-%d-%02d %02d:%02d:%02d:%04d]", st.wYear, st.wMonth, st.wDay, st.wHour, st.wMinute, st.wSecond, st.wMilliseconds);

	if(0 != (LogFileLevel() & m_level))   output(m_logFile, &m_LogCs, "log");


	m_pos += sprintf(m_buf + m_pos, "[thread=%d]", ::GetCurrentThreadId());
	
	if(m_pos >= sizeof(m_buf) -100)
	{
		m_pos += sprintf(m_buf + m_pos, "...");
	}
	else
	{
		m_pos += sprintf(m_buf + m_pos, "[%s:%d]",  m_FileName,m_FileLine);
	}

	
	if(0 != (DebugFileLevel() & m_level))  output(m_debugFile, &m_DebugCs, "debug");
	//文件过长则产生新文件
	
}

void  TLog::GetFileName(char* ext, char* buf, int BufSize)
{
	//找到exe的path
	GetModuleFileName((HMODULE)0, buf, BufSize);
	//找到ini文件名
	char* pos = strrchr(buf, '.');
	if(0 == pos)
	{
		pos = buf + strlen(buf);
		
	}
	SYSTEMTIME st;
	::GetLocalTime(&st);
	sprintf(pos, ".%04d-%02d-%02d %02d_%02d_%02d.%s", st.wYear, st.wMonth, st.wDay, st.wHour, st.wMinute, st.wSecond, ext);
}

void TLog::FileTooLong(FILE*& fp, TLock* lock, char* ext)
{
	int flen=ftell(fp); 
	if(flen < MaxFileSize)
	{
		return;
	}
	TGuard guard(lock);
	char buf[1024*100];
	GetFileName(ext, buf, sizeof(buf));
	fclose(fp);
	fp = fopen(buf, "wb");

	FileTooMany(fp, lock, ext);
}

void TLog::FileTooMany(FILE*& fp, TLock* lock, char* ext)
{
	char buf[1024*100];
	GetModuleFileName((HMODULE)0, buf, sizeof(buf));
	char* pos = strrchr(buf, '.');
	if(NULL == pos)
	{
		pos = buf + strlen(buf);
	}
	sprintf(pos, ".*.%s", ext);

	WIN32_FIND_DATA data;
	HANDLE h = FindFirstFile(buf, &data);
	if(h)
	{
		char tmp[1024*100];
		tmp[0] = 0;
		__int64 count = 0;
		do
		{
			count += data.nFileSizeLow;
			if(0 == tmp[0] || strcmp(data.cFileName, tmp) < 0)
			{
				strcpy(tmp, data.cFileName);
			}
		}while(FindNextFile(h,&data));
		::FindClose(h);
		if(count > MaxDirSize)
		{
			pos = strrchr(buf, '\\');
			sprintf(pos, "\\%s", tmp);
			remove(buf);
		}
	}

}

void TLog::output(FILE*& fp, TLock* lock, char* ext)
{
	FileTooLong(fp, lock, ext);


	TGuard guard(lock);
	//将内容写进文件
	fwrite(m_buf, m_pos, 1, fp);
	fflush(fp);
}

TLog& TLog::operator<<(const char* value)
{
	if(m_pos >= sizeof(m_buf) -100)
	{
		return *this;
	}
	m_pos += sprintf(m_buf + m_pos, "%s", value);
	return *this;
}

TLog& TLog::operator<<(int value)
{
	if(m_pos >= sizeof(m_buf) -100)
	{
		return *this;
	}
	m_pos += sprintf(m_buf + m_pos, "%d", value);
	return *this;
}

TLog& TLog::operator<<(double value)
{
	if(m_pos >= sizeof(m_buf) -100)
	{
		return *this;
	}
	m_pos += sprintf(m_buf + m_pos, "%f", value);
	return *this;
}