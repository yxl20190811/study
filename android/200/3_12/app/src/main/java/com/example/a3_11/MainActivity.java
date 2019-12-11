package com.example.a3_11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setIcon(R.drawable.ic_launcher_foreground);
        b.setTitle("多选对话框");
        final boolean[] flag = new boolean[]{false,false,false};
        AlertDialog.Builder builder = b.setMultiChoiceItems(R.array.MyArray, flag,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean checkId) {
                        flag[which] = checkId;
                        TextView v = findViewById(R.id.textView);
                        String r = "你选择了:";
                        String[] tmp = getResources().getStringArray(R.array.MyArray);
                        for(int i =0; i < 3; ++i){
                            if(flag[i]){
                                r += tmp[i] + "  ";
                            }
                        }

                        v.setText(r);
                    }
                });

        b.setPositiveButton(
                "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        b.show();
    }
}
