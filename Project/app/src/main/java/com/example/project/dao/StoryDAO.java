package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.project.model.Chapter;
import com.example.project.model.Genre;
import com.example.project.model.Story;
import com.example.project.model.User;

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
                int isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow("isCompleted"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int viewCount = cursor.getInt(cursor.getColumnIndexOrThrow("viewCount"));

                stories.add(new Story(idstory,title,author,description,imgUrl,isCompleted,viewCount));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return stories;
    }

    public List<Story> findByKeywordTitle(String keyword){
        List<Story> list = new ArrayList<Story>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stories WHERE title LIKE ? ", new String[]{"%" + keyword + "%"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String idstory = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String imgUrl = cursor.getString(cursor.getColumnIndexOrThrow("imgURL"));
                int isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow("isCompleted"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int viewCount = cursor.getInt(cursor.getColumnIndexOrThrow("viewCount"));

                Story story = new Story(idstory,title,author,description,imgUrl,isCompleted,viewCount);
                list.add(story);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<Story> findByKeywordAuthor(String keyword){
        List<Story> list = new ArrayList<Story>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stories WHERE author LIKE ? ", new String[]{"%" + keyword + "%"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String idstory = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String imgUrl = cursor.getString(cursor.getColumnIndexOrThrow("imgURL"));
                int isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow("isCompleted"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int viewCount = cursor.getInt(cursor.getColumnIndexOrThrow("viewCount"));

                Story story = new Story(idstory,title,author,description,imgUrl,isCompleted,viewCount);
                list.add(story);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<Story> searchByKeyword(String keyword){
        List<Story> list = new ArrayList<Story>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stories WHERE author LIKE ? OR title LIKE ? ", new String[]{"%" + keyword + "%", "%" + keyword + "%"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String idstory = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String imgUrl = cursor.getString(cursor.getColumnIndexOrThrow("imgURL"));
                int isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow("isCompleted"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int viewCount = cursor.getInt(cursor.getColumnIndexOrThrow("viewCount"));

                Story story = new Story(idstory,title,author,description,imgUrl,isCompleted,viewCount);
                list.add(story);
            } while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public long insert(Story story) {
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        long newRowId = -1;
//        try {
//            String sql = "INSERT INTO stories(id,tittle,author,imgURL,description,genres,isCompleted,viewCount) VALUES(?,?,?,?,?,?,?,?)";
//            SQLiteStatement stmt = db.compileStatement(sql);
//            stmt.bindString(1, story.getIdstory());
//            stmt.bindString(2,story.getTitle());
//            stmt.bindString(3,story.getAuthor());
//            stmt.bindString(4, story.getImageurl());
//            stmt.bindString(5,story.getDescription());
//            stmt.bindString(6, story.getGenre().getIdgenre());
//            stmt.bindLong(7,story.isIscompleted());
//            stmt.bindLong(8, story.getViewcount());
//            newRowId = stmt.executeInsert();
//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//        return newRowId > 0;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
//        values.put("username", user.getUsername());
//        values.put("password", user.getPassword());
//        values.put("email", user.getEmail());

        return db.insert("stories", null, values);
    }

    @Override
    public boolean update(String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public List<String> getFavoriteStory_Helper(String username){
        List<String> favories = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM favorite_stories WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                favories.add(id);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favories;
    }
    public List<Story> getFavoriteStory(String username){
        List<Story> stories = new ArrayList<>();
        List<String> helper = getFavoriteStory_Helper(username);
        for (Story story: selectAll()
             ) {
            if(helper.contains(story.getIdstory())){
                stories.add(story);
            }
        }
        return stories;
    }
    public long insertFavorite(String username, String idStory) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("idStory", idStory);

        return db.insert("favorite_stories", null, values);
    }
    public long deleteFavorite(String username, String idStory){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String selection = "username = ? AND idStory = ?";
        String[] selectionArgs = { username, idStory };
        return db.delete("favorite_stories", selection, selectionArgs);
    }

    public long insertHistory(String username, String idStory){
    SQLiteDatabase db = databaseHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("username", username);
    values.put("idStory", idStory);

    return db.insert("history_stories", null, values);
    }

    public List<String> getHistoryStory_Helper(String username){
        List<String> history = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM history_stories WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        if (cursor.moveToFirst()) {
            do {
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                history.add(idStory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return history;
    }
    public List<Story> getHistoryStory(String username){
        List<Story> stories = new ArrayList<>();
        List<String> helper = getHistoryStory_Helper(username);
        for (Story story: selectAll()
        ) {
            if(helper.contains(story.getIdstory())){
                stories.add(story);
            }
        }
        return stories;
    }
}
