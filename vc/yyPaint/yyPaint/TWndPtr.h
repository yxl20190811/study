#pragma once
class TWndPtr
{
public:
    CWnd* m_wnd;
public:
    virtual void SetWnd(CWnd* wnd){m_wnd = wnd;}
};

