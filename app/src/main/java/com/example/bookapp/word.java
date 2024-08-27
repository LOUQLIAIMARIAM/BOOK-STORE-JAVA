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

public class word extends AppCompatActivity {

    TextView title;
    ImageView home,down,flch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);


        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(word.this,MainActivity.class);
                startActivity(intent);
            }
        });

        flch = findViewById(R.id.flech);
        flch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(word.this,romance.class);
                startActivity(intent);
            }
        });
        down = findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ibiblio.org/ebooks/Verne/Eighty_Days.pdf";

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

                Toast.makeText(word.this, "download started", Toast.LENGTH_SHORT).show();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("word.pdf").load();
    }
}