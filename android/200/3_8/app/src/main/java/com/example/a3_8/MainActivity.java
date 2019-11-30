package com.example.a3_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private class My  implements View.OnClickListener{
        public void onClick(View v){}
        public int m_index;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final ImageView giv = findViewById(R.id.imageView);
        int width = getWindowManager().getDefaultDisplay().getWidth();

        LinearLayout Grallery = findViewById(R.id.Grallery1);
        final int[] drawIds={R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
        };
        for(int i = 0; i < drawIds.length; ++i){

            ImageView iv = new ImageView(this);
            iv.setImageResource(drawIds[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new ViewGroup.LayoutParams(300,300));

            My tmp = new My(){
                @Override
                public void onClick(View v) {
                    giv.setImageResource(drawIds[m_index]);
                }
            };
            tmp.m_index = i;
            iv.setOnClickListener(tmp);

            Grallery.addView(iv);
        }

    }
}
