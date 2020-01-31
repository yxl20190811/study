#pragma once
#include "TGraph.h"

class TDeepVisite: public TGraph
{
private:
    TNode* m_aNode;
    TNode* m_zNode;
private:
    long long m_count;
    long long m_LastTime;
    long long m_StartTime;
public:
    void init(const char* aNodeName, const char* zNodeName);
public:
    void deep();
private:
    void deep(TNode* curNode);
};

