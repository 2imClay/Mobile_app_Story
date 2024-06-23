package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.project.model.Chapter;
import com.example.project.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{

    DatabaseHelper databaseHelper;
    public  UserDAO(Context context){
        this.databaseHelper = new DatabaseHelper(context);
    }
    @Override
    public List<User> selectAll() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        if(cursor.moveToFirst()){
            do {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String role = cursor.getString(cursor.getColumnIndexOrThrow("role"));
                String srcImg =cursor.getString(cursor.getColumnIndexOrThrow("srcImg"));
                User user = new User(username,password,email,name,role,srcImg);
                list.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean insert(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long newRowId = -1;
        try{
            String sql = "INSERT INTO users(username, password, email,name,role, srcImg) VALUES (??????)";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindString(1, user.getUsername());
            stmt.bindString(2, user.getPassword());
            stmt.bindString(3,user.getEmail());
            stmt.bindString(4,user.getName());
            stmt.bindString(5,user.getRole());
            stmt.bindString(6, user.getSrcImg());
            newRowId = stmt.executeInsert();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return newRowId > 0;
    }

    @Override
    public boolean update(String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
