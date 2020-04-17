#pragma once
#include "TLock.h"
#include <windows.h>

class TCriticalSection: public TLock
{
public:
	TCriticalSection(void);
	~TCriticalSection(void);
    virtual void lock();
	virtual void unlock();
private:
	CRITICAL_SECTION m_cs;
};
