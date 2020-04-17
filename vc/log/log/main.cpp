// log.cpp : 定义控制台应用程序的入口点。
//

#include "TLog.h"

int main(int argc, char* argv[])
{
	int i = 0;
	while(1)
	{
		IF_LOG(1){LOG(1) << i;}
		++i;
	}

	return 0;
}

