#pragma once
#include <string>

class TEdge;

class TNode
{
public:
    TNode();
public:
    std::string   m_name;
    int           m_id;
public:
    TEdge*        m_ObverseEdgeLst;
    TEdge*        m_ReverseEdgeLst;
};

