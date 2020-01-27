#pragma once
class TNode;
#include "TEdge.h"
#include "TTypeDef.h"
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <map>

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
    TShortRoute*  m_map[10000];
    TShortRoute*  m_HasPop[10000];
public:
    TShortRouteLst();
public:
    inline void push(TShortRoute* route)
    {
        ++m_count;
        {
            TShortRoute*& head = m_HasPop[route->m_NodeCount];
            if(NULL != head)
            {
                TShortRoute* cur = head;
                while(cur)
                {
                    if(0 == memcmp(cur->m_NodeLst, route->m_NodeLst, cur->m_NodeCount*sizeof(void*)))
                    {
                        //cur->PrintRoute(-1);
                        //route->PrintRoute(-2);
                        return;
                    }
                    cur = cur->m_next;
                }
            }
        }
        {
            TShortRoute*& head = m_map[route->m_NodeCount];
            if(NULL != head)
            {
                TShortRoute* cur = head;
                while(cur)
                {
                    if(0 == memcmp(cur->m_NodeLst, route->m_NodeLst, cur->m_NodeCount*sizeof(void*)))
                    {
                        //cur->PrintRoute(-1);
                        //route->PrintRoute(-2);
                        return;
                    }
                    cur = cur->m_next;
                }
            }
            route->m_next = head;
            head = route;
        }
    }
public:
    inline TShortRoute* pop()
    {
        TShortRoute** head = NULL;
        for(int i = 0; i < sizeof(m_map)/sizeof(TShortRoute*); ++i)
        {
            if(NULL != m_map[i])
            {
                head = &(m_map[i]);
                break;
            }
        }
        if(NULL == head)
        {
            return NULL;
        }
        TShortRoute* ret = *head;
        *head = ret->m_next;
        --m_count;

        head = &(m_HasPop[ret->m_NodeCount]);
        ret->m_next = *head;
        *head = ret;

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
        /*
        cur->m_next = m_head;
        m_head = cur;
        m_count++;
        */
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
