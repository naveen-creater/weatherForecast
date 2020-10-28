package com.example.weatherforecast.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.weatherforecast.Modelclass.Userdata;

/**
 * Created by Naveen on 26/10/20.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static SQLiteDatabase db;

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "userdata.db";
    private static final String TABLE_NAME = "userdetails";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_EMAILID = "emailid";
    private static final String COLUMN_MOBILENO = "mobileno";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE = "create table IF NOT EXISTS userdetails (id integer primary key not null , " +
            "fullname text not null , emailid text not null, age integer not null, password text not null, mobileno text not null);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        this.db = db;
        Log.d("Table created", "created");
    }

    public boolean insertData(Userdata userdata) {
        SQLiteDatabase dp = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, userdata.getFullName());
        values.put(COLUMN_EMAILID, userdata.getEmail());
        values.put(COLUMN_MOBILENO, userdata.getMobileNo());
        values.put(COLUMN_AGE, userdata.getAge());
        values.put(COLUMN_PASSWORD, userdata.getPassword());

        try {
            dp.insert(TABLE_NAME, null, values);
            Log.d("Insert SUCCESS", values.toString());
            return true;
        } catch (Exception e) {
            Log.d("Insert FAILURE", e.toString());
            return false;
        }
    }

    public boolean userExists(String emailid, String password) {
        SQLiteDatabase DB = this.getReadableDatabase();
        String fetchuser = "Select emailid,password from " + TABLE_NAME;
        Cursor cursor = DB.rawQuery(fetchuser, null);
        String a, b = "not found";
        Log.d("received emailid", emailid);
        Log.d("Cursor count", String.valueOf(cursor.getCount()));
        if (cursor.moveToFirst()) {
            Log.d("Select ", " clause");
            do {
                a = cursor.getString(0);
                Log.d("a ", a);
                if (a.equals(emailid)) {
                    Log.d("emailid  If loop", a);
                    b = cursor.getString(1);
                    Log.d("b ", b);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        if (b.equals(password)) {
            Log.d("Returning ", " true");
            return true;
        } else return false;
    }

    public String getUserName(String emailid) {
        SQLiteDatabase DB = this.getReadableDatabase();
        String fetchuser = "Select " + COLUMN_EMAILID + ", " + COLUMN_FULLNAME + " from " + TABLE_NAME;
        Cursor cursor = DB.rawQuery(fetchuser, null);
        String a, b = "not found";
        Log.d("received emailid", emailid);
        Log.d("Cursor count", String.valueOf(cursor.getCount()));
        if (cursor.moveToFirst()) {
            Log.d("Select ", " clause");
            do {
                a = cursor.getString(0);
                Log.d("a ", a);
                if (a.equals(emailid)) {
                    Log.d("emailid  If loop", a);
                    b = cursor.getString(1);
                    Log.d("b ", b);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropquery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropquery);
        this.onCreate(db);
    }
}
