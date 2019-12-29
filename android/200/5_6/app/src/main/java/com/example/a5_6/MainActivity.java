package com.example.a5_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView m_tv;
    private AutoCompleteTextView m_at;

    static public String[] m_PeopoleProject =
            new String[] {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private Cursor m_cur;
    private ContactsAdapter m_ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_tv = findViewById(R.id.textView);
        m_at = findViewById(R.id.autoCompleteTextView2);

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
        switch (requestCode){
            case 1:
                //同意申请的权限
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"同意通过",Toast.LENGTH_SHORT).show();
                    open();
                    //拒绝申请的权限
                }else{
                    Toast.makeText(this,"拒绝通过",Toast.LENGTH_SHORT).show();
                    m_tv.setEnabled(true);
                }
                break;
            default:
                break;
        }

    }

    public void open(){
        ContentResolver con = getContentResolver();
        m_cur = con.query(
                ContactsContract.Contacts.CONTENT_URI,
                m_PeopoleProject,
                null, null, null);
        m_ca = new ContactsAdapter(this, m_cur);

        m_at.setAdapter(m_ca);

        m_at.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = "联系人: ";
                Cursor c = m_ca.getCursor();
                c.moveToPosition(position);
                String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                result  += name;

                String contactId = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                if(c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    result  += " 电话号码";
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
                    if(phones.moveToFirst()){
                        do{
//遍历所有的电话号码
                            String phonenum= phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            result +=":"+phonenum;
                        }while(phones.moveToNext());
                    }
                }

                m_tv.setText(result);
            }
        });
    }
}
