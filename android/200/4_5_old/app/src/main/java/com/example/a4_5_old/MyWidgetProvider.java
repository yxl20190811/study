package com.example.a4_5_old;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Date;

//�ر�ע��������ÿ���յ���Ϣ��ϵͳ����һ���¶�����˴�����󲻿�����洢״̬
public class MyWidgetProvider  extends AppWidgetProvider
{
	RemoteViews rv;
    Context m_context;

	public MyWidgetProvider()
	{
		Log.d("MyWidgetProvider","============");	
	}
	
	@Override  
	public void onDisabled(Context context) 
	{//��Ϊ���һ��ʵ��
		//ɾ��ʱֹͣ��̨��ʱ����Widgetʱ���Service
		context.stopService(new Intent(context,TimeService.class));
	}
	
	@Override  
	public void onEnabled (Context context) 
	{//��Ϊ��һ��ʵ����򿪷���
		//������̨��ʱ����ʱ���Service
        m_context = context;
        //SendMsg();
        context.startService(new Intent(context,TimeService.class));
	}


	
	//onUpdateΪ���������������ʱ����,���������UI
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) 
	{
		//����RemoteViews
		rv = new RemoteViews(context.getPackageName(), R.layout.wmain);	
		//���������޸������Activity��Intent
        Intent intent = new Intent(context,Sample4_5_Activity.class);
        //����������Intent��PendingIntent
        PendingIntent pendingIntent=PendingIntent.getActivity
        (
        		context, 
        		0, 
        		intent, 
        		PendingIntent.FLAG_UPDATE_CURRENT
        );
        //���ð���Widget���ı����ʹ�PendingIntent
        rv.setOnClickPendingIntent(R.id.TextView11, pendingIntent);
        
        //��ȡSharedPreferences
        SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
        //��SharedPreferences�ж�ȡ�ϴε�����
        String xqStr=sp.getString
        (
        		"xxq",   //��ֵ
        		null    //Ĭ��ֵ
        );
        if(xqStr!=null)
        {//���ϴ�����������������
        	rv.setTextViewText(R.id.TextView11, xqStr);
        }
        
        //����Widget
        appWidgetManager.updateAppWidget(appWidgetIds, rv);


    }
	
	@Override  //onReceiver Ϊ���չ㲥ʱ���ø���UI
    public void onReceive(Context context, Intent intent) 
	{
		super.onReceive(context, intent);
        if (rv == null) 
        {
        	//����RemoteViews
            rv = new RemoteViews(context.getPackageName(), R.layout.wmain);
        }
        if (intent.getAction().equals("wyf.action.update_xq")) 
        {//�յ����Ǹ�������� Action���������
        	//��������
            rv.setTextViewText(R.id.TextView11, intent.getStringExtra("xxq"));
            //��Preferences��д������
	        SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("xq",intent.getStringExtra("xxq"));
            editor.commit();
        }
        else  if (intent.getAction().equals("wyf.action.time_upadte")) 
        {//�յ����Ǹ���ʱ��� Action�����ʱ��
        	rv.setTextViewText(R.id.TextView12,intent.getStringExtra("time"));
        }     
        else  if (intent.getAction().equals("wyf.action.load_xq")) 
        {//�յ��ĸ������鲢��Ӽ�����������鲢��Ӽ���==Ϊ�������������л�������
        	//���������޸������Activity��Intent
            Intent intentTemp = new Intent(context,Sample4_5_Activity.class);
            //����������Intent��PendingIntent
            PendingIntent pendingIntent=PendingIntent.getActivity
            (
            		context, 
            		0, 
            		intentTemp, 
            		PendingIntent.FLAG_UPDATE_CURRENT
            );
            //���ð���Widget���ı����ʹ�PendingIntent
            rv.setOnClickPendingIntent(R.id.TextView11, pendingIntent);
            
            //��ȡSharedPreferences
            SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
            //��SharedPreferences�ж�ȡ�ϴε�����
            String xqStr=sp.getString
            (
            		"xxq",   //��ֵ
            		null    //Ĭ��ֵ
            );
            if(xqStr!=null)
            {//���ϴ�����������������
            	rv.setTextViewText(R.id.TextView11, xqStr);
            }
        }    

        //��������Widget
        AppWidgetManager appWidgetManger = AppWidgetManager.getInstance(context);
        int[] appIds = appWidgetManger.getAppWidgetIds
        (
        	new ComponentName
        	(
                context, 
                MyWidgetProvider.class
            )
        );
        appWidgetManger.updateAppWidget(appIds, rv);
	}

    public  void SendMsg() {
        new Thread(){
            public void run() {
                while (true) {
                    if(null != rv) {
                        Date d=new Date();
                        StringBuilder sb=new StringBuilder();
                        sb.append(d.getYear()+1900);
                        sb.append("年");
                        sb.append((d.getMonth()+1<10)?"0":"");
                        sb.append(d.getMonth()+1);
                        sb.append("月");
                        sb.append((d.getDate()<10)?"0":"");
                        sb.append(d.getDate());
                        sb.append("日  ");
                        sb.append((d.getHours()<10)?"0":"");
                        sb.append(d.getHours());
                        sb.append(":");
                        sb.append((d.getMinutes()<10)?"0":"");
                        sb.append(d.getMinutes());
                        sb.append(":");
                        sb.append((d.getSeconds()<10)?"0":"");
                        sb.append(d.getSeconds());

                        rv.setTextViewText(R.id.TextView12, sb.toString());


                        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(m_context);
                        appWidgetManager.updateAppWidget(
                                new ComponentName(m_context, MyWidgetProvider.class), rv);

                    }
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
