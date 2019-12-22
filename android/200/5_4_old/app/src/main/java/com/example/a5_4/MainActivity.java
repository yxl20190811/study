package com.example.a5_4;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.widget.Toast.LENGTH_LONG;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bdial=(Button)this.findViewById(R.id.Button01);
        bdial.setOnClickListener(// 为拨号按钮添加监听器
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        //获取输入的电话号码
                        EditText etTel=(EditText)findViewById(R.id.EditText02);
                        String telStr=etTel.getText().toString();

                        //获取输入的短信内容
                        EditText etSms=(EditText)findViewById(R.id.EditText01);
                        String smsStr=etSms.getText().toString();
                        //判断号码字符串是否合法
                        if(PhoneNumberUtils.isGlobalPhoneNumber(telStr))
                        {//合法则发送短信
                            v.setEnabled(false);//短信发送完成前将发送按钮设置为不可用
                            sendSMS(telStr,smsStr,v);
                        }
                        else
                        {//不合法则提示
                            Toast.makeText(
                                    MainActivity.this, //上下文
                                    "电话号码不符合格式！！！", //提示内容
                                    LENGTH_LONG						//信息显示时间
                            ).show();
                        }
                    }
                });
    }

    //自己开发的直接发送短信的方法
    private String m_telNo, m_smsStr;
    private View m_v;
    private final int PERMISSION_REQUEST_CODE = 1;
    private void sendSMS(String telNo,String smsStr,View v)
    {
        m_telNo = telNo;
        m_smsStr = smsStr;
        m_v = v;
        v.setEnabled(false);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},PERMISSION_REQUEST_CODE);
        }
        else {
            ReadSendSMS();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                //同意申请的权限
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"同意通过",Toast.LENGTH_SHORT).show();
                    ReadSendSMS();
                    //拒绝申请的权限
                }else{
                    Toast.makeText(this,"拒绝通过",Toast.LENGTH_SHORT).show();
                    m_v.setEnabled(true);
                }
                break;
            default:
                break;
        }

    }

    private void ReadSendSMS(){
        PendingIntent pi=//创建PendingIntent对象
                PendingIntent.getActivity(this, 0, new Intent(this,MainActivity.class), 0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(m_telNo, null, m_smsStr, pi, null);//收件人，发送人，正文，发送服务，送达服务，其中收件人和正文不可为空
        //短信发送成功给予提示
        Toast.makeText(
                MainActivity.this, //上下文
                "恭喜你，短信发送成功！", //提示内容
                LENGTH_LONG						//信息显示时间
        ).show();
        m_v.setEnabled(true);//短信发送完成后恢复发送按钮的可用状态
    }
}