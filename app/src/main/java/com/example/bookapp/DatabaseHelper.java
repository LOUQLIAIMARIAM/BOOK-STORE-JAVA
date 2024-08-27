package com.example.bookapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static  final String databaseName = "Book";

    public DatabaseHelper(@Nullable Context context) {
        super(context,"Signup.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table  allusers(email TEXT primary key , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1)
    {
        MyDatabase.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String email , String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email",email);
        contentValues.put("Password",password);
        long result = MyDatabase.insert("allusers",null,contentValues);

        if(result == -1){
            return false;
        }else{
            return  true;
        }

    }

    public Boolean checkEmail(String email){
        SQLiteDatabase  MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from  allusers where email = ?", new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }

    }
    public  Boolean chekEmailPassword(String email, String password){
        SQLiteDatabase  MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from  allusers where email = ? and password = ?", new String[]{email,password});

        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }

    }

    @SuppressLint("Range")
    public String getEmail() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + "allusers";
        Cursor cursor = db.rawQuery(selectQuery, null);

        String email = null;
        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex("email"));
        }

        cursor.close();
        db.close();


        return email;
    }


    public ArrayList<String> getAllData() {
        ArrayList<String> dataList = new ArrayList<>();
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM  allusers", null);

        if (cursor.moveToFirst()) {
            int emailColumnIndex = cursor.getColumnIndex("email");
            int passwordColumnIndex = cursor.getColumnIndex("password");

            do {
                String email = cursor.getString(emailColumnIndex);


                dataList.add("Email: " + email);
            } while (cursor.moveToNext());
        }

        cursor.close();
        MyDatabase.close();
        return dataList;
    }


}
