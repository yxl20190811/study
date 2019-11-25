package com.example.a2_25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyView mv = new MyView(this);
        LinearLayout l = findViewById(R.id.LineLayout1);
        l.addView(mv);

        Button blt1 = findViewById(R.id.button);
        blt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.MoveLeft();
            }
        });

        Button blt2 = findViewById(R.id.button2);
        blt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.MoveRight();
            }
        });

        Button blt3 = findViewById(R.id.button3);
        blt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.RotationLeft();
            }
        });

        Button blt4 = findViewById(R.id.button4);
        blt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.RotationRight();
            }
        });

        Button blt5 = findViewById(R.id.button5);
        blt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.Narrow();
            }
        });

        Button blt6 = findViewById(R.id.button6);
        blt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.Engarge();
            }
        });
    }
    private class MyView extends View{

        private Paint m_p;

        private Bitmap m_bitMap, m_bitMapDisplay;

        private int m_posX=0;
        private int m_PosY = 0;
        private int m_width=0;
        private int m_heght = 0;

        private float m_Angle= (float) 0.0;
        private float m_Scale = (float)1.0;

        private Matrix  m_matrix;

        public MyView(Context c){
            super((c));

            m_p = new Paint();
            m_p.setFlags(Paint.ANTI_ALIAS_FLAG);
            m_bitMap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_round);

            m_bitMapDisplay = m_bitMap;
            m_matrix = new Matrix();
            // 获取图片宽高
            m_width = m_bitMap.getWidth();
            m_heght = m_bitMap.getHeight();
        }
        public void MoveLeft(){
            m_posX -= 10;
            invalidate();
        }
        public void MoveRight(){
            m_posX += 10;
            invalidate();
        }

        public void RotationRight(){
            m_Angle -= 5;
            ReRotate();
            invalidate();
        }
        public void RotationLeft(){
            m_Angle += 5;
            ReRotate();
            invalidate();
        }

        public void Narrow(){
            m_Scale += 1;
            ReScale();
            invalidate();
        }
        public void Engarge(){
            m_Scale -= 1;
            ReScale();
            invalidate();
        }
        private void ReScale(){
            m_matrix.reset();

            m_matrix.setScale(m_Scale, m_Scale);

            m_bitMapDisplay = Bitmap.createBitmap(
                    m_bitMap, 0, 0, m_width, m_heght, m_matrix, true
            );
        }
        private void ReRotate(){
            m_matrix.reset();
            m_matrix.setRotate(m_Angle);
            m_bitMapDisplay = Bitmap.createBitmap(
                    m_bitMap, 0, 0, m_width, m_heght, m_matrix, true
            );
        }
        protected void onDraw(Canvas c){
            c.drawBitmap(m_bitMapDisplay, m_posX, m_PosY, m_p);
        }
    };
}
