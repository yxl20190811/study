#include "TGraph.h"

TNode* TGraph::AddNode(const char* aNode)
{
    TNode tmp;
    tmp.m_name = aNode;
    std::pair<std::map<std::string, TNode>::iterator, bool> ret =
        m_NodeMap.insert(std::pair<std::string, TNode>(tmp.m_name, tmp));
    return &(ret.first->second);
}

TEdge* TGraph::AddEdge()
{
    m_EdgeLst.push_back(TEdge());
    return &(m_EdgeLst.back());
}

void TGraph::AddEdge(TNode* aNode, TNode* zNode, TCostType cost)
{
    TEdge* edge = AddEdge();
    edge->m_aNode = aNode;
    edge->m_zNode = zNode;
    edge->m_cost = cost;
    
    edge->m_ObverseNextEdge = aNode->m_ObverseEdgeLst;
    aNode->m_ObverseEdgeLst = edge;

    edge->m_ReverseNextEdge = zNode->m_ReverseEdgeLst;
    zNode->m_ReverseEdgeLst = edge;
}

void TGraph::AddEdge(const char* aNodeName, const char* zNodeName, bool IsDoubleDir, TCostType cost)
{
    TNode* aNode = AddNode(aNodeName);
    TNode* zNode = AddNode(zNodeName);
    AddEdge(aNode, zNode, cost);
    if(IsDoubleDir)
    {
        AddEdge(zNode, aNode, cost);
    }
}

