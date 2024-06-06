package com.example.relativenet;

import android.database.sqlite.SQLiteDatabase;

public class IDataBasePath {
    private static SQLiteDatabase database;


    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(SQLiteDatabase database) {
        IDataBasePath.database = database;
    }
}
