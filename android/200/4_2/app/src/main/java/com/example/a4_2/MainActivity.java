package com.example.a4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button b = findViewById(R.id.button);
        b.setOnTouchListener(new View.OnTouchListener() {
            int xSpan, ySpan;
            int X_MODIFY = 6;
            int Y_MODIFY = 200;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        xSpan = (int)event.getX();
                        ySpan = (int)event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        b.setLayoutParams(new AbsoluteLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                                (int)event.getRawX()-xSpan- X_MODIFY, (int)event.getRawY()-ySpan-Y_MODIFY));
                        break;
                }
                return true;
            }
        });
    }
}
