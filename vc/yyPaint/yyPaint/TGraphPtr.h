#pragma once
class TGraph;

class TGraphPtr
{
public:
    TGraph*  m_graph;
public:
    virtual void SetGraph(TGraph*  graph);
};

