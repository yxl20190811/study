
// ChildView.h : CChildView ��Ľӿ�
//


#pragma once
#include "TView.h"

// CChildView ����

class CChildView : public TView
{
// ����
public:
	CChildView();

// ����
// ����
public:

// ��д
	protected:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);

// ʵ��
public:
	virtual ~CChildView();

	// ���ɵ���Ϣӳ�亯��
protected:
	afx_msg void OnPaint();
	DECLARE_MESSAGE_MAP()
public:
    afx_msg void OnDrawLine();
    afx_msg void OnDrawNode();
    afx_msg void OnView();
};

