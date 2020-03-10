#pragma once

typedef enum
{
    TGRAPH_CELL_NODE_TYPE,
    TGRAPH_CELL_LINE_TYPE,
}TCellType;

class TGraphCell
{
public:
    TGraphCell(){m_orderId = -1; m_Selected = false;}
    virtual void OnDraw(CDC* dc) = 0;
    virtual bool IsSelect(int posX, int posY) = 0;
    virtual void OnDrawSelect(CDC* dc) = 0;
    virtual TCellType GetCellType()= 0;
public:
    int m_orderId;
    bool m_Selected;
};

