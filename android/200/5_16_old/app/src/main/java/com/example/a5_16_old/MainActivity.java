package com.example.a5_16_old;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {
    private ListView lv;
    private TelephonyManager tm;
    private ContentResolver cr;
    private List<String> list=new ArrayList<String>();
    private List<String> name=new ArrayList<String>();
    private Button bCheck;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] tmp = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.MODIFY_AUDIO_SETTINGS
        };

        ActivityCompat.requestPermissions(MainActivity.this, tmp,1);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    on();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void on(){
        lv=(ListView)this.findViewById(R.id.ListView01);
        tm=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        cr=MainActivity.this.getContentResolver();
        bCheck=(Button)this.findViewById(R.id.Button01);
        String str=null;//记录cr获取的信息
        name.add("手机号码：");
        name.add("电信网络国别：");
        name.add("电信公司代码：");
        name.add("电信公司名称：");
        name.add("SIM码：");
        name.add("手机通信类型：");
        name.add("手机网络类型 ：");
        name.add("手机是否漫游：");
        name.add("蓝牙状态：");
        name.add("WIFI状态：");
        if(tm.getLine1Number()!=null)//手机号码
        {
            list.add(tm.getLine1Number());
        }else
        {
            list.add("无法取得您的电话号码");
        }

        if(!tm.getNetworkCountryIso().equals(""))//电信网络国别
        {
            list.add(tm.getNetworkCountryIso());
        }else
        {
            list.add("无法取得您的电信网络国别");
        }

        if(!tm.getNetworkOperator().equals(""))//电信公司代码
        {
            list.add(tm.getNetworkOperator());
        }else
        {
            list.add("无法获取电信公司代码");
        }

        if(!tm.getNetworkOperatorName().equals(""))//电信公司名称
        {
            list.add(tm.getNetworkOperatorName());
        }else
        {
            list.add("无法获取电信公司名称");
        }



        if(tm.getSimSerialNumber()!=null)//手机SIM码
        {
            list.add(tm.getSimSerialNumber());
        }else
        {
            list.add("无法获取手机SIM码");
        }


        if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_GSM)//手机行动通信类型
        {
            list.add("GSM");
        }
//        else if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_CDMA)  //需用真机演示
//        {
//        	list.add("CDMA");
//        }
        else
        {
            list.add("无法获取手机通信类型");
        }

        if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_EDGE)//获取手机网络类型
        {
            list.add("EDGE");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_GPRS)
        {
            list.add("GPRS");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_UMTS)
        {
            list.add("UMTS");
        }
//        else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_HSDPA) //需用真机演示
//        {
//        	list.add("HSDPA");
//        }
        else
        {
            list.add("无法获取手机网络类型");
        }

        if(tm.isNetworkRoaming())//手机是否漫游
        {
            list.add("手机漫游中");
        }else
        {
            list.add("手机无漫游");
        }
        str=android.provider.Settings.System.getString(
                cr,android.provider.Settings.System.BLUETOOTH_ON
        );
        if(str.equals("1"))
        {
            list.add("蓝牙已打开");
        }else
        {
            list.add("蓝牙未打开");
        }
        str=android.provider.Settings.System.getString(cr, android.provider.Settings.System.WIFI_ON);
        if(str.equals("1"))
        {
            list.add("WIFI已打开");
        }else
        {
            list.add("WIFI未打开");
        }
        bCheck.setOnClickListener
                (
                        new OnClickListener()
                        {
                            public void onClick(View v) {
                                BaseAdapter ba=new BaseAdapter()//创建适配器
                                {

                                    public int getCount() {
                                        return list.size();
                                    }

                                    public Object getItem(int position) {
                                        return null;
                                    }

                                    public long getItemId(int position) {
                                        return 0;
                                    }

                                    public View getView(int arg0, View arg1, ViewGroup arg2) {
                                        LinearLayout ll=new LinearLayout(MainActivity.this);
                                        ll.setOrientation(LinearLayout.HORIZONTAL);
                                        ll.setPadding(5, 5, 5, 5);

                                        TextView tv=new TextView(MainActivity.this);//初始化TextView
                                        tv.setTextColor(Color.BLACK);//设置字体颜色
                                        tv.setPadding(5,5,5,5);
                                        tv.setText(name.get(arg0));//添加任务名字
                                        tv.setGravity(Gravity.LEFT);//左对齐
                                        tv.setTextSize(18);//字体大小
                                        ll.addView(tv);//LinearLayout添加TextView

                                        TextView tvv=new TextView(MainActivity.this);//初始化TextView
                                        tvv.setTextColor(Color.BLACK);//设置字体颜色
                                        tvv.setPadding(5,5,5,5);
                                        tvv.setText(list.get(arg0));//添加任务名字
                                        tvv.setGravity(Gravity.LEFT);//左对齐
                                        tvv.setTextSize(18);//字体大小
                                        ll.addView(tvv);//LinearLayout添加TextView
                                        return ll;
                                    }
                                };
                                lv.setAdapter(ba);//设置适配器
                                lv.setOnItemClickListener//设置选中菜单的监听器
                                        (
                                                new OnItemClickListener()
                                                {
                                                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                                                            int arg2, long arg3) {
                                                        Toast.makeText(MainActivity.this, name.get(arg2)+""+list.get(arg2), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                        );
                            }

                        }
                );



    }
}