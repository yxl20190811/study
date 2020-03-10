
// ChildView.h : CChildView 类的接口
//


#pragma once
#include "TView.h"

// CChildView 窗口

class CChildView : public TView
{
// 构造
public:
	CChildView();

// 特性
// 操作
public:

// 重写
	protected:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);

// 实现
public:
	virtual ~CChildView();

	// 生成的消息映射函数
protected:
	afx_msg void OnPaint();
	DECLARE_MESSAGE_MAP()
public:
    afx_msg void OnDrawLine();
    afx_msg void OnDrawNode();
    afx_msg void OnView();
};

