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
                android.R.layout.simple_dropdown_item_1line,
                parent,
                false);

        int index = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        String text = cursor.getString(index);
        tv.setText(text);
        return tv;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView v = (TextView)view;
        int index = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        String text = cursor.getString(index);
        v.setText(text);
    }

    public String  convertToString(Cursor cur){
        String str = cur.getString(
                cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
        return str;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence cs) {
        if (null != getFilterQueryProvider()) {
            return getFilterQueryProvider().runQuery(cs);
        }

        String ret = "";
        String[] str = null;
        if (null != cs) {
            ret = ret + "UPPER(\"" + ContactsContract.Contacts.DISPLAY_NAME + "\") GLOB ?";
            str = new String[]{
                    cs.toString().toUpperCase() + "*"
            };
        }
        return m_cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                MainActivity.m_PeopoleProject,
                null == ret ? null : ret,
                str,
                null
        );



    }
}
