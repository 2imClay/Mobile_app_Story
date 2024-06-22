package com.example.project.model;

import java.sql.Date;

public class Chapter {
    private String id;
    private String storyId;
    private String title;
    private String content;
    private String publishedDate;
    private int viewCount;

    public Chapter(String id, String storyId, String title, String content, String publishedDate, int viewCount) {
        this.id = id;
        this.storyId = storyId;
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.viewCount = viewCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
