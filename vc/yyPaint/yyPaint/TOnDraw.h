#pragma once
#include "TGraphPtr.h"
#include "TWndPtr.h"

class TOnDraw: public TGraphPtr, public TWndPtr
{
public:
    TOnDraw(void);
    ~TOnDraw(void);
public:
    virtual void OnDraw(CDC* dc);
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
};

