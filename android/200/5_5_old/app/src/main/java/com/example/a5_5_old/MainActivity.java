package com.example.a5_5_old;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText etReceiver;//收件人
    EditText etSender;//发件人
    EditText etTheme;//主题
    EditText etMessage;//内容
    Button bSend;//发送按钮
    String strReceiver;//收件人信息
    String strSender;//发件人信息
    String strTheme;//主题信息
    String strMessage;//内容信息

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etReceiver=(EditText)this.findViewById(R.id.EditText01);//获取对象
        etSender=(EditText)this.findViewById(R.id.EditText04);//获取对象
        etTheme=(EditText)this.findViewById(R.id.EditText02);//获取对象
        etMessage=(EditText)this.findViewById(R.id.EditText03);//获取对象
        bSend=(Button)this.findViewById(R.id.Button01);//发送按钮


        bSend.setOnClickListener(
            new OnClickListener() {
                public void onClick(View v) {
                    strReceiver=etReceiver.getText().toString().trim();//获取收件人
                    strSender=etSender.getText().toString().trim();//获取发件人
                    strTheme=etTheme.getText().toString().trim();//获取主题
                    strMessage=etMessage.getText().toString().trim();//获取内容
                    String parent="^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
                    if(!strReceiver.matches(parent))//查看收件人地址是否符合格式
                    {
                        Toast.makeText(MainActivity.this, "收件人地址格式错误", Toast.LENGTH_SHORT).show();
                    }else if(!strSender.matches(parent))//查看发件人地址是否符合格式
                    {
                        Toast.makeText(MainActivity.this, "发件人地址格式错误", Toast.LENGTH_SHORT).show();
                    }else//若都符合格式，则发送邮件
                    {
                        Intent intent=new Intent(android.content.Intent.ACTION_SEND);//发送邮件功能
                        intent.setType("plain/text");
                        intent.putExtra(android.content.Intent.EXTRA_EMAIL, strReceiver);
                        intent.putExtra(android.content.Intent.EXTRA_CC, strSender);///
                        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, strTheme);
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, strMessage);
                        startActivity(Intent.createChooser(intent, getResources().getString(R.string.start)));
                    }
                }
            }
        );
    }
}