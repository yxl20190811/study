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
        for(unsigned long long kSpf = 0 ; kSpf < DisMax; ++kSpf)//开始第K条路由计算
        {
            if(kSpf%100 ==99)
            {
                long long time = ::GetTickCount();
                printf("\r\n%d:%d:%d:%d", kSpf, time-StartTime, time-LastTime,m_RouteLst.m_count);
                LastTime = time;
            }
            //从路由列表中取出最短的路由，作为第K条最短路由
            if(0 != kSpf)
            {
                curRoute = m_RouteLst.pop();
                if(NULL == curRoute){break;}//没有找到最短路由则不再有更长的路由，退出循环
                //curRoute->PrintRoute(kSpf);
                //检查是否停止循环
                curRoute->SetDelEdgeState(true);//禁止该路由上已经删除的边
                DelEdgeLst.FindNeedDelEdge(curRoute); //从最短路由中找出“需要删除的边”
            } 
            //遍历“需要删除的边”，每个边删除，计算最短路由
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
                spf();//用D计算最短路由
                saveRoute(curRoute, curDelEdge);//保存路由
                //恢复删除边
                if(0 != kSpf)
                {
                    curDelEdge->m_IsForbidPass = false;
                }
                //退出
                if(0 == kSpf)
                {
                    break;
                }
            }
            //恢复该路由上已经删除的边
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
        //从池中取出空闲的路由
        TShortRoute* tmp = m_ShortRoutePool->pop();
        
        TNode* curNode = m_zNode;
        int count = 0;
        for(; count < m_NodeCount && NULL != curNode; ++count)
        {
            tmp->m_NodeLst[count] = curNode;
            curNode = m_prev[curNode->m_id];
        }
        tmp->m_NodeCount = count;

        //保存“已经删除的边”
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
        //保存dis
        tmp->m_dis = m_dis[m_zNode->m_id];
        //放到lst中
        m_RouteLst.push(tmp);
    }
};

