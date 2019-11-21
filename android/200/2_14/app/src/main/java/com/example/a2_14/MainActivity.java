package com.example.a2_14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);

        Button bt =findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                if(cb1.isChecked()){
                    text += " 唱歌 ";
                }
                if(cb2.isChecked()){
                    text += " 游泳 ";
                }
                if(cb3.isChecked()){
                    text += " 写JAVA程序 ";
                }
                if(text.length() > 1) {
                    et.setText("你选择的是:" + text);
                }
                else
                {
                    et.setText("");
                }

            }
        });
    }

}
