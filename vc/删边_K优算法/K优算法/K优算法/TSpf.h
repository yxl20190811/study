#pragma once
#include "TGraph.h"

class TSpf: public TGraph
{
protected:
    int    m_NodeCount;
protected:
    TNode* m_aNode;
    TNode* m_zNode;
protected:
    TDisType*   m_dis;
    TNode**     m_prev;
    TDisType*   m_openlst;
    TNode**     m_NodeVector;
public:
    TSpf();
    ~TSpf();
    void init(const char* aNodeName, const char* zNodeName);
public:
    inline void spf()
    {
        //����·�ɼ��������
        ClearData();
        //��ʼ·�ɼ���
        while(true)
        {
            //��openlstȡ������Ľڵ㣬��Ϊ��ǰ�ڵ�
            TNode* curNode = Openlst_pop();
            //���û�нڵ���Դ������˳�ѭ��
            if(NULL == curNode){break;}
            //����Ѿ�Ѱ�ҵ�Z�����˳�ѭ��
            if(curNode == m_zNode){break;}
            //Ѱ�����ڽڵ�
            FindNextNode(curNode);
        }
    }
public:
    inline void ClearData()
    {
        memset(m_dis, 0xff, sizeof(TDisType)*m_NodeCount);
        memset(m_prev, 0, sizeof(TNode*)*m_NodeCount);
        memset(m_openlst, 0xff, sizeof(TDisType)*m_NodeCount);
        m_prev[m_aNode->m_id] = NULL;
        m_dis[m_aNode->m_id] = 0;
        m_openlst[m_aNode->m_id] = 0;
    }
public:
    inline TNode* Openlst_pop()
    {
        TDisType dis = DisMax;
        int NodeId = -1;
        int count = m_NodeCount;
        for(int  i = 0; i < count; ++i)
        {
            if(m_openlst[i] < dis)
            {
                dis = m_openlst[i];
                NodeId = i;
            }
        }
        if(NodeId > -1)
        {
            m_openlst[NodeId] = DisMax;
            return m_NodeVector[NodeId];
        }
        return NULL;
    }
public:
    inline void FindNextNode(TNode* curNode)
    {
        TEdge* edge = curNode->m_ObverseEdgeLst;
        while(NULL != edge)
        {
            TDisType curDis = m_dis[curNode->m_id];
            if(!edge->m_IsForbidPass)
            {
                TDisType dis = curDis + edge->m_cost;
                int tmpId = edge->m_zNode->m_id;
                if(dis < m_dis[tmpId])
                {
                    m_dis[tmpId] = dis;
                    m_prev[tmpId] = curNode;
                    m_openlst[tmpId] = dis;
                }
            }
            edge = edge->m_ObverseNextEdge;
        }
    }
};

