#include "TCriticalSection.h"

TCriticalSection::TCriticalSection(void)
{
	InitializeCriticalSection(&m_cs);
}

TCriticalSection::~TCriticalSection(void)
{
	DeleteCriticalSection(&m_cs);
}

void TCriticalSection::lock()
{
	EnterCriticalSection(&m_cs);
}

void TCriticalSection::unlock()
{
	LeaveCriticalSection(&m_cs);
}