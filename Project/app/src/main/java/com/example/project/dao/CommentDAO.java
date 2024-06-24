package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.model.Comment;

import java.util.List;

public class CommentDAO implements DAO<Comment>{
    private DatabaseHelper databaseHelper;
    public  CommentDAO(Context context){
        this.databaseHelper = new DatabaseHelper(context);
    }
    @Override
    public List<Comment> selectAll() {
        return null;
    }

    @Override
    public long insert(Comment comment) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
//        values.put("username", user.getUsername());
//        values.put("password", user.getPassword());
//        values.put("email", user.getEmail());

        return db.insert("comments", null, values);
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
