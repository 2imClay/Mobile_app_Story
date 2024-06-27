package com.example.project.model;

import java.io.Serializable;

public class Story implements Serializable {
    private String idstory;
    private String title;
    private String author;
    private String description;
    private Genre genre;
    private String imageurl;
    private int iscompleted;
    private int viewcount;

    public Story(String idstory, String title, String author, String description,  String imageurl, int iscompleted, int viewcount) {
        this.idstory = idstory;
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageurl = imageurl;
        this.iscompleted = iscompleted;
        this.viewcount = viewcount;
    }

    public String getIdstory() {
        return idstory;
    }

    public void setIdstory(String idstory) {
        this.idstory = idstory;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int isIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(int iscompleted) {
        this.iscompleted = iscompleted;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    @Override
    public String toString() {
        return "Story{" +
                "idstory='" + idstory + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

