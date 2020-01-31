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

