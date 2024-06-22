package com.example.project.model;

public class Story {
    private String idstory;
    private String title;
    private String author;
    private String description;
    private Genre genre;
    private String imageurl;
    private boolean iscompleted;
    private int viewcount;

    public Story(String idstory, String title, String author, String description, Genre genre, String imageurl, boolean iscompleted, int viewcount) {
        this.idstory = idstory;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.imageurl = imageurl;
        this.iscompleted = iscompleted;
        this.viewcount = viewcount;
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

    public boolean isIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }
}

