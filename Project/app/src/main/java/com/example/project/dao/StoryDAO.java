package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryDAO {
    private DatabaseHelper dbHelper;

    public StoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public static void onCreate(SQLiteDatabase db) {
        String CREATE_STORY_TABLE = "CREATE TABLE " + TABLE_STORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_TOTAL_CHAPTERS + " INTEGER,"
                + COLUMN_COVER_IMAGE_URL + " TEXT,"
                + COLUMN_IS_COMPLETED + " INTEGER,"
                + COLUMN_AVERAGE_RATING + " REAL,"
                + COLUMN_VIEW_COUNT + " INTEGER" + ")";
        db.execSQL(CREATE_STORY_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        onCreate(db);
    }

    // Tên bảng và các cột
    public static final String TABLE_STORY = "story";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_TOTAL_CHAPTERS = "totalChapters";
    public static final String COLUMN_COVER_IMAGE_URL = "coverImageUrl";
    public static final String COLUMN_IS_COMPLETED = "isCompleted";
    public static final String COLUMN_AVERAGE_RATING = "averageRating";
    public static final String COLUMN_VIEW_COUNT = "viewCount";

    // Thêm một story mới
    public void addStory(Story story) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, story.getTitle());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_DESCRIPTION, story.getDescription());
        values.put(COLUMN_GENRE, story.getGenre());
        values.put(COLUMN_TOTAL_CHAPTERS, story.getTotalChapters());
        values.put(COLUMN_COVER_IMAGE_URL, story.getCoverImageUrl());
        values.put(COLUMN_IS_COMPLETED, story.isCompleted() ? 1 : 0);
        values.put(COLUMN_AVERAGE_RATING, story.getAverageRating());
        values.put(COLUMN_VIEW_COUNT, story.getViewCount());

        db.insert(TABLE_STORY, null, values);
        db.close();
    }

    // Lấy một story theo ID
    public Story getStory(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STORY, new String[]{
                        COLUMN_ID,
                        COLUMN_TITLE,
                        COLUMN_AUTHOR,
                        COLUMN_DESCRIPTION,
                        COLUMN_GENRE,
                        COLUMN_TOTAL_CHAPTERS,
                        COLUMN_COVER_IMAGE_URL,
                        COLUMN_IS_COMPLETED,
                        COLUMN_AVERAGE_RATING,
                        COLUMN_VIEW_COUNT}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Story story = new Story(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6),
                cursor.getInt(7) > 0,
                cursor.getDouble(8),
                cursor.getInt(9)
        );

        cursor.close();
        db.close();
        return story;
    }

    // Lấy tất cả các story
    public List<Story> getAllStories() {
        List<Story> storyList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STORY;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Story story = new Story(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getInt(7) > 0,
                        cursor.getDouble(8),
                        cursor.getInt(9)
                );
                storyList.add(story);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return storyList;
    }

    // Cập nhật một story
    public int updateStory(Story story) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, story.getTitle());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_DESCRIPTION, story.getDescription());
        values.put(COLUMN_GENRE, story.getGenre());
        values.put(COLUMN_TOTAL_CHAPTERS, story.getTotalChapters());
        values.put(COLUMN_COVER_IMAGE_URL, story.getCoverImageUrl());
        values.put(COLUMN_IS_COMPLETED, story.isCompleted() ? 1 : 0);
        values.put(COLUMN_AVERAGE_RATING, story.getAverageRating());
        values.put(COLUMN_VIEW_COUNT, story.getViewCount());

        return db.update(TABLE_STORY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(story.getId())});
    }

    // Xóa một story
    public void deleteStory(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_STORY, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
