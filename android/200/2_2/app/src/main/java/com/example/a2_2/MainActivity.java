package com.example.a2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String str1="", curText="";
    TextView  tx1, tx2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx1 = (TextView)findViewById(R.id.textView1);
        tx2 = (TextView)findViewById(R.id.textView2);

        tx1.setText("");
        tx2.setText("");
    }
    public void OnButtonClick(View v){
        Button tmp = (Button)v;
        switch(v.getId()) {
            case R.id.buttonClear: {
                str1 = "";
                curText = "";
                break;
            }
            case R.id.buttonAdd:
            case R.id.buttonDec:
            case R.id.buttonPlus:
            case R.id.buttonDiv:{
                if(curText.length() < 1){
                    return;
                }
                curText = "";
                str1= str1 + tmp.getText();
                break;
            }
            case R.id.button0:
            {
                if(curText.length() < 1){
                    return;
                }
            }
            default:{
                str1= str1 + tmp.getText();
                curText+=tmp.getText();
            }
        }
        tx1.setText(str1);
        tx2.setText(cal(str1));
    }
    private String cal(String text){
        return "";
    }
}
