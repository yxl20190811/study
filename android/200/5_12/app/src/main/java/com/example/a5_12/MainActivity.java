package com.example.a5_12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private CheckBox cb;
    private WifiManager  mWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        cb = findViewById(R.id.checkBox);
        mWifi = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);


        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CHANGE_WIFI_STATE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CHANGE_WIFI_STATE},1);
        }
        else {
            SetState();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    SetState();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void SetState(){
            cb.setEnabled(true);
            switch(mWifi.getWifiState()){
                case WifiManager.WIFI_STATE_ENABLED:
                    cb.setChecked(true);
                    tv.setText("wifi已经打开");
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    cb.setChecked(false);
                    tv.setText("正在打开wifi");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    cb.setChecked(false);
                    tv.setText("wifi已经关闭");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    cb.setChecked(true);
                    tv.setText("正在关闭wifi");
                    break;
            }

    }
    public void OnClick(View v){
        if(cb.isChecked()){
            mWifi.setWifiEnabled(true);
        }
        else{
            mWifi.setWifiEnabled(false);
        }
        SetState();
    }
}
