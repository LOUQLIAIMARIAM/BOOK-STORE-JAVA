package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class bichop extends AppCompatActivity {

    TextView title;
    ImageView home,down,flch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bichop);


        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(bichop.this,MainActivity.class);
                startActivity(intent);
            }
        });
        flch = findViewById(R.id.flech);
        flch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(bichop.this,crime.class);
                startActivity(intent);
            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("bichop.pdf").load();
    }
}