package com.example.a2_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText  m_UserName, m_Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_UserName = findViewById(R.id.editText2);
        m_Password = findViewById(R.id.editText);
    }
    public void OnClear(View v){
        m_UserName.setText("");
        m_Password.setText("");
    }
    public void OnLogin(View v){

        String user = m_UserName.getText().toString();
        String pass = m_Password.getText().toString();
        if(user.equals("e1001") && pass.equals("111111")){
            Toast.makeText(this, "恭喜你成功登录", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
        }
    }
}
