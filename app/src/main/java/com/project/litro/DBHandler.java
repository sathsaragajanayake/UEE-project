package com.project.litro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME_USER = "litro.db"; //

    public DBHandler(Context context) {
        super(context, DATABASE_NAME_USER, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


            sqLiteDatabase.execSQL("CREATE TABLE user(firstName TEXT,secondName TEXT,email TEXT ,mobileNumber TEXT,password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE address(address1 TEXT, address2 TEXT ,city TEXT,place TEXT,district TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE orders(orderId TEXT, date TEXT , dealer TEXT , total TEXT , qty TEXT , status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS address");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orders");
    }

    public long addUser(String firstName,String secondName, String email,String mobileNumber,String password) {


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("firstName", firstName);
        values1.put("secondName", secondName);
        values1.put("email", email);
        values1.put("mobileNumber", mobileNumber);
        values1.put("password", password);

        //


        long id1 = sqLiteDatabase.insert("user", null, values1);
        return id1;

    }

    public long addAddress(String address1,String address2, String city,String place,String district) {


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values2 = new ContentValues();
        values2.put("address1", address1);
        values2.put("address2", address2);
        values2.put("city", city);
        values2.put("place", place);
        values2.put("district", district);




        long id2 = sqLiteDatabase.insert("address", null, values2);
        return id2;

    }
    public long addOrders(String orderId,String date, String dealer,String total,String qty,String status) {


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values3 = new ContentValues();
        values3.put("orderId", orderId);
        values3.put("date", date);
        values3.put("dealer", dealer);
        values3.put("total", total);
        values3.put("qty", qty);
        values3.put("status", status);




        long id3 = sqLiteDatabase.insert("address", null, values3);
        return id3;

    }

    public Cursor readUserData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user" ,null );

        return cursor;
    }
    public Cursor readaddressData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM address",null );

        return cursor;
    }

    public Cursor readordersData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orders" ,null );
        return cursor;
    }

    public long updateNumber(String firstName,String secondName, String email,String mobileNumber,String password){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values4 = new ContentValues();
        values4.put("firstName", firstName);
        values4.put("secondName", secondName);
        values4.put("mobileNumber", mobileNumber);
        values4.put("password", password);
        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM user where email=?" ,new String[]{email});
        long id2 = sqLiteDatabase.update("user",values4, "email=?",new String[]{email});
        return id2;
    }
}
