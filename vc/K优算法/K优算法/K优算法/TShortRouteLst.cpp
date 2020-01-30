#include "TShortRouteLst.h"

TShortRouteLst::TShortRouteLst()
{
    m_count = 0;
    memset(m_map, 0, sizeof(m_map));
    memset(m_HasPop, 0, sizeof(m_HasPop));
    
}
