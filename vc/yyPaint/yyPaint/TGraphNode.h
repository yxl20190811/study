#pragma once
#include "TGraphCell.h"

class TGraphNode: public TGraphCell
{
public:
    int m_posX, m_posY, m_width, m_height;
public:
    virtual void OnDraw(CDC* dc);
    virtual bool IsSelect(int posX, int posY);
    virtual void OnDrawSelect(CDC* dc);
    virtual void OnDrawSelectCancel(CDC* dc);
    TGraphNode(int x, int y);
    ~TGraphNode(void);
     TCellType GetCellType(){return TGRAPH_CELL_NODE_TYPE;}
};

