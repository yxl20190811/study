#pragma once
#include <map>

class TGraphNode;
class TGraphCell;

class TGraph
{
public:
    typedef std::map<int, TGraphCell*> TCellMap;
    TCellMap m_CellMap;
public:
    void AddNode(int x, int y);
    void AddLine(TGraphNode* a, TGraphNode* z);
    TGraphCell* FindCellByPos(int x, int y);
    void OnDraw(CDC* dc);
};

