package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.model.Genre;
import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryDAO implements DAO<Story>{
    private DatabaseHelper databaseHelper;


    private GenreDAO genreDAO;
    public StoryDAO(Context context) {

        this.databaseHelper = new DatabaseHelper(context);
        genreDAO = new GenreDAO(context);
    }

    @Override
    public List<Story> selectAll() {
        List<Story> stories = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stories ", null);

        if (cursor.moveToFirst()) {
            do {
                String idstory = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String imgUrl = cursor.getString(cursor.getColumnIndexOrThrow("imgURL"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String genres = cursor.getString(cursor.getColumnIndexOrThrow("genres"));
                Genre genre = genreDAO.getGenreById(genres);

                stories.add(new Story(idstory,title,author,description,genre,imgUrl,false,0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return stories;
    }
}
