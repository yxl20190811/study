package com.example.a2_24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Paint m_p;
    private class MyView extends View {
        public MyView(Context c){
            super(c);
            m_p = new Paint();
        }
        protected void onDraw(Canvas c){
            c.drawColor(Color.WHITE);

            m_p.setColor(Color.GREEN);
            m_p.setTextSize(60);
            c.drawText("绘制集合图形",30, 50, m_p );

            m_p.setColor(Color.BLACK);
            m_p.setStrokeWidth(4);
            c.drawLine(0,0, 800,800, m_p);

            m_p.setColor(Color.RED);
            m_p.setStrokeWidth(10);
            c.drawCircle(200, 500,150, m_p);

            m_p.setColor(Color.BLUE);
            m_p.setStrokeWidth(1);
            c.drawRect(300, 300, 100, 200, m_p);

            Path p = new Path();
            p.moveTo(150, 150);
            p.lineTo(150+45, 150-50);
            p.lineTo(150+30, 150-50);
            p.lineTo(150+100, 400-100);
            p.close();

            c.drawPath(p, m_p);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
