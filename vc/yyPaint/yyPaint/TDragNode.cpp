#include "StdAfx.h"
#include "TDragNode.h"
#include "TGraphNode.h"
#include "TGraph.h"
#include "Resource.h"
#include "TGraphLine.h"

TDragNode::TDragNode(void)
{
    m_SelectNode = NULL;
}


TDragNode::~TDragNode(void)
{
}


LRESULT TDragNode::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    switch(message)
    {
    case WM_LBUTTONDOWN:
        OnLButtonDown(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    case WM_LBUTTONUP:
        OnLButtonUp(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    case WM_MOUSEMOVE:
        OnMouseMove(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    case WM_RBUTTONDOWN:
        CancelDrag();
         m_wnd->Invalidate();
        break;
    case WM_KEYDOWN:
        if(27 == wParam)//ESC被按下
        {
            CancelDrag();
            m_wnd->Invalidate();
        }
        break;
    }
    return FALSE;
}

void TDragNode::OnMouseMove(int x, int y)
{
    if(NULL == m_SelectNode)
    {
        return;
    }
    m_SelectNode->m_posX = x - m_offsetX;
    m_SelectNode->m_posY = y - m_offsetY;
    ReCalLinePos();
    m_wnd->Invalidate();
}

void TDragNode::OnLButtonUp(int x, int y)
{
    if(NULL == m_SelectNode)
    {
        return;
    }
    m_SelectNode->m_posX = x - m_offsetX;
    m_SelectNode->m_posY = y - m_offsetY;
    ReCalLinePos();
    ClearSelectNode();
    m_wnd->Invalidate();
}

void TDragNode::ClearSelectNode()
{
    if(NULL != m_SelectNode)
    {
        m_SelectNode->m_Selected = false;
    }
    m_SelectNode = NULL;
    m_LineLst.clear();
}

void TDragNode::ReCalLinePos()
{
    std::list<TGraphLine*>::iterator it = m_LineLst.begin();
    std::list<TGraphLine*>::iterator end = m_LineLst.end();
    for(; it != end; ++it)
    {
        (*it)->CalPos();
    }
}

void TDragNode::OnLButtonDown(int x, int y)
{
    ClearSelectNode();
    TGraphCell* cell = m_graph->FindCellByPos(x, y);
    if(NULL == cell || TGRAPH_CELL_NODE_TYPE != cell->GetCellType())
    {
        return;
    }
    m_SelectNode = (TGraphNode*)cell;
    m_SelectNode->m_Selected = true;
    m_OldX = m_SelectNode->m_posX;
    m_OldY = m_SelectNode->m_posY;
    m_offsetX = x - m_SelectNode->m_posX;
    m_offsetY = y - m_SelectNode->m_posY ;
    //找到关联的line
    {
        TGraph::TCellMap::iterator it = m_graph->m_CellMap.begin();
        TGraph::TCellMap::iterator end = m_graph->m_CellMap.end();
        for(; it != end; ++it)
        {
            TGraphCell* cell = it->second;
            if(TGRAPH_CELL_LINE_TYPE != cell->GetCellType())
            {
                continue;
            }
            TGraphLine* line = (TGraphLine*)cell;
            if(line->m_aNode == m_SelectNode || line->m_zNode == m_SelectNode)
            {
                m_LineLst.push_back(line);
            }
        }
    }
    m_wnd->Invalidate();
}

void TDragNode::CancelDrag()
{
    if(NULL == m_SelectNode)
    {
        return;
    }
    m_SelectNode->m_posX = m_OldX;
    m_SelectNode->m_posY = m_OldY;
    m_SelectNode->m_Selected = false;
    ReCalLinePos();
    ClearSelectNode();
    
}