#pragma once
#include "TGraphPtr.h"
#include "TWndPtr.h"
class TGraphNode;

class TMouseMoveSelect: public TGraphPtr, public TWndPtr
{
public:
    TMouseMoveSelect(void);
    ~TMouseMoveSelect(void);
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
    void OnMouseMove(int posX, int posY);
public:
    TGraphNode* m_SelectNode;
};

