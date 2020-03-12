#pragma once
#include "TState.h"
#include "TOnSetCuror.h"
#include "TDrawNode.h"
#include "TDrawLine.h"
#include "TGraph.h"
#include "TOnDraw.h"

class TView : public CWnd, public TGraph
{
private:
    TOnSetCuror  m_OnSetCuror;
    TStateChang  m_state;
    TOnDraw      m_OnDraw;
    TDrawNode    m_DrawNode;
    TDrawLine    m_DrawLine;
public:
    TView();
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
};
