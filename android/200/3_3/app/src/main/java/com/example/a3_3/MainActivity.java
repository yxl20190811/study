package com.example.a3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.textView);
        final Spinner s = findViewById(R.id.spinner);

        BaseAdapter ba = new BaseAdapter() {
            int DrawIds[]={R.drawable.andy, R.drawable.bill, R.drawable.edgar, R.drawable.torvalds, R.drawable.turing};
            String DesArray[]={"andy", "bill", "edgar", "torvalds", "turing"};
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout l = new LinearLayout(MainActivity.this);
                l.setOrientation(LinearLayout.HORIZONTAL);

                ImageView iv = new ImageView(MainActivity.this);
                iv.setLayoutParams(new LinearLayout.LayoutParams(100,
                        100));
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(DrawIds[position]);
                l.addView(iv);

                TextView tv = new TextView(MainActivity.this);
                tv.setText(DesArray[position]);
                tv.setTextSize(30);
                tv.setTextColor(Color.BLUE);
                l.addView(tv);
                return l;
            }
        };
        s.setAdapter(ba);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)(((LinearLayout)view).getChildAt(1));
                tv.setText("你选择的是: "+v.getText().toString());
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
