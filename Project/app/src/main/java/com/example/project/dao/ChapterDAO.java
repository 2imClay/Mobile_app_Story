package com.example.project.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.project.model.Chapter;
import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class ChapterDAO implements DAO<Chapter>{
    DatabaseHelper databaseHelper;
    StoryDAO storyDAO;
    public  ChapterDAO(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.storyDAO = new StoryDAO(context);
    }
    @Override
    public List<Chapter> selectAll() {
        List<Chapter> chapterList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chapters", null);

        if(cursor.moveToFirst()){
            do {
                String idChapter = cursor.getString(cursor.getColumnIndexOrThrow("idChapter"));
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String publishDate = cursor.getString(cursor.getColumnIndexOrThrow("publishDate"));
                int viewCount =Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("viewCount")));
                Chapter chapter = new Chapter(idChapter,idStory,title,content,publishDate,viewCount);
                chapterList.add(chapter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return chapterList;
    }

    public void selectAllByIdStory(String StoryId){
        List<Chapter> chapterList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chapters WHERE idStory = ?", new String[]{StoryId});
        if (cursor.moveToFirst()) {
            do {
                String idChapter = cursor.getString(cursor.getColumnIndexOrThrow("idChapter"));
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String publishDate = cursor.getString(cursor.getColumnIndexOrThrow("publishDate"));
                int viewCount = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("viewCount")));
                Chapter chapter = new Chapter(idChapter, idStory, title, content, publishDate, viewCount);
                chapterList.add(chapter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
    @Override
    public boolean insert(Chapter chapter) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long newRowId = -1;
        try {
            String sql = "INSERT INTO chapters (idChapter, idStory, title, content, publishDate, viewCount) VALUES (?, ?, ?, ?, ?, ?)";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindString(1, chapter.getId());
            stmt.bindString(2, chapter.getStoryId());
            stmt.bindString(3, chapter.getTitle());
            stmt.bindString(4, chapter.getContent());
            stmt.bindString(5, chapter.getPublishedDate());
            stmt.bindLong(6, chapter.getViewCount());
            newRowId = stmt.executeInsert();
        } catch (Exception e) {
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
