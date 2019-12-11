package com.example.a3_11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = LayoutInflater.from(this).inflate(R.layout.my_layout, null);
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("单选对话框列表");
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setCancelable(false);
        builder.setView(v);
        builder.show();

    }
}
