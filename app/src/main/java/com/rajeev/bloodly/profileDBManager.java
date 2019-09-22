package com.rajeev.bloodly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class profileDBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myDatabase1";    // Database Name
    private static final String TABLE_NAME = "profile";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version
    private static final String ID="id";     // Column I (Primary Key)
    private static final String NAME = "Name";    //Column II
    private static final String BloodGroup= "BloodGroup";    // Column III
    private static final String Age= "Age";
    private static final String Phone= "Phone";
    private static final String Place= "Address";

    public profileDBManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME+" (\n" +
                " "+ID+" INTEGER NOT NULL CONSTRAINT blood1_pk PRIMARY KEY AUTOINCREMENT,\n" +
                " "+NAME+" varchar(20) NOT NULL,\n" +
                " "+BloodGroup+" varchar(10) NOT NULL,\n" +
                " "+Age+" varchar NOT NULL,\n" +
                " "+Phone+" varchar NOT NULL,\n" +
                " "+Place+" varchar(100) NOT NULL\n" +
                ");";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    boolean update(String name, String bloodGroup, String age, String phone, String address){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(BloodGroup, bloodGroup);
        cv.put(Age, age);
        cv.put(Phone, phone);
        cv.put(Place, address);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;
    }

}
