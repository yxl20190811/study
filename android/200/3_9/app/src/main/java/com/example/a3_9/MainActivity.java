package com.example.a3_9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void OnButtonClick1(View v){
        new AlertDialog.Builder(this).setTitle("")
            .setTitle("消息提示")
                .setMessage("这时一个AlertDialog!")
               /* .setPositiveButton////显示确定按钮
                (
                        "确定",
                        new DialogInterface.OnClickListener()//为确定按钮设置监听
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //在这里设计当对话按钮单击之后要运行的事件
                                System.out.println();
                            }
                        }
                )*/
                .show();
    }

    public void OnButtonClick2(View v){
        new AlertDialog.Builder(this).setTitle("")
                .setTitle("消息提示")
                .setMessage("这时一个AlertDialog!")
                .setPositiveButton////显示确定按钮
                (
                        "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                )
                .show();
    }


    public void OnButtonClick3(View v){
        new AlertDialog.Builder(this).setTitle("")
                .setTitle("消息提示")
                .setMessage("这时一个AlertDialog!")
                 .setPositiveButton////显示确定按钮
                 (
                         "确定",
                         new DialogInterface.OnClickListener()//为确定按钮设置监听
                         {
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 //在这里设计当对话按钮单击之后要运行的事件
                                 System.out.println();
                             }
                         }
                 )
                .show();
    }
}
