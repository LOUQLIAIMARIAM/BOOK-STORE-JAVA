package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class island extends AppCompatActivity {
    TextView title;
    ImageView home,down,flch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island);


        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(island.this,MainActivity.class);
                startActivity(intent);
            }
        });
        flch = findViewById(R.id.flech);
        flch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(island.this,clothing.class);
                startActivity(intent);
            }
        });
        down = findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.arvindguptatoys.com/arvindgupta/mysterious-island.pdf";

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                String title = URLUtil.guessFileName(url,null,null);
                request.setTitle(title);
                request.setDescription("Downloading File please wait ....");
                String cookie = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie",cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);

                DownloadManager downloadManager =(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                Toast.makeText(island.this, "download started", Toast.LENGTH_SHORT).show();
            }
        });

        PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("island.pdf").load();


    }
}