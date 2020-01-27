#pragma once
#include <stdio.h>
#include "TShortRoute.h"
#include "TNode.h"

class TEdge;

class TDelEdgeLst
{
private:
    int m_max;
    int m_count;
    int m_pos;
    TEdge** m_EdgeLst;
public:
    TDelEdgeLst(int max);
    ~TDelEdgeLst(void);
public:
    inline TEdge* pop()
    {
        if(m_count > m_pos)
        {
            TEdge* ret = m_EdgeLst[m_pos];
            ++m_pos;
            return ret;
        }
        return NULL;
    }
public:
    inline void FindNeedDelEdge(TShortRoute* curRoute)
    {
        m_pos = 0;
        m_count = 0;
        TNode* prevNode = curRoute->m_NodeLst[curRoute->m_NodeCount-1];
        TNode* curNode = curRoute->m_NodeLst[curRoute->m_NodeCount-2];
        TNode* NextNode = NULL;
        for(int i = curRoute->m_NodeCount-3; i >= 0; --i)
        {
            if(i > 0)
            {
                NextNode = curRoute->m_NodeLst[i];
            }
            else
            {
                NextNode = NULL;
            }

            TEdge* edge = curNode->m_ReverseEdgeLst;
            TEdge* DelEdge = NULL;
            bool IsNeedDel = false;
            while(NULL != edge)
            {
                if(edge->m_aNode == prevNode)
                {
                    DelEdge = edge;
                }
                else if(edge->m_aNode == NextNode)
                {
                }
                else
                {
                    IsNeedDel = true;
                }
                if(IsNeedDel && DelEdge)
                {
                    m_EdgeLst[m_count] = DelEdge;
                    ++m_count;
                    break;
                }
                edge = edge->m_ReverseNextEdge;
            }
            prevNode = curNode;
            curNode = NextNode;
        }
    }
};

