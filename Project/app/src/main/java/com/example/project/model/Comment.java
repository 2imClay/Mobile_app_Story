package com.example.project.model;

import java.sql.Date;

public class Comment {
    private int id;
    private int userId;
    private int storyId;
    private String content;
    private Date commentedDate;

    public Comment(int id, int userId, int storyId, String content, Date commentedDate) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.content = content;
        this.commentedDate = commentedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentedDate() {
        return commentedDate;
    }

    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = commentedDate;
    }
}
