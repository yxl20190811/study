#include "ut.h"
#include <list>
#include <stdlib.h>


class TUt
{
public:
	static std::list<TRegUt*>* get()
	{
		static std::list<TRegUt*> lst;
		return &lst;
	}
public:
	TUt()
	{
		int ok = 0;
		int nopass = 0;
		for(std::list<TRegUt*>::iterator it = TUt::get()->begin();
			TUt::get()->end() != it; ++it)
		{
			if((*it)->m_fun() < 0)
			{
				++nopass;
				printf("\r\n[ut test error][%s:%s:%d]", (*it)->m_name.c_str(), (*it)->m_FileName.c_str(), (*it)->m_FileLine);
			}
			else
			{
				++ok;
			}
		}
		printf("\r\nut finish. [%d no pass] [%d ok]", nopass, ok);
	}
};


TRegUt::TRegUt(int (*fun)(), const char* name, const char* FileName, int FileLine)
{
	m_fun = fun;
	m_name = name;
	m_FileName =  FileName;
	m_FileLine = FileLine;
	TUt::get()->push_back(this);
}




UT_TEST(ut_ok)
{
	return 0;
}

UT_TEST(ut_fail)
{
	return -1;
}

//放到最后一行，等待所有的单元测试函数都注册后才执行单元测试函数
static TUt ut;

