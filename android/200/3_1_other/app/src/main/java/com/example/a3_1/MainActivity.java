package com.example.a3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
    //所有资源图片（andy、bill、edgar、torvalds、turing）id的数组
    int[] drawableIds=
            {R.drawable.andy,R.drawable.bill,R.drawable.edgar,R.drawable.torvalds,R.drawable.turing};
    //所有资源字符串（andy、bill、edgar、torvalds、turing）id的数组
    int[] msgIds={R.string.andy,R.string.bill,R.string.edgar,R.string.torvalds,R.string.turing};
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//切屏到主界面


        //初始化ListView
        ListView lv=(ListView)this.findViewById(R.id.ListView01);
        //为ListView设置适配器
        BaseAdapter ba=new BaseAdapter()
        {
            public int getCount()
            {
                return 5;
            }
            public Object getItem(int position)
            {
                return null;
            }
            public long getItemId(int position)
            {
                return 0;
            }
            public View getView(int arg0, View arg1, ViewGroup arg2)
            {

                //动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
                //中包含一个ImageView及一个TextView构成

                //初始化LinearLayout
                LinearLayout ll=new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向
                ll.setPadding(5,5,5,5);//设置四周留白
                //初始化ImageView
                ImageView  ii=new ImageView(MainActivity.this);
                ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//设置图片
                ii.setScaleType(ImageView.ScaleType.FIT_XY);
                ii.setLayoutParams(new Gallery.LayoutParams(300,300));
                ll.addView(ii);//添加到LinearLayout中
                //初始化TextView
                TextView tv=new TextView(MainActivity.this);
                tv.setText(getResources().getText(msgIds[arg0]));//设置内容
                tv.setTextSize(24);//设置字体大小
                tv.setTextColor(MainActivity.this.getResources().getColor(R.color.white));//设置字体颜色
                tv.setPadding(5,5,5,5);//设置四周留白
                tv.setGravity(Gravity.LEFT);
                ll.addView(tv);//添加到LinearLayout中
                return ll;
            }
        };
        lv.setAdapter(ba);//为ListView设置内容适配器

        //设置选项选中的监听器
        lv.setOnItemSelectedListener
                (
                        new OnItemSelectedListener()
                        {
                            //@Override
                            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                       int arg2, long arg3) {//重写选项被选中事件的处理方法
                                TextView tv=(TextView)findViewById(R.id.TextView01);//获取主界面TextView
                                LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
                                TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView
                                StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
                                sb.append(getResources().getText(R.string.ys));//添加"你选择了字符串"
                                sb.append(":");//添加":"
                                sb.append(tvn.getText());//添加TextView的值
                                String stemp=sb.toString();	//将StringBuilder转换成String类型
                                tv.setText(stemp.split("\\n")[0]);//信息设置进主界面TextView
                            }
                            //@Override
                            public void onNothingSelected(AdapterView<?> arg0) { }
                        }
                );

        //设置选项被单击的监听器
        lv.setOnItemClickListener(
                new OnItemClickListener()
                {
                    //@Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {//重写选项被单击事件的处理方法
                        TextView tv=(TextView)findViewById(R.id.TextView01);//获取主界面TextView
                        LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
                        TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView
                        StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
                        sb.append(getResources().getText(R.string.ys));//添加"你选择了字符串"
                        sb.append(":");//添加":"
                        sb.append(tvn.getText().toString());//添加TextView的值
                        String stemp=sb.toString();//将StringBuilder转换成String类型
                        tv.setText(stemp.split("\\n")[0]);//信息设置进主界面TextView
                    }
                }
        );

    }
}