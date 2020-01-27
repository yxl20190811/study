#pragma once
#include "TEdge.h"
#include "TNode.h"
#include <map>
#include <list>

class TGraph
{
public:
    void AddEdge(const char* aNodeName, const char* zNodeName, bool IsDoubleDir, TCostType cost);
protected:
    void AddEdge(TNode* aNode, TNode* zNode, TCostType cost);
    TNode* AddNode(const char* aNode);
    TEdge* AddEdge();
protected:
    std::map<std::string, TNode> m_NodeMap;
    std::list<TEdge>  m_EdgeLst;
};

