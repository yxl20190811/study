package com.example.a4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        String tmp = "屏幕高度:[" + dm.heightPixels + "],宽度:[" + dm.widthPixels + "]";

        TextView tv = findViewById(R.id.textView);
        tv.setText(tmp);

    }
}
