#include "StdAfx.h"
#include "TMouseMoveSelect.h"
#include "Resource.h"
#include "TGraph.h"
#include "TGraphNode.h"
#include "TGraphCell.h"

TMouseMoveSelect::TMouseMoveSelect(void)
{
    m_SelectNode = NULL;
}


TMouseMoveSelect::~TMouseMoveSelect(void)
{
}

LRESULT TMouseMoveSelect::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
     switch(message)
    {
        case WM_COMMAND: 
            {
                UINT nID = LOWORD(wParam);
                switch(nID)
                {
                case ID_DRAW_LINE:
                    if(NULL != m_SelectNode)
                    {
                        m_SelectNode->OnDrawSelectCancel(m_wnd->GetDC());
                        m_SelectNode = NULL;
                    }
                }
            }
            break;
        case WM_MOUSEMOVE:
            OnMouseMove(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));break;
     }

    return FALSE;
}

void TMouseMoveSelect::OnMouseMove(int posX, int posY)
{
    if(NULL != m_SelectNode)
    {
        if(!m_SelectNode->IsSelect(posX, posY))
        {
            m_SelectNode->OnDrawSelectCancel(m_wnd->GetDC());
            m_SelectNode = NULL;
        }
    }
    TGraphCell* cell = m_graph->FindCellByPos(posX, posY);
    if(cell == NULL)
    {
        return;
    }

    if(cell != m_SelectNode && NULL != m_SelectNode)
    {
        m_SelectNode->OnDrawSelectCancel(m_wnd->GetDC());
    }
    cell->OnDrawSelect(m_wnd->GetDC());
    m_SelectNode = (TGraphNode*)cell;
}