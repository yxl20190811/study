#include "StdAfx.h"
#include "TDrawLine.h"
#include "TGraphNode.h"
#include "TGraph.h"
#include "Resource.h"

TDrawLine::TDrawLine(void)
{
    m_CellA = NULL;
}


TDrawLine::~TDrawLine(void)
{
}


LRESULT TDrawLine::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    switch(message)
    {
    case WM_COMMAND: 
            {
                UINT nID = LOWORD(wParam);
                switch(nID)
                {
                case ID_DRAW_LINE:
                    ClearCellA();
                }
            }
            break;
    case WM_LBUTTONDOWN:
        OnLButtonDown(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    case WM_LBUTTONUP:
        OnLButtonUp(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    case WM_MOUSEMOVE:
        OnMouseMove(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
        break;
    }
    return FALSE;
}

void TDrawLine::ClearCellA()
{
    if(NULL != m_CellA)
    {
        m_CellA->m_Selected = false;
        m_wnd->Invalidate();
    }
    m_CellA = NULL;
}

void TDrawLine::OnMouseMove(int x, int y)
{
    if(NULL != m_CellA)
    {
        CRect rect;
        m_wnd->GetWindowRect(rect);
        CDC* dc = m_wnd->GetDC();
        dc->Rectangle(0,0, rect.right-rect.left, rect.bottom-rect.top);
        m_graph->OnDraw(dc);
        dc->MoveTo(m_CellA->m_posX+m_CellA->m_width/2, m_CellA->m_posY+m_CellA->m_height/2);
        dc->LineTo(x,y);
        m_CellA->OnDrawSelect(dc);

        TGraphCell* cell = m_graph->FindCellByPos(x, y);
        if(NULL != cell && TGRAPH_CELL_NODE_TYPE == cell->GetCellType())
        {
            cell->OnDrawSelect(dc);
        }
    }


    
    
}

void TDrawLine::OnLButtonUp(int x, int y)
{
    if(NULL != m_CellA)
    {
        TGraphCell* cell = m_graph->FindCellByPos(x, y);
        if(NULL != cell && TGRAPH_CELL_NODE_TYPE == cell->GetCellType())
        {
            m_graph->AddLine(m_CellA, (TGraphNode*)cell);
            m_wnd->Invalidate();
        }
    }
    ClearCellA();
}
void TDrawLine::OnLButtonDown(int x, int y)
{
    ClearCellA();
    TGraphCell* cell = m_graph->FindCellByPos(x, y);
    if(NULL == cell || TGRAPH_CELL_NODE_TYPE != cell->GetCellType())
    {
        return;
    }
    m_CellA = (TGraphNode*)cell;
    m_CellA->m_Selected = true;
    m_wnd->Invalidate();
}