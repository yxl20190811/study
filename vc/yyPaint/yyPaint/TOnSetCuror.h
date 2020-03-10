#pragma once
class TOnSetCuror
{
public:
    TOnSetCuror(void);
    ~TOnSetCuror(void);
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
    void OnCreate();
    void OnCmmand(WPARAM wParam);
public:
    HCURSOR m_hCurNode, m_hCurLine, m_hCurView, m_hCur;
};

