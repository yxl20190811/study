#pragma once
class TNode;
#include "TEdge.h"
#include "TTypeDef.h"
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

class TShortRoute
{
public:
    TDisType m_dis;
public:
    int m_NodeCount;
    TNode** m_NodeLst;
public:
    int m_DelEdgeCount;
    TEdge** m_DelEdgeLst;
public:
    TShortRoute* m_next;
public:
    void PrintRoute(unsigned long long kSpf);
public:
    inline void SetDelEdgeState(bool state)
    {
        for(int  i =0; i < m_DelEdgeCount; ++i)
        {
            m_DelEdgeLst[i]->m_IsForbidPass = state;
        }
    }
};

class TShortRouteLst
{
public:
    long long  m_count;
    TShortRoute* m_head;
public:
    TShortRouteLst();
public:
    inline void push(TShortRoute* route)
    {
        ++m_count;
        if(NULL == m_head || m_head->m_dis > route->m_dis)
        {
            route->m_next = m_head;
            m_head = route;
            return;
        }

        TShortRoute* prev = m_head;
        TShortRoute* cur = prev->m_next;
        while(NULL != cur)
        {
            if(cur->m_dis > route->m_dis)
            {
                break;
            }
            prev = cur;
            cur = cur->m_next;
        }
        if(prev->m_dis == route->m_dis && prev->m_NodeCount == route->m_NodeCount)
        {
            int count = prev->m_NodeCount;
            if(0 == memcmp(prev->m_NodeLst, route->m_NodeLst, sizeof(void*)*count))
            {
                return;
            }
        }
        {
            prev->m_next = route;
            route->m_next = cur;
            return;
        }
    }
public:
    inline TShortRoute* pop()
    {
        if(NULL == m_head)
        {
            return NULL;
        }
        --m_count;
        TShortRoute* ret = m_head;
        m_head = m_head->m_next;
        return ret;
    }
};
class TShortRoutePool
{
private:
    TShortRoute* m_head;
    char* m_buf;
    int  m_RouteSize;
    int  m_NodeCount;
    long long  m_count;
public:
    TShortRoutePool(int NodeCount);
    ~TShortRoutePool();
public:
    inline void push(TShortRoute* cur)
    {
        cur->m_next = m_head;
        m_head = cur;
        m_count++;
    }
public:
    inline TShortRoute* pop()
    {
        if(NULL == m_head)
        {
            abort();
        }
        TShortRoute* ret = m_head;
        m_head = m_head->m_next;
        memset(ret, 0, m_RouteSize);
        ret->m_NodeLst = (TNode**)((char*)ret + sizeof(TShortRoute) +20);
        ret->m_DelEdgeLst =(TEdge**)((char*)ret+ sizeof(TShortRoute) + m_NodeCount*sizeof(void*) + 50);
        m_count--;
        return ret;
        
    }
};
