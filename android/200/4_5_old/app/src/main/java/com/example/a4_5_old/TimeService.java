package com.example.a4_5_old;

import java.util.Date;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service 
{
	boolean flag=true;//线程循环标志
	Thread task;//定时刷新时间的任务线程
	
	@Override
	public IBinder onBind(Intent arg0) 
	{
		//因为本例用不到Bind功能，因此直接返回null
		return null;
	}
   
	@Override
	public void onCreate()
	{
		android.os.Debug.waitForDebugger();
		Log.d("hjz","onCreate");
		super.onCreate();
		//创建定时更新时间的任务线程
		task=new Thread()
		{
			public void run()
			{
				while(flag)
				{
					//定时发送Intent更新时间
					Intent intent = new Intent("wyf.action.time_upadte");
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
					
					intent.putExtra("time", sb.toString());
					intent.setComponent(new ComponentName(TimeService.this, MyWidgetProvider.class));
					TimeService.this.sendBroadcast(intent);	
					
					//定时发送Intent更新心情内容，并给心情文本View添加监听器
					//防止切屏后widget不工作了
					intent = new Intent("wyf.action.load_xq");
					TimeService.this.sendBroadcast(intent);

					Log.d("hjz","sendBroadcast");
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
		//启动任务线程
		task.start();
	}
	
	@Override
	public void onDestroy()
	{
		//关闭定时更新时间的任务线程
		flag=false;
	}
}
