#include "TKspf.h"


void TKspf::init(const char* aNodeName, const char* zNodeName)
{
    TSpf::init(aNodeName, zNodeName);
    m_ShortRoutePool = new TShortRoutePool(m_NodeCount);
}
TKspf::TKspf()
{
    m_ShortRoutePool = NULL;
}
TKspf::~TKspf()
{
    if(NULL != m_ShortRoutePool)
    {
        delete m_ShortRoutePool;
    }
}