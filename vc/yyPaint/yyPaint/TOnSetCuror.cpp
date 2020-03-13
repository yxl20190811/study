#include "StdAfx.h"
#include "TOnSetCuror.h"
#include "Resource.h"

TOnSetCuror::TOnSetCuror(void)
{
}


TOnSetCuror::~TOnSetCuror(void)
{
}
LRESULT TOnSetCuror::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
{
    switch(message)
    {
    case WM_CREATE: 
        OnCreate(); 
        break;
    case WM_SETCURSOR: 
        SetCursor(m_hCur); 
        break;
    case WM_COMMAND: 
        OnCmmand(wParam);
        break;
    default:
        return false;
    }
    return TRUE;
}

void TOnSetCuror::OnCmmand(WPARAM wParam)
{
    UINT nID = LOWORD(wParam);
    switch(nID)
    {
    case ID_DRAW_LINE: 
        m_hCur = m_hCurLine; 
        break;
    case ID_DRAW_NODE: 
        m_hCur = m_hCurNode; 
        break;
    case ID_VIEW: 
        m_hCur = m_hCurView; 
        break;
    }
}

void TOnSetCuror::OnCreate()
{
    m_hCurNode = LoadIcon(AfxGetInstanceHandle() ,MAKEINTRESOURCE(IDI_DRAW_NODE));
    m_hCurLine = LoadIcon(AfxGetInstanceHandle() ,MAKEINTRESOURCE(IDI_DRAW_LINE));
    m_hCurView = ::LoadIcon(AfxGetInstanceHandle() ,MAKEINTRESOURCE(IDI_VIEW)); 
    m_hCur = m_hCurView;
}
