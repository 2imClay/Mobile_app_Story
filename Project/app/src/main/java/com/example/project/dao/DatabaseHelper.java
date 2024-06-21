package com.example.project.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "example.db";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Database path: " + context.getDatabasePath(DATABASE_NAME));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Gọi các phương thức tạo bảng của các DAO
        UserDAO.onCreate(db);
        StoryDAO.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Gọi các phương thức nâng cấp bảng của các DAO
        UserDAO.onUpgrade(db, oldVersion, newVersion);
        StoryDAO.onUpgrade(db, oldVersion, newVersion);
    }
}
