package com.example.project.model;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {
    private String userId;
    private String storyId;
    private String content;
    private Date commentedDate;

    public Comment( String userId, String storyId, String content) {
        this.userId = userId;
        this.storyId = storyId;
        this.content = content;
    }

    public Comment(String userId, String storyId, String content, Date commentedDate) {
        this.userId = userId;
        this.storyId = storyId;
        this.content = content;
        this.commentedDate = commentedDate;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
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
