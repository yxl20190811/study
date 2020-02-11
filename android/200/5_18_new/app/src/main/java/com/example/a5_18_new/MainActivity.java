package com.example.a5_18_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int xPos = 0;
    int yPos = 0;
    int width = 200;
    int heght = 200;
    boolean isChoosed = false;

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageView);
        iv.setLayoutParams
                (
                        new AbsoluteLayout.LayoutParams(200, 200, xPos, yPos)
                );
    }
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX()-100;
        float y = e.getY()-300;
        switch(e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (
                        x > xPos - width
                                && x < xPos + width
                                && y > yPos - heght
                                && y < yPos + heght
                ) {
                    isChoosed = true;
                    iv.setBackground(getDrawable(R.drawable.football));
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if(!isChoosed){return true;}
                xPos = (int)x;
                yPos = (int)y;
                iv.setLayoutParams
                        (
                                new AbsoluteLayout.LayoutParams(200, 200, xPos, yPos)
                        );
                break;
            }
            case MotionEvent.ACTION_UP:{
                if(!isChoosed){return true;}
                isChoosed = false;
                iv.setBackground(getDrawable(R.drawable.basketball));
                break;
            }

        }
        return true;
    }

}
