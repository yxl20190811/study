// log.cpp : �������̨Ӧ�ó������ڵ㡣
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

