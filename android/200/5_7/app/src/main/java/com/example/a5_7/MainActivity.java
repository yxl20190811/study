package com.example.a5_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
    }
    public void OnClick(View v){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else {
            open();
        }
    }

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

                }
                break;
            default:
                break;
        }

    }

    public void open(){
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        this.startActivityForResult(intent, 1);
    }

    public void onActivityResult(int reqCode, int retCode, Intent data ){
        if(1 != reqCode){
            return;
        }
        final Uri uri = data.getData();
        if(null != uri){
            ContentResolver con = getContentResolver();
            Cursor cur = con.query(
                    uri,
                    null,
                   null , null, null);

            cur.moveToFirst();
            String name= cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));


            String contactId = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            int index = cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            if(cur.getInt(index) > 0){
                name  += " 电话号码";
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
                if(phones.moveToFirst()){
                    do{
//遍历所有的电话号码
                        String phonenum= phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        name +=":"+phonenum;
                    }while(phones.moveToNext());
                }
            }

            tv.setText(name);
        }
    }
}
