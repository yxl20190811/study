#include "StdAfx.h"
#include "TGraph.h"
#include "TGraphCell.h"
#include "TGraphNode.h"
#include "Resource.h"
#include "TGraphLine.h"

void TGraph::AddNode(int x, int y)
{
    TGraphNode* node = new TGraphNode(x, y);
    node->m_orderId = m_CellMap.size() +1;
    m_CellMap[node->m_orderId] = node;
}

void TGraph::AddLine(TGraphNode* a, TGraphNode* z)
{
    TGraphLine* line = new TGraphLine();
    line->m_aNode = a;
    line->m_zNode = z;
    line->CalPos();
    line->m_orderId = m_CellMap.size() +1;
    m_CellMap[line->m_orderId] = line;
}

TGraphCell* TGraph::FindCellByPos(int x, int y)
{
    int size = m_CellMap.size();
    if(size < 1)
    {
        return NULL;
    }
    TGraph::TCellMap::iterator it = m_CellMap.end();
    --it;
   
    for(int i = 0; i < size; --it, ++i)
    {
        TGraphCell* cell = it->second;
        if(cell->IsSelect(x, y))
        {
            return cell;
        }
    }
    return NULL;
}

void TGraph::OnDraw(CDC* dc)
{
    TGraph::TCellMap::iterator it =m_CellMap.begin();
    TGraph::TCellMap::iterator end = m_CellMap.end();
    for(; end != it; ++it)
    {
        TGraphCell* cell = it->second;
        if(cell->m_Selected)
        {
            cell->OnDrawSelect(dc);
        }
        else
        {
            cell->OnDraw(dc);
        }
        
    }
}






