package com.example.a4_5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import java.util.Date;

public class editActive  extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = this.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
        String tx = sp.getString("xq", "今天好心情");
        setContentView(R.layout.edit_layerout);
        final EditText et = findViewById(R.id.editText);
        et.setText(tx);

        Button bt = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et.getText().toString();
                SharedPreferences sp = editActive.this.getSharedPreferences("xqsj", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("xq", msg);
                ed.commit();

                editActive.this.finish();
            }
        });
    }
}
