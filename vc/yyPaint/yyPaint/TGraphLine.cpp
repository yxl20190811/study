#include "StdAfx.h"
#include "TGraphLine.h"
#include "TGraphNode.h"

TGraphLine::TGraphLine(void)
{
    m_aNode = NULL;
    m_zNode = NULL;

    m_x1 =0;
    m_x2 = 0;
    m_y1 = 0;
    m_y2 = 0;
}


TGraphLine::~TGraphLine(void)
{
}

void TGraphLine::OnDraw(CDC* dc)
{
    dc->MoveTo(m_x1,m_y1);
    dc->LineTo(m_x2,m_y2);
}

void TGraphLine::CalPos()
{
    int x1 =  m_aNode->m_posX + m_aNode->m_width/2;
    int y1 =  m_aNode->m_posY + m_aNode->m_height/2;

    int x2 =  m_zNode->m_posX + m_zNode->m_width/2;
    int y2 =  m_zNode->m_posY + m_zNode->m_height/2;

    int x = x2-x1;
    int y = y2-y1;

    m_x1 = x1 + m_aNode->m_width/2;
    m_y1 = y2-(x-m_aNode->m_width/2)*y/x;

    m_x2 = x2-m_zNode->m_width/2;
    m_y2 = y2-m_zNode->m_width/2*y/x;
}

bool TGraphLine::IsSelect(int posX, int posY)
{
    return false;
}
void TGraphLine::OnDrawSelect(CDC* dc)
{
}