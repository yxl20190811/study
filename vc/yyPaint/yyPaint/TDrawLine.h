#pragma once
#include "TGraphPtr.h"
#include "TWndPtr.h"
#include "TMouseMoveSelect.h"
class TGraphNode;

class TDrawLine: public TGraphPtr, public TWndPtr
{
public:
    TGraphNode* m_CellA;
    int m_OldX,m_OldY;
public:
    TDrawLine(void);
    ~TDrawLine(void);
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
public:
    void OnLButtonDown(int x, int y);
    void OnLButtonUp(int x, int y);
    void OnMouseMove(int x, int y);
    void ClearCellA();
};

