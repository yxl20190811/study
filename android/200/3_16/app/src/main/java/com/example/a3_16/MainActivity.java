package com.example.a3_16;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends ListActivity {

    final int[] drawIds = {R.drawable.app, R.drawable.bpp, R.drawable.cpp};
    final String[] desString ={"苹果", "香蕉", "草莓"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return 3;
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
                iv.setImageResource(drawIds[position]);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new ViewGroup.LayoutParams(200,200));
                iv.setPadding(20,20, 50,50);
                l.addView(iv);

                TextView tv = new TextView(MainActivity.this);
                tv.setText(desString[position]);
                tv.setTextSize(30);
                l.addView(tv);

                return l;
            }
        };

        this.setListAdapter(ba);


    }
    public void onListItemClick(ListView l, View v, int pos, long id){
        String tmp = "你选择了: \"" + desString[pos]  + "\"";
        Toast.makeText(this,tmp, Toast.LENGTH_SHORT).show();
    }
}
