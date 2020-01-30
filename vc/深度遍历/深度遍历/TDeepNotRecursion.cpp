#include "TDeepNotRecursion.h"


TDeepNotRecursion::TDeepNotRecursion(void)
{
}



TDeepNotRecursion::~TDeepNotRecursion(void)
{
}

void TDeepNotRecursion::init(const char* aNodeName, const char* zNodeName)
{
    m_aNode = AddNode(aNodeName);
    m_zNode = AddNode(zNodeName);
}

void TDeepNotRecursion::PrintRoute(int kSpf, TEdge* curEdge)
{
   TNode* v[1000];

   int count = 0;
    while(curEdge)
    {
        v[count] = curEdge->m_zNode;
        ++count;
        curEdge = curEdge->m_next;
    }
    v[count] = m_aNode;
    ++count;

    printf("\r\n%d:%d\t", kSpf, count);
    for(int i = count-1; i >= 0; --i)
    {
        printf("%s\t", v[i]->m_name.c_str());
    }
}

void TDeepNotRecursion::deep()
{
    m_aNode->m_dis = 0;
    TEdge* curEdge = m_aNode->m_ObverseEdgeLst;
    curEdge->m_prev = NULL;
    //stack.push(curEdge);
    TEdge* stack = NULL;
    

    long long kSpf = 0;
    while(true)
    {
        ++kSpf;
        while(true)//ѭ��Ѱ��"��Ч"�ĺ����
        {
            //������������������Ч�ĺ����
            if(curEdge->m_zNode != m_zNode 
                && curEdge->m_aNode->m_dis +1 < curEdge->m_zNode->m_dis
                && (curEdge->m_zNode->m_ObverseEdgeLst))
            {
                break;
            }

            //����Ѿ���Z�� ���ӡ��ǰ·��
            if(curEdge->m_zNode == m_zNode)
            {
                curEdge->m_next = stack;
                PrintRoute(kSpf, curEdge);
            }

            while(true)//ȡһ�����ڵı�
            {
                curEdge = curEdge->m_ObverseNextEdge;
                if(NULL != curEdge)
                {
                    break;
                }
                //���ڱ��Ѿ������꣬����Ҫ���ˣ�curEdge = stack.pop();
                {
                    if(!stack){ return; }
                    curEdge = stack;
                    stack = stack->m_next;
                    curEdge->m_zNode->m_dis = DisMax;
                    //PrintRoute(stack);
                }
            }
        }

        //stack.push(curEdge);
        {
            curEdge->m_zNode->m_dis = curEdge->m_aNode->m_dis + 1;
            curEdge->m_next = stack;
            stack = curEdge;
            //PrintRoute(stack);
        }
        curEdge = curEdge->m_zNode->m_ObverseEdgeLst;

    }
}