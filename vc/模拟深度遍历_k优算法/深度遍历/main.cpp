// 深度遍历.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "TDeepVisite.h"
#include <Windows.h>
#include "TDeepNotRecursion.h"

void AddEdge(TGraph& spf, int i, int j)
{
    char aNodeName[1000], zNodeName[1000];
    sprintf(aNodeName, "%d_%d", i, j);
    sprintf(zNodeName, "%d_%d", i+1, j);
    spf.AddEdge(aNodeName, zNodeName, true, 1);

    sprintf(zNodeName, "%d_%d", i, j+1);
    spf.AddEdge(aNodeName, zNodeName, true, 1);
}

void test3()
{
    TDeepNotRecursion spf;
    const int x = 6;
    const int y = 6;
    for(int i = 0; i < x; ++i)
    {
        for(int j = 0; j < y; ++j)
        {
            AddEdge(spf, i, j);
        }
    }
    spf.init("1_1", "2_2");
    spf.deep();
}


void test2()
{
    TDeepNotRecursion spf;
    spf.AddEdge("A", "B", true, 1);
    spf.AddEdge("B", "C", true, 1);
    spf.AddEdge("C", "D", true, 1);
    spf.AddEdge("D", "E", true, 1);
    spf.AddEdge("E", "F", true, 1);
    spf.AddEdge("F", "G", true, 1);
    spf.AddEdge("G", "H", true, 1);

    spf.AddEdge("B", "D", true, 1);
    spf.AddEdge("E", "G", true, 1);

    spf.init("A","H");
    spf.deep();
}

int _tmain(int argc, _TCHAR* argv[])
{
    int start = ::GetTickCount();
    test3();
    int end = ::GetTickCount();
    printf("\r\n used time = %d", end-start);
	return 0;
}

