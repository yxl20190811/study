#pragma once

typedef enum {
    DRAW_NODE,
    DRAW_LINE,
    DRAW_LINEING,
    VIEW
}TState;

class TStateChang
{
public:
    TStateChang();
    TState m_state;
public:
    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
    void OnCmmand(WPARAM wParam);
};