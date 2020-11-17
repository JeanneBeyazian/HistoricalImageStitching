// https://www.sqlitetutorial.net/sqlite-create-table/
// https://developer.android.com/training/data-storage/sqlite
// https://developer.android.com/training/data-storage/room

package com.example.historicalimagestitching.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.sql.Blob;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "picturesDB.db";

    public static final String TABLE_NAME = "Picture";
    public static final String COLUMN_ID = "PictureID";
    public static final String COLUMN_TITLE = "PictureTitle";
    public static final String COLUMN_LOCATION_NAME = "PictureLocationName";
    public static final String COLUMN_LATITUDE = "PictureLatitude";
    public static final String COLUMN_LONGITUDE = "PictureLongitude";
    public static final String COLUMN_IMAGE = "PictureImage";



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + "TEXT NOT NULL," +
                COLUMN_LOCATION_NAME + "TEXT NOT NULL," +
                COLUMN_LATITUDE + "REAL NOT NULL," +
                COLUMN_LONGITUDE + "REAL NOT NULL," +
                COLUMN_IMAGE + "BLOB NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){}

    public String loadHandler() {

        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            int result_ID = cursor.getInt(0);
            String result_title = cursor.getString(1);
            String result_location = cursor.getString(2);
            double result_latitude = cursor.getDouble(3);
            double result_longitude = cursor.getDouble(4);
            byte[] result_image = cursor.getBlob(5);

            result += String.valueOf(result_ID) + " " + result_title + " " + result_location + " " +
                    String.valueOf(result_latitude) + " " + String.valueOf(result_longitude) + " " +
                    String.valueOf(result_image) + System.getProperty("line.separator");

        }

        cursor.close();
        db.close();
        return result;

    }


    public void addHandler(Picture pic) {

        ContentValues values = new ContentValues();

        //values.put(COLUMN_ID, pic.getID());
        values.put(COLUMN_TITLE, pic.getTitle());
        values.put(COLUMN_LOCATION_NAME, pic.getLocationName());
        values.put(COLUMN_LATITUDE, pic.getLatitude());
        values.put(COLUMN_LONGITUDE, pic.getLongitude());
        values.put(COLUMN_IMAGE, pic.getImage());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    /**
     * Retrieve a picture from the database by ID
     * @param ID primary key of the picture object
     * @return Picture object found
     */
    public Picture findHandler(int ID) {


        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID +
                " = ''" + String.valueOf(ID) + "''";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Picture pic = new Picture();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();
            pic.setID(cursor.getInt(0));
            pic.setTitle(cursor.getString(1));
            pic.setLocationName(cursor.getString(2));
            pic.setLatitude(cursor.getDouble(3));
            pic.setLongitude(cursor.getDouble(4));
            pic.setImage(cursor.getBlob(5));

            cursor.close();

        } else {
            pic = null;
        }

        db.close();
        return pic;
    }

    public boolean deleteHandler(int ID){

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID +
                " = ''" + String.valueOf(ID) + "''";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Picture pic = new Picture();

        if (cursor.moveToFirst()) {

            pic.setID(cursor.getInt(0));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                    String.valueOf((pic.getID()))
            });
            cursor.close();
            result = true;
        }

        db.close();
        return result;

    }

    public boolean updateHandler(int ID, String title, String locationName, double latitude,
                                 double longitude, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID, ID);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_LOCATION_NAME, locationName);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        values.put(COLUMN_IMAGE, image);

        return db.update(TABLE_NAME, values, COLUMN_ID + "=" + ID, null) > 0;

    }




}
