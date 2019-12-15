package com.example.a4_5_old_ok;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

//�ر�ע��������ÿ���յ���Ϣ��ϵͳ����һ���¶�����˴�����󲻿�����洢״̬
public class MyWidgetProvider  extends AppWidgetProvider
{
	RemoteViews rv;

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
        rv.setOnClickPendingIntent(R.id.TextView01, pendingIntent);          
        
        //��ȡSharedPreferences
        SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
        //��SharedPreferences�ж�ȡ�ϴε�����
        String xqStr=sp.getString
        (
        		"xq",   //��ֵ
        		null    //Ĭ��ֵ
        );
        if(xqStr!=null)
        {//���ϴ�����������������
        	rv.setTextViewText(R.id.TextView01, xqStr);
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
        {
            rv.setTextViewText(R.id.TextView01, intent.getStringExtra("xxq"));            
            //��Preferences��д������
	        SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("xq",intent.getStringExtra("xxq"));
            editor.commit();
        }
        else  if (intent.getAction().equals("wyf.action.time_upadte")) 
        {//�յ����Ǹ���ʱ��� Action�����ʱ��
        	rv.setTextViewText(R.id.TextView02,intent.getStringExtra("time"));
        }     
        else  if (intent.getAction().equals("wyf.action.load_xq")) 
        {
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
            rv.setOnClickPendingIntent(R.id.TextView01, pendingIntent);  
            
            //��ȡSharedPreferences
            SharedPreferences sp=context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
            //��SharedPreferences�ж�ȡ�ϴε�����
            String xqStr=sp.getString
            (
            		"xq",   //��ֵ
            		null    //Ĭ��ֵ
            );
            if(xqStr!=null)
            {//���ϴ�����������������
            	rv.setTextViewText(R.id.TextView01, xqStr);
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
}
