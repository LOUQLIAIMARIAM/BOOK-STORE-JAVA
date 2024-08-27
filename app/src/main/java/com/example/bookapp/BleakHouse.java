package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BleakHouse extends AppCompatActivity {

    ImageView home, down,flch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleak_house);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("Bleakhouse.pdf").load();

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BleakHouse.this, MainActivity.class);
                startActivity(intent);
            }
        });


        flch = findViewById(R.id.flech);
        flch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(BleakHouse.this,clothing.class);
                startActivity(intent);
            }
        });

        down = findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.planetebook.com/free-ebooks/bleak-house.pdf";

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

                Toast.makeText(BleakHouse.this, "download started", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

