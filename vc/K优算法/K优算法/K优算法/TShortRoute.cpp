#include "TShortRoute.h"
#include <stdio.h>
#include "TNode.h"

void TShortRoute::PrintRoute(unsigned long long kSpf)
{
    printf("\r\nk=%u:route size=%u\t", kSpf, m_NodeCount);
    for(int i = m_NodeCount-1; i >= 0; --i)
    {
        printf("%s\t", m_NodeLst[i]->m_name.c_str());
    }
}


