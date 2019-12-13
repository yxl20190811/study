package com.example.a4_4;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Handler h = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.textView);

        final Handler h1 = new Handler() {
            public void handleMessage(Message m) {
                Bundle b = m.getData();
                int value = b.getInt("value");
                String x ="";
                x = x + value;
                tv.setText(x);
            }
        };

        new Thread(){
            public void run(){
                Looper.prepare();
                h =  new Handler(){
                    public void handleMessage(Message m){
                      Bundle b = m.getData();
                      String msg = b.getString("msg");
                      Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                };
                Looper.loop();
            }
        }.start();

        new Thread(){
            public void run(){
                int i = 0;
                while(null == h){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (true)
                {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++i;
                    {
                        String msg = "当前计数：[" + i + "]";

                        Bundle b = new Bundle();
                        b.putString("msg", msg);


                        Message s = new Message();
                        s.what = 1;
                        s.setData(b);

                        h.sendMessage(s);
                    }

                    {
                        Bundle b = new Bundle();

                        b.putInt("value", i);

                        Message s = new Message();
                        s.what = 1;
                        s.setData(b);

                        h1.sendMessage(s);
                    }
                }
            }
        }.start();


    }
}
