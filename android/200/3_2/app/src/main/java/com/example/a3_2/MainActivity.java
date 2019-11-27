package com.example.a3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int drawIds[] = {R.drawable.andy, R.drawable.bill, R.drawable.edgar, R.drawable.torvalds, R.drawable.torvalds};
    int[] nameIds = {R.string.andy, R.string.bill, R.string.edgar, R.string.torvalds, R.string.turing};
    int[] msgIds = {R.string.andydis, R.string.billdis, R.string.edgardis, R.string.torvaldsdis, R.string.turingdis};

    public List<Map<String, Object>> getDataList(){
        ArrayList<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
        int len = drawIds.length;
        for(int i = 0; i < len; ++i){
            HashMap<String, Object> hmap = new HashMap<String, Object>();
            hmap.put("c1", drawIds[i]);
            hmap.put("c2", this.getResources().getString(nameIds[i]));
            hmap.put("c3", this.getResources().getString(msgIds[i]));
            ret.add(hmap);
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gv = findViewById(R.id.GridView1);
        SimpleAdapter sa = new SimpleAdapter(
                this, getDataList(),
                R.layout.test, new String[]{"c1","c2","c3"},
                new int[]{R.id.imageView, R.id.textView4, R.id.textView5}
        );

        gv.setAdapter(sa);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = findViewById(R.id.textView);

                return;
            }
        });
    }
}
