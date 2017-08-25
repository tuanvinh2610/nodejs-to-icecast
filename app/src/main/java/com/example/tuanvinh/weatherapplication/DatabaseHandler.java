package com.example.tuanvinh.weatherapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanVinh on 8/24/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "weather.db";

    private static final String TABLE_CONTACTS = "weather";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN = "main";

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TEMP = "temp";
    private static final String KEY_HUMIDITY = "humidity";
    private static final String KEY_SPEED = "speed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_MAIN + " TEXT,"+ KEY_DESCRIPTION + "TEXT,"+
                KEY_TEMP + "DOUBLE," +KEY_HUMIDITY + "DOUBLE," +KEY_SPEED + "DOUBLE,"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Adding weather
    void addWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, weather.getName());
        values.put(KEY_MAIN, weather.getMain());
        values.put(KEY_DESCRIPTION, weather.getDescription());
        values.put(KEY_TEMP, weather.getTemp());
        values.put(KEY_HUMIDITY, weather.getHumidity());
        values.put(KEY_SPEED, weather.getSpeed());


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }


    public int updateWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, weather.getName());
        values.put(KEY_MAIN, weather.getMain());
        values.put(KEY_DESCRIPTION, weather.getDescription());
        values.put(KEY_TEMP, weather.getTemp());
        values.put(KEY_HUMIDITY, weather.getHumidity());
        values.put(KEY_SPEED, weather.getSpeed());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(weather.getId()) });
    }

    // Getting All Contacts
    public List<Weather> getAllWeather() {
        List<Weather> weatherList = new ArrayList<Weather>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Weather weather = new Weather();
                weather.setId(Integer.parseInt(cursor.getString(0)));
                weather.setName(cursor.getString(1));
                weather.setTemp(cursor.getDouble(2));

                weatherList.add(weather);
            } while (cursor.moveToNext());
        }


        return weatherList;
    }

    public void deleteWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(weather.getId()) });
        db.close();
    }


}
