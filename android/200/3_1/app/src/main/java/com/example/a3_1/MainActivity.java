package com.example.a3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView m_OldVt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.textView1);
        final ListView lv = findViewById(R.id.ListView1);

        lv.setAdapter(new MyBaseAdapter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout ll=(LinearLayout)view;//获取当前选中选项对应的LinearLayout
                TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView
                String  tmp = "你选择了: " + tvn.getText().toString();
                tv.setText(tmp.split("\\n")[0]);
                tvn.setTextColor(Color.RED);
                if(null != MainActivity.this.m_OldVt ){
                    MainActivity.this.m_OldVt.setTextColor(Color.BLACK);
                }
                MainActivity.this.m_OldVt = tvn;
                return;
            }
        });
    }

    public class MyBaseAdapter extends BaseAdapter{

        int[] drawableIds=
                {R.drawable.andy,R.drawable.bill,R.drawable.edgar,R.drawable.torvalds,R.drawable.turing};
        //所有资源字符串（andy、bill、edgar、torvalds、turing）id的数组
        int[] msgIds={R.string.andy,R.string.bill,R.string.edgar,R.string.torvalds,R.string.turing};


        int len = 0;
        public MyBaseAdapter(){
            len = drawableIds.length;
        }

        @Override
        public int getCount() {
            return len;
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

            LinearLayout  l = new LinearLayout(MainActivity.this);
            l.setOrientation(LinearLayout.HORIZONTAL);
            l.setPadding(10,3,5,10);
            //增加图片
            ImageView iv = new ImageView(MainActivity.this);
            iv.setImageResource(drawableIds[position]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new Gallery.LayoutParams(230, 230));
            l.addView(iv);
            //增加文字描述
            TextView tv = new TextView(MainActivity.this);
            tv.setPadding(100,5,5,5);
            //tv.setBackgroundColor(Color.GRAY);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(30);
            tv.setText(msgIds[position]);
            l.addView(tv);
            return l;
        }


    }
}
