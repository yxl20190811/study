package com.example.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ContactsAdapter extends CursorAdapter
{
    private ContentResolver mContent;

    public ContactsAdapter(Context context, Cursor c)
    {
        super(context, c);
        mContent = context.getContentResolver();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {

        ((TextView) view).setText(cursor.getString(cursor
                .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final TextView view = (TextView) inflater.inflate(
                android.R.layout.simple_dropdown_item_1line, parent, false);
        view.setText(cursor.getString(cursor
                .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));
        return view;
    }

    @Override
    public String convertToString(Cursor cursor)
    {
        return cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint)
    {

        if (getFilterQueryProvider() != null)
        {
            return getFilterQueryProvider().runQuery(constraint);
        }

        StringBuilder buffer = null;
        String[] args = null;
        if (constraint != null)
        {
            buffer = new StringBuilder();
            buffer.append("UPPER(");
            buffer.append(ContactsContract.Contacts.DISPLAY_NAME);
            buffer.append(") GLOB ?");
            args = new String[]
                    { constraint.toString().toUpperCase() + "*" };
        }
        Cursor c= mContent.query(ContactsContract.Contacts.CONTENT_URI,
                MainActivity.PEOPLE_PROJECTION,
                buffer == null ? null : buffer.toString(),
                args,null);
        return c;

    }
}