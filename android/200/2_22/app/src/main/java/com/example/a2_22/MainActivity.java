package com.example.a2_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private MyView m_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_v = new MyView(this);
        setContentView(m_v);
    }

    private class MyView extends SurfaceView implements SurfaceHolder.Callback {
        private Paint m_p;
        public boolean m_IsRun = true;
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height){
        }
        public void surfaceDestroyed(SurfaceHolder holder){
            m_IsRun = false;
        }
        public void surfaceCreated(SurfaceHolder holder){
            m_p = new Paint();
            new Thread(){
                public void run(){
                    SurfaceHolder sh = m_v.getHolder();
                    Canvas c = new Canvas();
                    while(m_v.m_IsRun){
                        c =  sh.lockCanvas();
                        m_v.MyDraw(c);
                        sh.unlockCanvasAndPost(c);

                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        public MyView(Context c){
            super(c);
            SurfaceHolder sh = getHolder();
            sh.addCallback(this);
        }

        private int i = 0;
        public void MyDraw(Canvas c){
            ++i;
            switch(i%3){
                case 0:{
                    c.drawColor(Color.WHITE);
                    break;
                }
                case 1:{
                    c.drawColor(Color.BLUE);
                    break;
                }
                case 2:{
                    c.drawColor(Color.GREEN);
                    break;
                }
            }
        }
    }
}
