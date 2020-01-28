#include "TEdge.h"
#include <memory.h>
#include <stdio.h>

TEdge::TEdge()
{
    m_aNode = NULL;
    m_zNode = NULL;
    m_cost = 1; 
    m_IsForbidPass = false;
    m_ObverseNextEdge = NULL;
    m_ReverseNextEdge = NULL;
    m_next = NULL;
    m_prev = NULL;
}

