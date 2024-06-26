package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.model.Comment;
import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements DAO<Comment>{
    private DatabaseHelper databaseHelper;
    public  CommentDAO(Context context){
        this.databaseHelper = new DatabaseHelper(context);
    }
    @Override
    public List<Comment> selectAll() {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM comments ", null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                comments.add(new Comment(username,idStory,content));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return comments;
    }

    @Override
    public long insert(Comment comment) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", comment.getUserId());
        values.put("idStory", comment.getStoryId());
        values.put("content",comment.getContent());

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
    public ArrayList<Comment> getCommentByIdStory(String id){
        ArrayList<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM comments WHERE idStory=?";
        Cursor cursor = db.rawQuery(query, new String[]{id});
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                comments.add(new Comment(username,idStory,content));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return comments;
    }
    public ArrayList<String> getContent(String id){
       ArrayList<String> strings = new ArrayList<>();
        for (Comment comment: getCommentByIdStory(id)
             ) {
            strings.add(comment.getContent());
        }
        return strings;
    }
}
