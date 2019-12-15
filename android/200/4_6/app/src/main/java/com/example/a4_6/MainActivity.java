package com.example.a4_6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setIconEnable(Menu menu, boolean enable) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");

            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MenuItem male;
    private MenuItem[] hobby = new MenuItem[3];
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu, true);
        SubMenu sm = menu.addSubMenu(2, 6, 0, "性别");
        sm.setIcon(R.drawable.gender);
        male = sm.add(0, 0, 0, "男");
        //male.setCheckable(true);
        MenuItem female = sm.add(0, 1, 0, "女");
        sm.setGroupCheckable(0, true, true);

        SubMenu sm1 = menu.addSubMenu(2, 7, 0, "爱好");
        sm1.setIcon(R.drawable.hobby);
        hobby[0] = sm1.add(1, 2, 0, "游泳");
        hobby[1] = sm1.add(1, 3, 0, "唱歌");
        hobby[2] = sm1.add(1, 4, 0, "写程序");
        for(MenuItem i: hobby){
            i.setCheckable(true);
        }

        menu.addSubMenu(2, 8, 0, "确定");
        return true;
    }

    private void upStateStr() {
        TextView tv = findViewById(R.id.textView01);
        String t;
        if(male.isChecked()){
            t = "你是男性 ";
        }
        else{
            t = "你是女性 ";
        }

        String h = "";
        for(MenuItem i: hobby){
            if(i.isChecked()){
                h += i.getTitle().toString() + " ";
            }
        }
        if(h.length() > 0){
            t = t + "你的爱好是:" + h;
        }

        tv.setText(t);
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        switch(mi.getItemId())
        {
            case 0:
            case 1:
                mi.setChecked(true);
                upStateStr();
                break;

            case 2:
            case 3:
            case 4:
                mi.setChecked(!mi.isChecked());
                upStateStr();
                break;
        }
        return true;
    }
}
