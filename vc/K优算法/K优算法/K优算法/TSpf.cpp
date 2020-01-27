#include "TSpf.h"

TSpf::TSpf()
{
    TNode* m_aNode = NULL;
    TNode* m_zNode = NULL;
    m_dis = NULL;
    m_prev = NULL;
    m_openlst = NULL;
    m_NodeVector = NULL;
}
TSpf::~TSpf()
{
    if(NULL != m_dis)
    {
        free(m_dis);
    }

    if(NULL != m_prev)
    {
        free(m_prev);
    }

    if(NULL != m_openlst)
    {
        free(m_openlst);
    }
    if(NULL != m_NodeVector)
    {
        free(m_NodeVector);
    }
}
void TSpf::init(const char* aNodeName, const char* zNodeName)
{
    m_NodeCount = (int)(m_NodeMap.size());
    m_aNode = AddNode(aNodeName);
    m_zNode = AddNode(zNodeName);
    m_dis = (TDisType*)malloc(sizeof(TDisType)*m_NodeCount+100);
    m_prev = (TNode**)malloc(sizeof(TNode*)*m_NodeCount+100);
    m_openlst = (TDisType*)malloc(sizeof(TDisType)*m_NodeCount+100);
    m_NodeVector = (TNode**)malloc(sizeof(TNode*)*m_NodeCount+100);

    int count = 0;
    for(std::map<std::string, TNode>::iterator it = m_NodeMap.begin();
        m_NodeMap.end() != it; ++it, ++count)
    {
        it->second.m_id = count;
        m_NodeVector[count] = &(it->second);
    }

}
