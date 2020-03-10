#pragma once
#include "TGraphPtr.h"
#include "TWndPtr.h"
class TDrawNode: public TGraphPtr, public TWndPtr
{
public:
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
    void OnLButtonDown(int x, int y);
};

