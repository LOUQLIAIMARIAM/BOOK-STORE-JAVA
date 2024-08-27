package com.example.bookapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ImageDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "image_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "images";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE_URI = "image_uri";

    public ImageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_URI + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertImageUri(Uri imageUri) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_URI, imageUri.toString());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Uri getImageUriFromDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {COLUMN_IMAGE_URI};
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        Uri imageUri = null;
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String uriString = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI));
            imageUri = Uri.parse(uriString);
            cursor.close();
        }
        db.close();
        return imageUri;
    }
}

