#include "StdAfx.h"
#include "TView.h"


TView::TView()
{
    m_OnDraw.SetGraph(this);
    m_OnDraw.SetWnd(this);

    m_DrawNode.SetGraph(this);
    m_DrawNode.SetWnd(this);

    m_DrawLine.SetGraph(this);
    m_DrawLine.SetWnd(this);

    m_DragNode.SetGraph(this);
    m_DragNode.SetWnd(this);
    
}
LRESULT TView::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    if(m_state.WindowProc(message, wParam, lParam))
    {
        return TRUE;
    }
    if(m_OnDraw.WindowProc(message, wParam, lParam))
    {
        return TRUE;
    }
    if(m_OnSetCuror.WindowProc(message, wParam, lParam))
    {
        return TRUE;
    }
    switch(m_state.m_state)
    {
        case DRAW_NODE:
        {
            if(m_DrawNode.WindowProc(message, wParam, lParam))
            {
                return TRUE;
            }
        }
        break;
        case DRAW_LINE:
        {
            if(m_DrawLine.WindowProc(message, wParam, lParam))
            {
                return TRUE;
            }
        }
        break;

        case VIEW:
        {
            if(m_DragNode.WindowProc(message, wParam, lParam))
            {
                return TRUE;
            }
        }
        break;
    }
    return CWnd::WindowProc(message, wParam, lParam);
}

