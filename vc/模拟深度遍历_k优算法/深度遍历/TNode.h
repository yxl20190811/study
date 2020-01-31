#pragma once
#include <string>
#include "TTypeDef.h"

class TEdge;

class TNode
{
public:
    TNode();
public:
    std::string   m_name;
    int           m_id;
public:
    TDisType      m_dis;
    TNode*        m_prev;
public:
    TEdge*        m_ObverseEdgeLst;
    TEdge*        m_ReverseEdgeLst;
};

