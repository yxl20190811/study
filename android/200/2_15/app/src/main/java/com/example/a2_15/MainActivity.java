package com.example.a2_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    ToggleButton tb;
    CheckBox cb;
    RadioButton rbOn, rbOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageView);
        rbOn = findViewById(R.id.radioButton);
        rbOff = findViewById(R.id.radioButton2);

        tb = findViewById(R.id.toggleButton);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.SetState(isChecked);
            }
        });

        RadioGroup rg = findViewById(R.id.RadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                MainActivity.this.SetState(checkedId == R.id.radioButton);
            }
        });

        cb = findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.SetState(isChecked);
            }
        });
    }
    void SetState(boolean isOnOff){
        if(isOnOff) {
            iv.setImageResource(R.drawable.bulb_on);
            cb.setText("开灯");

        }
        else{
            iv.setImageResource(R.drawable.bulb_off);
            cb.setText("关灯");
        }
        rbOn.setChecked(isOnOff);
        rbOff.setChecked(!isOnOff);

        cb.setChecked(isOnOff);
        tb.setChecked(isOnOff);

    }
    private boolean m_IsOnOff = true;

    public void OnBtClick(View v){
        final Button bt = findViewById(R.id.button);

        if(m_IsOnOff) {
            bt.setBackgroundResource(R.drawable.bulb_on);
        }
        else{
            bt.setBackgroundResource(R.drawable.bulb_off);
        }
        m_IsOnOff = !m_IsOnOff;
    }
}
