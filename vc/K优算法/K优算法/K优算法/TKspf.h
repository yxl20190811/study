#pragma once
#include "TSpf.h"
#include "TDelEdgeLst.h"
#include "TShortRoute.h"
#include <Windows.h>

class TKspf: public TSpf
{
private:
    TShortRoutePool* m_ShortRoutePool;
    TShortRouteLst  m_RouteLst;
public:
    void init(const char* aNodeName, const char* zNodeName);
    TKspf();
    ~TKspf();
public:
    void kspf()
    {
        long long StartTime = ::GetTickCount();
        long long LastTime = StartTime;

        TDelEdgeLst DelEdgeLst(m_NodeCount);
        TShortRoute* curRoute = NULL;
        TEdge* curDelEdge = NULL;
        for(unsigned long long kSpf = 0 ; kSpf < DisMax; ++kSpf)//��ʼ��K��·�ɼ���
        {
            if(kSpf%100 ==99)
            {
                long long time = ::GetTickCount();
                printf("\r\n%d:%d:%d:%d", kSpf, time-StartTime, time-LastTime,m_RouteLst.m_count);
                LastTime = time;
            }
            //��·���б���ȡ����̵�·�ɣ���Ϊ��K�����·��
            if(0 != kSpf)
            {
                curRoute = m_RouteLst.pop();
                if(NULL == curRoute){break;}//û���ҵ����·�������и�����·�ɣ��˳�ѭ��
                //curRoute->PrintRoute(kSpf);
                //����Ƿ�ֹͣѭ��
                curRoute->SetDelEdgeState(true);//��ֹ��·�����Ѿ�ɾ���ı�
                DelEdgeLst.FindNeedDelEdge(curRoute); //�����·�����ҳ�����Ҫɾ���ıߡ�
            } 
            //��������Ҫɾ���ıߡ���ÿ����ɾ�����������·��
            while(true)
            {
                if(0 != kSpf)
                {
                    curDelEdge = DelEdgeLst.pop();
                    if(NULL == curDelEdge)
                    {
                        break;
                    }
                    curDelEdge->m_IsForbidPass = true;
                }
                spf();//��D�������·��
                saveRoute(curRoute, curDelEdge);//����·��
                //�ָ�ɾ����
                if(0 != kSpf)
                {
                    curDelEdge->m_IsForbidPass = false;
                }
                //�˳�
                if(0 == kSpf)
                {
                    break;
                }
            }
            //�ָ���·�����Ѿ�ɾ���ı�
            if(0 != kSpf)
            {
                curRoute->SetDelEdgeState(false);
                m_ShortRoutePool->push(curRoute);
            }
        }
    }
public:
    inline void saveRoute(TShortRoute* curRoute, TEdge* curDelEdge)
    {
        if(m_prev[m_zNode->m_id] == NULL){return;}
        //�ӳ���ȡ�����е�·��
        TShortRoute* tmp = m_ShortRoutePool->pop();
        
        TNode* curNode = m_zNode;
        int count = 0;
        for(; count < m_NodeCount && NULL != curNode; ++count)
        {
            tmp->m_NodeLst[count] = curNode;
            curNode = m_prev[curNode->m_id];
        }
        tmp->m_NodeCount = count;

        //���桰�Ѿ�ɾ���ıߡ�
        if(NULL != curRoute && NULL != curDelEdge)
        {
            if(curRoute->m_DelEdgeCount >= m_NodeCount)
            {
                abort();
            }
            memcpy(tmp->m_DelEdgeLst, curRoute->m_DelEdgeLst, curRoute->m_DelEdgeCount*sizeof(void*));
            tmp->m_DelEdgeLst[curRoute->m_DelEdgeCount] = curDelEdge;
            tmp->m_DelEdgeCount = curRoute->m_DelEdgeCount + 1;
        }
        //����dis
        tmp->m_dis = m_dis[m_zNode->m_id];
        //�ŵ�lst��
        m_RouteLst.push(tmp);
    }
};

