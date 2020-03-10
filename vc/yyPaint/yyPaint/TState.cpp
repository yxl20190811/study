#include "StdAfx.h"
#include "TState.h"
#include "Resource.h"

TStateChang::TStateChang()
{
    m_state = VIEW;
}

 LRESULT TStateChang::WindowProc(UINT message, WPARAM wParam, LPARAM lParam)
 {
     switch(message)
     {
         case WM_COMMAND:
         {
             OnCmmand(wParam);
         }
         break;
     }
     return FALSE;
 }

 void TStateChang::OnCmmand(WPARAM wParam)
{
    UINT nID = LOWORD(wParam);
    switch(nID)
    {
    case ID_DRAW_LINE: 
        m_state = DRAW_LINE;
        break;
    case ID_DRAW_NODE: 
        m_state = DRAW_NODE;
        break;
    case ID_VIEW: 
        m_state = VIEW; 
        break;
    }
}
