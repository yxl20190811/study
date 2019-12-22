package com.example.a5_3;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    //数字按钮的ID数组
    int[] numButtonIds=
            {
                    R.id.Button00,R.id.Button01,R.id.Button02,
                    R.id.Button03,R.id.Button04,R.id.Button05,
                    R.id.Button06,R.id.Button07,R.id.Button08,
                    R.id.Button09
            };

    private String m_num;
    private final int PERMISSION_REQUEST_CODE = 1;
    public void call(String num)
    {
        m_num = num;
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CODE);
        }
        else {
            RealCall();
        }

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                //同意申请的权限
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"同意通过",Toast.LENGTH_SHORT).show();
                    RealCall();
                    //拒绝申请的权限
                }else{
                    Toast.makeText(this,"拒绝通过",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
    public void RealCall(){
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse(m_num));
        MainActivity.this.startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为删除按钮添加监听器
        Button bDel=(Button)this.findViewById(R.id.Button_del);
        bDel.setOnClickListener(
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        EditText et=(EditText)findViewById(R.id.EditText01);
                        String num=et.getText().toString();
                        num=(num.length()>1)?num.substring(0,num.length()-1):"";
                        et.setText(num);
                    }
                });


        //为拨号按钮添加监听器
        Button bDial=(Button)this.findViewById(R.id.Button_dial);
        bDial.setOnClickListener(
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        //获取输入的电话号码
                        EditText et=(EditText)findViewById(R.id.EditText01);
                        String num=et.getText().toString();

                        //根据获取的电话号码创建Intent拨号
                        call("tel:" + num);
                        /*
                        Intent dial = new Intent();
                        dial.setAction("android.intent.action.CALL");
                        dial.setData(Uri.parse("tel://"+num));
                        startActivity(dial);
                        */

                    }
                });

        //为退出按钮添加监听器
        Button bCancel=(Button)this.findViewById(R.id.Button_cancel);
        bCancel.setOnClickListener(
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        MainActivity.this.finish();
                    }
                });

        //为0-9数字按钮创建监听器
        View.OnClickListener numListener=new  View.OnClickListener()
        {
            public void onClick(View v)
            {
                Button tempb=(Button)v;
                EditText et=(EditText)findViewById(R.id.EditText01);
                et.append(tempb.getText());
            }
        };

        //为所有数字按钮添加监听器
        for(int id:numButtonIds)
        {
            Button tempb=(Button)this.findViewById(id);
            tempb.setOnClickListener(numListener);
        }

    }
}
