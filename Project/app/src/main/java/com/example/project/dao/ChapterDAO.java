package com.example.project.dao;

import android.content.ContentValues;
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
//                String publishDate = cursor.getString(cursor.getColumnIndexOrThrow("publishDate"));
//                int viewCount =Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("viewCount")));
                Chapter chapter = new Chapter(idChapter,idStory,title,content);
                chapterList.add(chapter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return chapterList;
    }

    public List<String> listTitleChapter(String storyID){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT title  FROM chapters WHERE idStory = ?", new String[]{storyID});
        if (cursor.moveToFirst()) {
            do{
                String idChapter = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                list.add(idChapter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
            return list;
    }
    public List<Chapter> selectAllByIdStory(String StoryId){
        List<Chapter> chapterList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chapters WHERE idStory = ?", new String[]{StoryId});
        if (cursor.moveToFirst()) {
            do {
                String idChapter = cursor.getString(cursor.getColumnIndexOrThrow("idChapter"));
                String idStory = cursor.getString(cursor.getColumnIndexOrThrow("idStory"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                Chapter chapter = new Chapter(idChapter, idStory, title, content);
                chapterList.add(chapter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return chapterList;
    }
    @Override
    public long insert(Chapter chapter) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
            values.put("idChapter", chapter.getId());
            values.put("idStory", chapter.getStoryId());
            values.put("title", chapter.getTitle());
            values.put("content", chapter.getContent());
            values.put("publishDate", chapter.getPublishedDate());
            values.put("viewCount", chapter.getViewCount());

        return db.insert("chapters", null, values);
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
