package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class search extends AppCompatActivity {

    ListView lv;
    TextView title;
    MediaPlayer player;
    ImageView home,down;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv=findViewById(R.id.lv);

        String []name={"THE BISHOP'S SECRET.","THE BLACK CAT","Bleakhouse","THE CALL OF CTHULHU","The Land that Time Forgot",
                       "The Last Man"};

        Integer[]img={R.drawable.bichop,R.drawable.catblack,R.drawable.bleakhouse,R.drawable.call, R.drawable.island,R.drawable.land,R.drawable.man
        };

        MyListAdapter adapter=new MyListAdapter(this, name,img);
        lv.setAdapter(adapter);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Check the clicked item position and start the corresponding activity
                switch (position) {
                    case 0:
                        playMusic(R.raw.bichop);
                        break;
                    case 1:
                        playMusic(R.raw.blackcat);
                        break;
                    case 2:
                        playMusic(R.raw.bleakhouse);
                        break;
                    case 3:
                        playMusic(R.raw.cal);
                        break;
                    case 4:
                        playMusic(R.raw.lan);
                        break;
                    case 5:
                        playMusic(R.raw.man);
                        break;
                    default:
                        Toast.makeText(search.this, "click ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(search.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    public class MyListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] name;
        private final Integer[] img;

        public MyListAdapter(Activity context, String[] name, Integer[] img) {
            super(context, R.layout.customlist, name);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.img = img;
            this.name=name;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.customlist, null,true);

            TextView titleText = (TextView) rowView.findViewById(R.id.title);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

            titleText.setText(name[position]);
            imageView.setImageResource(img[position]);

            return rowView;

        };



    }

    private void playMusic(int resourceId) {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }

        player = MediaPlayer.create(this, resourceId);
        player.start();}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }
    }
//    private void bichop() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.bichop);
//        }
//        player.start();
//    }
//    private void blackcat() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.blackcat);
//        }
//        player.start();
//    }
//    private void blesk() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.bleakhouse);
//        }
//        player.start();
//    }
//    private void cal() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.cal);
//        }
//        player.start();
//    }
//    private void lan() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.lan);
//        }
//        player.start();
//    }
//    private void man() {
//        if (player == null) {
//            player = MediaPlayer.create(this, R.raw.man);
//        }
//        player.start();
//    }


}

