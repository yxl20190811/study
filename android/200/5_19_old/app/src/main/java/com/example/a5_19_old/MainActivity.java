package com.example.a5_19_old;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String>  list = new ArrayList<String>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.ListView1);
    }
    public void BltOnClick(View v) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.GET_TASKS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.GET_TASKS}, 1);
        } else {
            test();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    test();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void test(){
        ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
        list.clear();
        List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
        for(int i = 0; i< l.size(); ++i){
            ActivityManager.RunningAppProcessInfo info = l.get(i);
            list.add( i + ":" + info.processName + ": id=" + info.pid);
        }
        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
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
                LinearLayout l1 = new LinearLayout(MainActivity.this);
                l1.setOrientation(LinearLayout.HORIZONTAL);
                l1.setPadding(5,5,5,5);
                TextView tv = new TextView(MainActivity.this);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.DKGRAY);
                tv.setText(list.get(position));
                tv.setTextSize(20);
                l1.addView(tv);
                return l1;
            }
        };
        lv.setAdapter(ba);
    }
}
