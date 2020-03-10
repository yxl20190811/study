#include "StdAfx.h"
#include "TDrawNode.h"
#include "TGraph.h"
#include "TGraphNode.h"


LRESULT TDrawNode::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    switch(message)
    {
    case WM_LBUTTONDOWN:
        OnLButtonDown(GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam));
    }
    return FALSE;
}

void TDrawNode::OnLButtonDown(int x, int y)
{
    
    m_graph->AddNode(x,y);
    m_wnd->Invalidate();
}

