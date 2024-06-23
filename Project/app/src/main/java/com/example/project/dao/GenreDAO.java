package com.example.project.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.project.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DAO<Genre>{
    private DatabaseHelper databaseHelper;

    public GenreDAO(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }
    @Override
    public List<Genre> selectAll() {
        List<Genre> genres = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM genres", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("idGenre"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                genres.add(new Genre(id, name));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return genres;
    }

    @Override
    public boolean insert(Genre genre) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long newRowId= -1;
        try {
            String sql = "INSERT INTO genres(idGenre,name) VALUES(?,?)";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindString(1,genre.getIdgenre());
            stmt.bindString(2,genre.getName());
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

    public Genre getGenreById(String idGenre){
        for (Genre genre: selectAll()
             ) {
            if (genre.getIdgenre().equalsIgnoreCase(idGenre)){
                return genre;
            }else return null;
        }
        return null;
    }

    public static void main(String[] args) {

//        List<Genre> list = dao.selectAll();
//        for (Genre genre : list){
//            System.out.println(genre.toString());
//        }
    }
}