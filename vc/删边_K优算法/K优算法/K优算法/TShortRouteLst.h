#pragma once
#include "TShortRoute.h"

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
                TNode** RouteNodeLst = route->m_NodeLst;
                int size = cur->m_NodeCount*sizeof(void*);
                while(cur)
                {
                    if(0 == memcmp(cur->m_NodeLst, RouteNodeLst, size))
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



