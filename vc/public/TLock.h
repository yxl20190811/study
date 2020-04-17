#pragma once

class TLock
{
public:
	virtual void lock() = 0;
	virtual void unlock() = 0;
};
