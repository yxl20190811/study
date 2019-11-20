package com.example.a2_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText  m_et, m_et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_et = findViewById(R.id.editText);
        m_et2 = findViewById(R.id.editText2);

        RadioGroup rg = findViewById(R.id.RadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bt = findViewById(checkedId);
                String  s = "RadioGroup choosed: " +  bt.getText().toString();
                m_et2.setText(s);
            }
        });
    }
    public void OnFemale(View v){
       m_et.setText("你选择的是：女");
    }
    public void OnMale(View v){
        m_et.setText("你选择的是：男");
    }

}
