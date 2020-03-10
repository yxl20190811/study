#pragma once
#include "TGraphCell.h"
class TGraphNode;

class TGraphLine: public TGraphCell
{
public:
    TGraphNode* m_aNode, *m_zNode;
    int m_x1, m_y1, m_x2, m_y2;
public:
    void CalPos();
    virtual void OnDraw(CDC* dc);
    virtual bool IsSelect(int posX, int posY);
    virtual void OnDrawSelect(CDC* dc);
    TGraphLine(void);
    ~TGraphLine(void);
    TCellType GetCellType(){return TGRAPH_CELL_LINE_TYPE;}
};

