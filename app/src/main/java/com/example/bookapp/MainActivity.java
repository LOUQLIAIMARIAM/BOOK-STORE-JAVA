package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView clothingCard;
    CardView horroractiviy;
    CardView romance;
    CardView science;
    CardView clasic;
    CardView crime;


    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clothingCard = findViewById(R.id.clothingCard);
        horroractiviy = findViewById(R.id.horror);
        romance = findViewById(R.id.romance);
        science = findViewById(R.id.science);
        clasic = findViewById(R.id.classics);
        crime = findViewById(R.id.crime);

        clothingCard.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, clothing.class);
                    startActivity(intent);
                }
            });

        horroractiviy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, horror.class);
                startActivity(intent);
            }
        });
          romance.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, romance.class);
                  startActivity(intent);
              }
          });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, science.class);
                startActivity(intent);
            }
        });

        clasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Classics.class);
                startActivity(intent);

            }
        });

        crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, crime.class);
                startActivity(intent);
            }
        });


        }






    }

