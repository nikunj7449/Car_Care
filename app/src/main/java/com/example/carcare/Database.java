package com.example.carcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userdetail";
    private static final String TABLE_USER = "userlist";
    private static final String TABLE_CART = "cart";
    private static final String USER_ID = "userid";
    private static final String USER_NAME = "username";
    private static final String USERE_EMAIL = "useremail";
    private static final String PRODUCT = "product";
    private static final String PRICE = "price";
    private static final String OTYPE = "otype";
    private static final String USER_PASSWORD = "userpassword";
    private static final String USER_CITY = "usercity";


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {

        String qry1 = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " TEXT,"
                + USERE_EMAIL + " TEXT,"
                + USER_PASSWORD + " TEXT,"
                + USER_CITY + " TEXT" + ")";
        sqliteDatabase.execSQL(qry1);

        String qry2 = "CREATE TABLE " + TABLE_CART + "("
                + USER_NAME + " TEXT,"
                + PRODUCT + " TEXT,"
                + PRICE + " FLOAT,"
                + OTYPE + " TEXT" + ")";
        sqliteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace (username text, fullname text, address text, contactno text, pincode text ,date text, time text, amount integer, otype text)";
        sqliteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int ov, int nv) {
        String qry2 = "DROP TABLE IF EXISTS " + TABLE_USER;
        sqliteDatabase.execSQL(qry2);
        onCreate(sqliteDatabase);
    }

    public void register(String username, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, username);
        cv.put(USERE_EMAIL, email);
        cv.put(USER_PASSWORD, password);
        db.insert(TABLE_USER, null, cv);
        db.close();

    }

    public void addCart(String username, String product, float price, String otpye) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, username);
        cv.put(PRODUCT, product);
        cv.put(PRICE, price);
        cv.put(OTYPE, otpye);
        db.insert(TABLE_CART, null, cv);
        db.close();

    }

    public void enterCityName(String usercity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_CITY, usercity);
        db.insert(TABLE_USER, null, cv);
        db.close();

    }

    public int login(String username, String password) {
        int result = 0;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " + TABLE_USER + " WHERE " + USER_NAME + "=? AND " + USER_PASSWORD + "=?",
                new String[]{username, password});
        if (cursor.moveToNext()) {
            result = 1;
        }
        sqLiteDatabase.close();
        cursor.close();
        return result;
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where Username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, String pincode, String date, String time, int price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time",time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert( "orderplace",  null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery( "select * from orderplace where username=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString( 8));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }
    public ArrayList getOrderData(String username,String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery( "select * from orderplace where username=? and otype=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString( 8));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }
    public ArrayList getOrderData(String username,String otype,String otype1) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[3];
        str[0] = username;
        str[1] = otype;
        str[2] = otype1;
        Cursor c = db.rawQuery( "select * from orderplace where username=? and otype=? or otype=? ",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString( 8));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists (String username, String fullname, String address, String contact, String date, String time){
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c= db.rawQuery( "select * from orderplace where username=? and fullname = ? and address =? and contactno = ? and date = ? and time=?",str);
        if(c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }


}