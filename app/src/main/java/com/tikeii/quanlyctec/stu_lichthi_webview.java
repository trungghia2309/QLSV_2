package com.tikeii.quanlyctec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class stu_lichthi_webview extends AppCompatActivity {
    WebView web;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_lichthi_webview);
        web = findViewById(R.id.stu_lichthi);
        back = findViewById(R.id.stu_lichthi_back);
        web.loadUrl("https://www.ctec.edu.vn/ctec/index.php?page=lichthihocky");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}