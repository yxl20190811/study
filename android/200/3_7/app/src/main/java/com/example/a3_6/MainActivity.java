package com.example.a3_6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public Bitmap m_bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv =findViewById(R.id.imageView);

        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);

        DisplayMetrics  dm = new DisplayMetrics();
        m_bm = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        iv.setImageBitmap(m_bm);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = MainActivity.this.m_bm;
                int width = bm.getWidth(); //图片宽度
                int height = bm.getHeight();//图片高度
                Matrix matrix = new Matrix(); //创建矩阵
                matrix.postRotate(90);//图片等比例缩小为原来的fblRatio倍
                MainActivity.this.m_bm =  Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);//声明位图
                iv.setImageBitmap(MainActivity.this.m_bm);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = MainActivity.this.m_bm;
                int width = bm.getWidth(); //图片宽度
                int height = bm.getHeight();//图片高度
                Matrix matrix = new Matrix(); //创建矩阵
                matrix.postRotate(-90);//图片等比例缩小为原来的fblRatio倍
                MainActivity.this.m_bm =  Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);//声明位图
                iv.setImageBitmap(MainActivity.this.m_bm);
            }
        });
    }
}
