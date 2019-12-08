package com.example.a3_10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public ProgressDialog mPd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void OnBltClick(View v){
        showDialog(1);
    }
    public Dialog onCreateDialog(int id){
        mPd = new ProgressDialog(this);
        mPd.setTitle("程序进度");
        mPd.setMax(10000);
        mPd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return mPd;
    }
    public void onPrepareDialog(int id, Dialog dialog)//每次弹出对话框时被回调以动态更新对话框内容的方法
    {
        super.onPrepareDialog(id, dialog);
        mPd.setProgress(0);
        new Thread(){
            public void run() {
                int i = 0;
                while(i < 10000){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++i;
                    mPd.setProgress(i);
                }
            }
        }.start();
    }
}
