#pragma once
#include "TLock.h"
class TGuard
{
public:
	TGuard(TLock* lock);
	~TGuard(void);
};
