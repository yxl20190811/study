
// ChildView.cpp : CChildView ���ʵ��
//

#include "stdafx.h"
#include "yyPaint.h"
#include "ChildView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CChildView

CChildView::CChildView()
{
}

CChildView::~CChildView()
{
}


BEGIN_MESSAGE_MAP(CChildView, CWnd)
	ON_WM_PAINT()
    ON_COMMAND(ID_DRAW_LINE, &CChildView::OnDrawLine)
    ON_COMMAND(ID_DRAW_NODE, &CChildView::OnDrawNode)
    ON_COMMAND(ID_VIEW, &CChildView::OnView)
END_MESSAGE_MAP()



// CChildView ��Ϣ�������

BOOL CChildView::PreCreateWindow(CREATESTRUCT& cs) 
{
	if (!CWnd::PreCreateWindow(cs))
		return FALSE;

	cs.dwExStyle |= WS_EX_CLIENTEDGE;
	cs.style &= ~WS_BORDER;
	cs.lpszClass = AfxRegisterWndClass(CS_HREDRAW|CS_VREDRAW|CS_DBLCLKS, 
		::LoadCursor(NULL, IDC_ARROW), reinterpret_cast<HBRUSH>(COLOR_WINDOW+1), NULL);

	return TRUE;
}

void CChildView::OnPaint() 
{
	CPaintDC dc(this); // ���ڻ��Ƶ��豸������
	
	// TODO: �ڴ˴������Ϣ����������
	
	// ��ҪΪ������Ϣ������ CWnd::OnPaint()
}



void CChildView::OnDrawLine()
{
    // TODO: �ڴ���������������
    
}


void CChildView::OnDrawNode()
{
    // TODO: �ڴ���������������
    
}


void CChildView::OnView()
{
    // TODO: �ڴ���������������
    
}


