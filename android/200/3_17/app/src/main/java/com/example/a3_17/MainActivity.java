package com.example.a3_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView vb = findViewById(R.id.webView);
        vb.getSettings().setJavaScriptEnabled(true);
        vb.loadUrl("https://www.sina.com.cn");
        vb.setWebViewClient(new MyWeb());
    }
    public class MyWeb extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
