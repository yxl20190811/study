#include "ut.h"
#include <list>
#include <stdlib.h>
#include "TConfigIni.h"
#include <windows.h>
#include <conio.h>

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
		LPWSTR cmdLine = ::GetCommandLineW();
		int argc = 0;
		LPWSTR* argv = CommandLineToArgvW(cmdLine, &argc);  
		if( 2 != argc)
		{
			return;
		}
		LPWSTR cmd = argv[1];
		unsigned long len = lstrlenW (cmd);
		char buf[1024*100];
		wcstombs(buf, cmd, len+1);
		if(0 != strcmp(buf, "UT_TEST"))
		{
			return;
		}
		////////////////////////
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
		printf("\r\npress any key to exit");
		getch();
		exit(0);
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

