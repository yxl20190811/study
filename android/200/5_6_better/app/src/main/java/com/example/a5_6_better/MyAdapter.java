package com.example.a5_6_better;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    static public String[] m_PeopoleProject =
            new String[] {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER};


    ContentResolver m_cr;
    Cursor m_cur;
    Context m_c;
    MyAdapter(Context c){
        m_c = c;
        m_cr = m_c.getContentResolver();
        m_cur = m_cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                m_PeopoleProject,
                null, null, null);
    }
    @Override
    public int getCount() {
        return m_cur.getCount();
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
        m_cur.moveToPosition(position);
        String name = m_cur.getString(m_cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

        String contactId = m_cur.getString(m_cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
        String num = "";
        if(m_cur.getInt(m_cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
            Cursor phones = m_cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
            if(phones.moveToFirst()){
                do{//遍历所有的电话号码
                    String phonenum= phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    num +=":"+phonenum;
                }while(phones.moveToNext());
            }
        }

        //初始化LinearLayout
        LinearLayout ll=new LinearLayout(m_c);
        ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向

        //初始化ImageView
        ImageView ii=new ImageView(m_c);
        ii.setImageDrawable(m_c.getResources().getDrawable(R.drawable.basketball));//设置图片
        ll.addView(ii);//添加到LinearLayout中

        //初始化TextView
        TextView tv=new TextView(m_c);
        tv.setText(name);//设置内容
        tv.setTextSize(24);//设置字体大小
        tv.setTextColor(Color.BLACK);//设置字体颜色
        ll.addView(tv); //添加到LinearLayout中

        //初始化TextView
        TextView tv1=new TextView(m_c);
        tv1.setText(num);//设置内容
        tv1.setTextSize(2);//设置字体大小
        tv1.setTextColor(Color.RED);//设置字体颜色
        ll.addView(tv1); //添加到LinearLayout中

        return ll;
    }
}
