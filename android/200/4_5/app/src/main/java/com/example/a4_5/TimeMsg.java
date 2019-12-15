package com.example.a4_5;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Date;

public class TimeMsg extends Thread {
    public String GetTime() {
        Date d = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(d.getYear() + 1900);
        sb.append("年");
        sb.append((d.getMonth() + 1 < 10) ? "0" : "");
        sb.append(d.getMonth() + 1);
        sb.append("月");
        sb.append((d.getDate() < 10) ? "0" : "");
        sb.append(d.getDate());
        sb.append("日 ");
        sb.append((d.getHours() < 10) ? "0" : "");
        sb.append(d.getHours());
        sb.append(":");
        sb.append((d.getMinutes() < 10) ? "0" : "");
        sb.append(d.getMinutes());
        sb.append(":");
        sb.append((d.getSeconds() < 10) ? "0" : "");
        sb.append(d.getSeconds());

        return sb.toString();
    }
    private Context m_context;
    public void run () {
        SharedPreferences sp = m_context.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
        while (null != m_context) {
            String time = GetTime();
            Intent intent = new Intent();
            intent.setAction("wyf.action.time_xq");
            intent.setComponent(new ComponentName(m_context, XqWidgetProvider.class));
            intent.putExtra("xxq", time);
            String tx = sp.getString("xq", "今天好心情");
            intent.putExtra("xxq1", tx);

            m_context.sendBroadcast(intent);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start(Context context){
        m_context = context;
        super.start();
    }
    public void finalize(){
        m_context = null;
    }
}
