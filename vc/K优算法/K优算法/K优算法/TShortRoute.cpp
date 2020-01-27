#include "TShortRoute.h"
#include <stdio.h>
#include "TNode.h"

TShortRouteLst::TShortRouteLst()
{
    m_count = 0;
    memset(m_map, 0, sizeof(m_map));
    memset(m_HasPop, 0, sizeof(m_HasPop));
    
}


TShortRoutePool::~TShortRoutePool()
{
    free(m_buf);
}


TShortRoutePool::TShortRoutePool(int NodeCount)
{
    long long PoolSize = 200*1000*5;
    m_count = 0;
    m_head = NULL;
    m_RouteSize = sizeof(TShortRoute) + 2*NodeCount*sizeof(void*) + 100;
    m_NodeCount = NodeCount;

    char* buf = (char*)malloc(m_RouteSize*PoolSize);
    memset(buf, 0, m_RouteSize*PoolSize);
    m_buf = buf;
    m_head = (TShortRoute*)buf;
    for(int i = 0; i < PoolSize-1; ++i, buf += m_RouteSize)
    {
        ((TShortRoute*)(buf))->m_next = (TShortRoute*)(buf + m_RouteSize);
    }
}

void TShortRoute::PrintRoute(unsigned long long kSpf)
{
    printf("\r\nk=%u:route size=%u\t", kSpf, m_NodeCount);
    for(int i = m_NodeCount-1; i >= 0; --i)
    {
        printf("%s\t", m_NodeLst[i]->m_name.c_str());
    }
}
