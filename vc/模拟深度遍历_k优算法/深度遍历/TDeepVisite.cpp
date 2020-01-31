#include "StdAfx.h"
#include "TDeepVisite.h"
#include <Windows.h>

void TDeepVisite::init(const char* aNodeName, const char* zNodeName)
{
    m_aNode = AddNode(aNodeName);
    m_zNode = AddNode(zNodeName);
}

void TDeepVisite::deep()
{
    m_count = 0;
    m_StartTime = ::GetTickCount();
    m_LastTime = m_StartTime;
    m_aNode->m_prev = NULL;
    m_aNode->m_dis = 0;
    deep(m_aNode);
}
void TDeepVisite::deep(TNode* curNode)
{
    if(m_zNode == curNode)
    {
        ++m_count;
        if(m_count %1 == 0)
        {
            TNode* tmp = curNode;
            long long time = ::GetTickCount();
            printf("\r\n%d  %d  %d\t", m_count, time-m_LastTime, time - m_StartTime);
            while(tmp)
            {
                printf("\t%s", tmp->m_name.c_str());
                tmp = tmp->m_prev;
            }   
        }
    }
    else
    {
        TEdge* curEdge = curNode->m_ObverseEdgeLst;
        while(curEdge)
        {
            if(curEdge->m_zNode->m_dis > curNode->m_dis + curEdge->m_cost)
            {
                curEdge->m_zNode->m_dis = curNode->m_dis + curEdge->m_cost;
                curEdge->m_zNode->m_prev = curNode;
                deep(curEdge->m_zNode);
            }
            curEdge = curEdge->m_ObverseNextEdge;
        }
    }
    curNode->m_prev = NULL;
    curNode->m_dis = DisMax;
}
