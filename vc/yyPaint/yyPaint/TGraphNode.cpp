#include "StdAfx.h"
#include "TGraphNode.h"


TGraphNode::TGraphNode(int x, int y)
{
    m_posX =x;
    m_posY = y;
    m_width = 30;
    m_height = 30;
}


TGraphNode::~TGraphNode(void)
{
}

void TGraphNode::OnDraw(CDC* dc)
{
   
    dc->Rectangle(m_posX, m_posY, m_posX+m_width, m_posY+m_height);
    
}


bool TGraphNode::IsSelect(int posX, int posY)
{
    if( posX<  m_posX || 
        posX > m_posX + m_width || 
        posY<  m_posY || 
        posY > m_posY + m_width 
        )
    {
        return false;
    }
    return true;
}

void TGraphNode::OnDrawSelect(CDC* dc)
{
    dc->Rectangle(m_posX, m_posY, m_posX+m_width, m_posY+m_height);
    /*
    dc->Ellipse(m_posX+m_width/2-3, m_posY-3, m_posX+m_width/2+3, m_posY+3);
    dc->Ellipse(m_posX+m_width/2-3, m_posY+m_width-3, m_posX+m_width/2+3, m_posY+m_width+3);
    dc->Ellipse(m_posX+-3, m_posY+m_height/2-3, m_posX+3, m_posY+m_height/2+3);
    dc->Ellipse(m_posX+m_width+-3, m_posY+m_height/2-3, m_posX+m_width+3, m_posY+m_height/2+3);
    */
    CRect rect(m_posX, m_posY, m_posX+m_width, m_posY+m_height);
    dc->DrawFocusRect(rect);
}

void TGraphNode::OnDrawSelectCancel(CDC* dc)
{
    CRect rect(m_posX, m_posY, m_posX+m_width, m_posY+m_height);
    dc->DrawFocusRect(rect);
}
