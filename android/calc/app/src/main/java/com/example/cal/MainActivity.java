package com.example.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView m_TextView;
    private String m_result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_TextView = this.findViewById(R.id.textView);

    }
    public void OnButtonClick(View v){
        switch(v.getId()){
            case R.id.btn_1:
                m_result += "1";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_2:
                m_result += "2";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_3:
                m_result += "3";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_4:
                m_result += "4";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_5:
                m_result += "5";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_6:
                m_result += "6";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_7:
                m_result += "7";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_8:
                m_result += "8";
                m_TextView.setText(m_result);
                break;
        }

        switch(v.getId()){
            case R.id.btn_9:
                m_result += "9";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_0:
                m_result += "0";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_ADD:
                m_result += "+";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_DEC:
                m_result += "-";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_MUL:
                m_result += "*";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_DEV:
                m_result += "/";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_DEL:
                int len = m_result.length();
                if(len > 1) {
                    m_result = m_result.substring(0, len - 1);
                    m_TextView.setText(m_result);
                }
                break;
        }
        switch(v.getId()){
            case R.id.btn_EQ:
                m_TextView.setText(calc(m_result));
                m_result = "";
                break;
        }
        switch(v.getId()){
            case R.id.btn_C:
                m_result = "";
                m_TextView.setText(m_result);
                break;
        }
        switch(v.getId()){
            case R.id.btn_DOT:
                m_result += ".";
                m_TextView.setText(m_result);
                break;
        }

    }
    private String calc(String text){
        return text;
    }
}
