#include "TNode.h"


TNode::TNode()
{
    m_id = 0;
    m_ObverseEdgeLst = NULL;
    m_ReverseEdgeLst = NULL;

    m_prev = 0;
    m_dis = DisMax;
}

