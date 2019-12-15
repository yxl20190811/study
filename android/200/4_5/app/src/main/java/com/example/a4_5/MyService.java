package com.example.a4_5;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Date;

public class MyService extends Service {

    boolean IsRun  = true;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private TimeMsg tm;
    public void onStart(Intent intent, int id)
    {
        if(null == tm) {
            tm = new TimeMsg();
            tm.start(this);
        }
    }

    @Override
    public void onDestroy()
    {
        //�رն�ʱ����ʱ��������߳�
        IsRun=false;
    }

}
