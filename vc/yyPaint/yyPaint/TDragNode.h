#pragma once
#include "TGraphPtr.h"
#include "TWndPtr.h"
#include "TMouseMoveSelect.h"
#include <list>
class TGraphNode;
class TGraphLine;

class TDragNode: public TGraphPtr, public TWndPtr
{
public:
    TDragNode(void);
    ~TDragNode(void);
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
public:
    TGraphNode* m_SelectNode;
    int m_offsetX, m_offsetY;
    int m_OldX, m_OldY;
    std::list<TGraphLine*> m_LineLst;
    void OnLButtonDown(int x, int y);
    void OnLButtonUp(int x, int y);
    void OnMouseMove(int x, int y);
    void ClearSelectNode();
    void CancelDrag();
    void ReCalLinePos();
};

