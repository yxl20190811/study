package com.example.a2_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity
{
    final int PROCESS_DLG_ID = 1;
    final int PROCESS_DLG_MSG = 10;
    private ProgressDialog dg;
    private Handler hd;

    private ProgressBar pb;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progressBar2);


        hd = new Handler(){
            public void handleMessage(Message msg) {
                if(PROCESS_DLG_MSG == msg.what){
                    dg.incrementProgressBy(1);
                    pb.incrementProgressBy(1);
                }
            }
        };
    }

    public void OnClick(View v){
        showDialog(PROCESS_DLG_ID);

        new Thread(){
            public void run() {
                while(true){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hd.sendEmptyMessage(PROCESS_DLG_MSG);
                }
            }
        }.start();
    }

    public Dialog onCreateDialog(int id) {
        if(PROCESS_DLG_ID == id){
            if(null == dg){
                dg = new ProgressDialog(MainActivity.this);
                dg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dg.setMax(100);
                dg.setTitle("我的进度");
                dg.setCancelable(true);
            }
        }
        return dg;
    }
    public void onPrepareDialog(int id, Dialog dialog){
        if(PROCESS_DLG_ID == id) {
            dg.setProgress(0);
        }
    }
}
