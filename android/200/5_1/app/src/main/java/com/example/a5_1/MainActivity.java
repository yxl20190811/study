package com.example.a5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.textView2);
        final EditText et = findViewById(R.id.editText);
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tv.setText(et.getText());
                Linkify.addLinks(tv, Linkify.WEB_URLS|Linkify.PHONE_NUMBERS|Linkify.EMAIL_ADDRESSES);
                return false;
            }
        });
    }
}
