package com.example.user.bd_bands;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 19-Aug-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String band_table= "CREATE TABLE Bands ( Id INTEGER PRIMARY KEY,Name TEXT, Type Text)";
        db.execSQL(band_table);
        String album_table= "CREATE TABLE Albums ( Id INTEGER PRIMARY KEY,Name TEXT, Band_id Integer,FOREIGN KEY(Band_id) REFERENCES band_table(Id))";
        db.execSQL(album_table);
        String song_table= "CREATE TABLE Songs ( Id INTEGER PRIMARY KEY,Name TEXT, Album_id Integer,FOREIGN KEY(Album_id) REFERENCES Album_table(Id)))";
        db.execSQL(song_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS band_album ");

        // Create tables again
        onCreate(db);
    }
}
