package com.example.a5_8;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
    final static int WRAP_CONTENT=-2;//表示WRAP_CONTENT的常量
    //所有资源图片（足球、篮球、排球）id的数组
    int[] drawableIds={R.drawable.football,R.drawable.basketball,R.drawable.volleyball};
    //所有资源字符串（足球、篮球、排球）id的数组
    int[] msgIds={R.string.zq,R.string.lq,R.string.pq};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Spinner
        Spinner sp=(Spinner)this.findViewById(R.id.Spinner01);

        //为Spinner准备内容适配器
        BaseAdapter ba=new BaseAdapter()
        {
            public int getCount() {
                return 3;//总共三个选项
            }

            public Object getItem(int arg0) { return null; }

            public long getItemId(int arg0) { return 0; }

            public View getView(int arg0, View arg1, ViewGroup arg2) {

                //初始化LinearLayout
                LinearLayout ll=new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向

                //初始化ImageView
                ImageView  ii=new ImageView(MainActivity.this);
                ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//设置图片
                ll.addView(ii);//添加到LinearLayout中

                //初始化TextView
                TextView tv=new TextView(MainActivity.this);
                tv.setText(" "+getResources().getText(msgIds[arg0]));//设置内容
                tv.setTextSize(24);//设置字体大小
                tv.setTextColor(R.color.black);//设置字体颜色
                ll.addView(tv); //添加到LinearLayout中

                return ll;
            }
        };

        sp.setAdapter(ba);//为Spinner设置内容适配器

        //设置选项选中的监听器
        sp.setOnItemSelectedListener(
                new OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {//重写选项被选中事件的处理方法
                        TextView tv=(TextView)findViewById(R.id.TextView01);//获取主界面TextView
                        LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
                        TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView
                        StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
                        sb.append(getResources().getText(R.string.ys));
                        sb.append(":");
                        sb.append(tvn.getText());
                        tv.setText(sb.toString());//信息设置进主界面TextView
                    }

                    public void onNothingSelected(AdapterView<?> arg0) { }
                }
        );

    }
}