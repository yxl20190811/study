#pragma once
class TNode;
#include "TTypeDef.h"

class TEdge
{
public:
    TEdge();
public:
    TNode* m_aNode;
    TNode* m_zNode;
    TCostType   m_cost; 
    bool        m_IsForbidPass;
public:
    TEdge* m_ObverseNextEdge;
    TEdge* m_ReverseNextEdge;
};

