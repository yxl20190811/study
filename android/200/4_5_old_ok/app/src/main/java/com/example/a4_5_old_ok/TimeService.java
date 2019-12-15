package com.example.a4_5_old_ok;

import java.util.Date;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

public class TimeService extends Service 
{
	boolean flag=true;//�߳�ѭ����־
	Thread task;//��ʱˢ��ʱ��������߳�
	
	@Override
	public IBinder onBind(Intent arg0) 
	{
		//��Ϊ�����ò���Bind���ܣ����ֱ�ӷ���null
		return null;
	}
   
	@Override
	public void onCreate()
	{
		super.onCreate();
		//������ʱ����ʱ��������߳�
		task=new Thread()
		{
			public void run()
			{
				while(flag)
				{
					//��ʱ����Intent����ʱ��
					Intent intent = new Intent("wyf.action.time_upadte");
					Date d=new Date();
					StringBuilder sb=new StringBuilder();
					sb.append(d.getYear()+1900);
					sb.append("��");
					sb.append((d.getMonth()+1<10)?"0":"");
					sb.append(d.getMonth()+1);
					sb.append("��");
					sb.append((d.getDate()<10)?"0":"");
					sb.append(d.getDate());
					sb.append("��  ");
					sb.append((d.getHours()<10)?"0":"");
					sb.append(d.getHours());
					sb.append(":");
					sb.append((d.getMinutes()<10)?"0":"");
					sb.append(d.getMinutes());
					sb.append(":");
					sb.append((d.getSeconds()<10)?"0":"");
					sb.append(d.getSeconds());
					
					intent.putExtra("time", sb.toString());
					intent.setComponent(new ComponentName(TimeService.this, MyWidgetProvider.class));
					TimeService.this.sendBroadcast(intent);	
					
					//��ʱ����Intent�����������ݣ����������ı�View��Ӽ�����
					//��ֹ������widget��������
					intent = new Intent("wyf.action.load_xq");
					intent.setComponent(new ComponentName(TimeService.this, MyWidgetProvider.class));
					TimeService.this.sendBroadcast(intent);	
					
					try 
					{
						Thread.sleep(500);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	@Override
	public void onStart(Intent intent, int id)
	{		
		//���������߳�
		task.start();
	}
	
	@Override
	public void onDestroy()
	{
		//�رն�ʱ����ʱ��������߳�
		flag=false;
	}
}
