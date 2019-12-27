package com.example.a5_6;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ContactsAdapter extends CursorAdapter {

    ContentResolver m_cr;
    public ContactsAdapter(Context context, Cursor c) {
        super(context, c);
        m_cr = context.getContentResolver();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater MyLi = LayoutInflater.from(context);
        final TextView tv = (TextView)MyLi.inflate(
                R.layout.activity_main,
                parent,
                false);

        tv.setText(cursor.getColumnIndexOrThrow(Contacts.People.NAME));
        return tv;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView v = (TextView)view;
        v.setText(cursor.getColumnIndexOrThrow(Contacts.People.NAME));
    }

    public String  Convert2String(Cursor cur){
        String str = cur.getString(
                cur.getColumnIndexOrThrow(Contacts.People.NAME));
        return str;
    }

    public Cursor RunQueryThread(CharSequence cs){
        if(null != getFilterQueryProvider()){
            return getFilterQueryProvider().runQuery(cs);
        }
        String ret = "";
        String[] str = null;
        if(null != cs){
            ret = ret + "UPPER(\""  + Contacts.People.NAME + "\")";
            str = new String[]{
               cs.toString().toUpperCase() +"*"
            };
        }
        return m_cr.query(
                Contacts.People.CONTENT_URI,
                MainActivity.m_PeopoleProject,
                null==ret?null:ret,
                str,
                Contacts.People.DEFAULT_SORT_ORDER
        );
    }
}