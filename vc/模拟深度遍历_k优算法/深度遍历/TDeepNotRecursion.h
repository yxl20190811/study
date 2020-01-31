#pragma once
#include "TGraph.h"

class TDeepNotRecursion: public TGraph
{
private:
    TNode* m_aNode;
    TNode* m_zNode;
public:
    TDeepNotRecursion(void);
    ~TDeepNotRecursion(void);
public:
    void init(const char* aNodeName, const char* zNodeName);
    void deep();
private:
    void PrintRoute(int kSpf, TEdge* curEdge);
};

