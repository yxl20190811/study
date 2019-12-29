package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private AutoCompleteTextView myAutoCompleteTextView;
    private TextView myTextView1;
    private ContactsAdapter myContactsAdapter;


    public static final String[] PEOPLE_PROJECTION=
            new String[] {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER};


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
        myTextView1 = (TextView) findViewById(R.id.myTextView1);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else {
            ReadContracts();
        }

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    ReadContracts();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                    //m_tv.setEnabled(true);
                }
                break;
        }
    }
    public void ReadContracts(){
        final ContentResolver content = getContentResolver();


        Cursor c = content.query(ContactsContract.Contacts.CONTENT_URI,
                PEOPLE_PROJECTION,null,null,null);



        myContactsAdapter = new ContactsAdapter(this,c);

        myAutoCompleteTextView.setAdapter(myContactsAdapter);

        myAutoCompleteTextView
                .setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3)
                    {

                        Cursor c = myContactsAdapter.getCursor();

                        c.moveToPosition(arg2);

                        String str="";

                        String contactId = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        int phoneCount = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        if(phoneCount>0){
//获得联系人的电话号码
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
                            if(phones.moveToFirst()){
                                do{
//遍历所有的电话号码
                                    String phonenum= phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    str+=":"+phonenum;
                                }while(phones.moveToNext());
                            }

                        }
                        myTextView1.setText(c.getString(c
                                .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                                + "的電話是" + str);
                    }
                });

    }
}

