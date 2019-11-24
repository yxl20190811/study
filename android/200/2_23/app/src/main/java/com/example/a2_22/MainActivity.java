package com.example.a2_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyView mv = new MyView(this);
        setContentView(mv);
        super.onCreate(savedInstanceState);
    }

    class MyView extends View {
        Paint p;
        public MyView(Context context){
            super(context);
            p = new Paint();
        }


        public void onDraw(Canvas c) {
            //protected void OnDraw(Canvas c){

            c.drawColor(Color.BLACK);
            p.setColor(Color.GREEN);
            c.drawText("当前屏幕",30, 30, p);

            p.setColor(Color.RED);
            p.setTextSize(38);
            c.drawText("当前屏幕",30+30, 30+130, p);

            p.setColor(Color.WHITE);
            p.setTextSize(60);
            p.setFlags(Paint.ANTI_ALIAS_FLAG);
            c.drawText("消除字体锯齿",30+30, 30+230, p);


        }
    };

}
