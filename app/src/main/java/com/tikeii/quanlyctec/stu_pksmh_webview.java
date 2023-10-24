package com.tikeii.quanlyctec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class stu_pksmh_webview extends AppCompatActivity {
    WebView web;
    Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_pksmh_webview);
        web = findViewById(R.id.pskmh);
        back = findViewById(R.id.stu_pksmh_back);
        web.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLScSsau1ttZAZYg6ICNaw3GT6TLXlrDyD4FOmfYbPUCvziydRw/viewform");
        web.getSettings().getJavaScriptEnabled();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}