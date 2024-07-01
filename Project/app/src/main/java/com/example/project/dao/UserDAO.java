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
                User user = new User(username,password,email);
                list.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public long insert(User user) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());

        return db.insert("users", null, values);
    }

    @Override
    public boolean update(String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
    public boolean authercationUser(String username, String password){
        for (User user:
             selectAll()) {
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)){
                return true;
            }
        }
        return false;
    }
    public boolean changePassword(String username, String newPassword) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        int rows = db.update("users", values, "username" + " = ?", new String[]{username});
        db.close();
        return rows > 0;
    }
    public boolean checkUser(String username) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM users WHERE username = ? " ;
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public User getUserByUsername(String username){
        for (User user:
             selectAll()) {
            if(user.getUsername().equalsIgnoreCase(username)) return user;
        }
        return  null;
    }
    public boolean checkUser(String username, String email){
        for (User user:
                selectAll()) {
            if(user.getUsername().equalsIgnoreCase(username) && user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }



}
