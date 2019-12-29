package com.example.addcontract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onAddContract(View v){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_CONTACTS},1);
        }
        else {

            addContact();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                //同意申请的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "同意通过", Toast.LENGTH_SHORT).show();
                    addContact();
                    //拒绝申请的权限
                } else {
                    Toast.makeText(this, "拒绝通过", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void addContact() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.dir/person");
        intent.setType("vnd.android.cursor.dir/contact");
        intent.setType("vnd.android.cursor.dir/raw_contact");
        startActivity(intent);
    }

    private int i= 1;
    public void addContact1(){
        String name = "heming_" + i;
        int num = 12345678 + i;
        String tel = "136" + num;
        RealAddContact(name, tel);
        ++i;
    }
    public void RealAddContact(String name, String phoneNumber) {
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();

        // 向RawContacts.CONTENT_URI空值插入，
        // 先获取Android系统返回的rawContactId
        // 后面要基于此id插入值
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();

        values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        // 电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);


        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
    }
}
