package com.example.project.model;

import java.sql.Date;

public class Chapter {
    private int id;
    private int storyId;
    private int chapterNumber;
    private String title;
    private String content;
    private Date publishedDate;
    private int viewCount;

    public Chapter(int id, int storyId, int chapterNumber, String title, String content, Date publishedDate, int viewCount) {
        this.id = id;
        this.storyId = storyId;
        this.chapterNumber = chapterNumber;
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.viewCount = viewCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
