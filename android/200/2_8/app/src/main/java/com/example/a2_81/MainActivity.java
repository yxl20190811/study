package com.example.a2_81;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv4 = findViewById(R.id.textView4);
        tv4.setBackgroundColor(Color.BLUE);

        TextView tv5 = findViewById(R.id.textView5);
        tv5.setTextColor(Color.RED);
    }
}
