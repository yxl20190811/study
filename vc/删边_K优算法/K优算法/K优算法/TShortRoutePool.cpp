#include "TShortRoutePool.h"
#include "TShortRoute.h"

static unsigned long long PoolSize = (long long)0x40000000*(long long)10;

TShortRoutePool::TShortRoutePool(void)
{
    m_buf = (char*)malloc(PoolSize);
    memset(m_buf, 0, PoolSize);
    m_pos = m_buf;
}


TShortRoutePool::~TShortRoutePool(void)
{
    free(m_buf);
}

TShortRoute* TShortRoutePool::pop(int NodeCount, int DelEdgeCount)
{
    int NeedSize = sizeof(TShortRoute) + sizeof(void*)*NodeCount + sizeof(void*)*(DelEdgeCount+2);
    if(NeedSize + m_pos > m_buf + PoolSize)
    {
        abort();
    }
    TShortRoute* ret = (TShortRoute*)m_pos;
    ret->m_NodeLst = (TNode**)(m_pos + sizeof(TShortRoute));
    if(DelEdgeCount > 0)
    {
        ret->m_DelEdgeLst = (TEdge**)(m_pos + sizeof(TShortRoute) + sizeof(void*)*NodeCount+sizeof(void*));
    }
    m_pos += NeedSize;
    return ret;
}

