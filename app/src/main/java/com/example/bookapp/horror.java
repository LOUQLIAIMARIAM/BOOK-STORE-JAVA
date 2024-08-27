package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class horror extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    CardView book1,book2,book3,book4,book5,book6;
    TextView text;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horror);

        bottomNavigationView = findViewById(R.id.bottmn);
        book1= findViewById(R.id.catblack);
        book2= findViewById(R.id.call);
        book3= findViewById(R.id.turn);
        book4= findViewById(R.id.book4);
        book5= findViewById(R.id.book5);
        book6= findViewById(R.id.book6);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }





        bottomNavigationView.setOnItemSelectedListener(item ->  {

            switch (item.getItemId()){
                case R.id.categorie:
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.search:
                    Intent intent1 = new Intent(this,search.class);
                    startActivity(intent1);
                    break;
                case R.id.profile:
                    Intent intent2 = new Intent(this,profile.class);
                    startActivity(intent2);
                    break;
            }

            return true;
        });

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(horror.this,blackCat.class);
                startActivity(intent);
            }
        });
        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(horror.this,call.class);
                startActivity(intent);
            }
        });
        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(horror.this,turn.class);
                startActivity(intent);
            }
        });
        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Buy the book                     29.00 MAD");
            }
        });
        book5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Buy the book                     84.00 MAD");
            }
        });
        book6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Buy the book                     23.00 MAD");
            }
        });

        text = findViewById(R.id.text);
        text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        text.setSelected(true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Your body hrer";
                String shareSub = "Your subject here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"SHare using"));
                break;
            case R.id.nav_about:
                Intent inten = new Intent(this,about.class);
                startActivity(inten);
                break;
            case R.id.nav_logout:
                Intent inten2 = new Intent(this,Login.class);
                startActivity(inten2);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This book is not available");
        builder.setMessage(message);

        builder.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(),paiment.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Actions à effectuer lorsque l'utilisateur clique sur le bouton "Non"
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}