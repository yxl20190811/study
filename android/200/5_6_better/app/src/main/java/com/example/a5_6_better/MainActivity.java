package com.example.a5_6_better;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv=findViewById(R.id.textView2);

        sp = findViewById(R.id.spinner);

        sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {//重写选项被选中事件的处理方法
                        LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
                        TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView
                        String name = tvn.getText().toString();

                        TextView tvn1 = (TextView)ll.getChildAt(2);//获取其中的TextView
                        String num = tvn1.getText().toString();

                        tv.setText(" 联系人:" + name + " ,电话号码: " + num);
                    }

                    public void onNothingSelected(AdapterView<?> arg0) { }
                }
        );

        QueryContacts();
    }
    public void QueryContacts(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else {
            open();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    open();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void open(){
        sp.setAdapter(new MyAdapter(this));
    }
}
