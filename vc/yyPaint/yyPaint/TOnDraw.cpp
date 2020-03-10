#include "StdAfx.h"
#include "TOnDraw.h"
#include "TGraph.h"
#include "TGraphCell.h"

TOnDraw::TOnDraw(void)
{
}


TOnDraw::~TOnDraw(void)
{
}
void TOnDraw::OnDraw(CDC* dc)
{
    m_graph->OnDraw(dc);
}

LRESULT TOnDraw::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    if(WM_PAINT == message)
    {
        CPaintDC dc(m_wnd);
        OnDraw(&dc);
        return TRUE;
    }
    return FALSE;
}