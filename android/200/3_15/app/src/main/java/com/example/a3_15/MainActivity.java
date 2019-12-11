package com.example.a3_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.textView);
        new Thread(){
            public void run() {
                while (true) {
                    long t = System.currentTimeMillis();
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(t);
                    String tmp = c.get(Calendar.HOUR) + "点" + c.get(Calendar.MINUTE) + "分" + c.get(Calendar.SECOND) + "秒";
                    tv.setText(tmp);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
