#pragma once
class TShortRoute;

class TShortRoutePool
{
private:
    char* m_buf;
    char* m_pos;
public:
    TShortRoutePool();
    ~TShortRoutePool();
public:
    TShortRoute* pop(int NodeCount, int DelEdgeCount);
};

