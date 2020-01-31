#include "TDelEdgeLst.h"
#include <stdlib.h>

TDelEdgeLst::TDelEdgeLst(int max)
{
    m_max = max;
    m_count = 0;
    m_pos = 0;
    m_EdgeLst = (TEdge**)malloc(sizeof(TEdge*)*max);
}


TDelEdgeLst::~TDelEdgeLst(void)
{
    free(m_EdgeLst);
}
