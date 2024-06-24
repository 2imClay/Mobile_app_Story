package com.example.project.model;

import java.util.List;

public class User {

    private String email;

    private String username;
    private String password;

    private String role;

    private String srcImg;
    private List<Story> favouriteStories;
    private List<Story> readStories;
    private List<Comment> comments;

    public User(String username,   String password,String email) {

        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String username, String password, String role, String srcImg, List<Story> favouriteStories, List<Story> readStories, List<Comment> comments) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.srcImg = srcImg;
        this.favouriteStories = favouriteStories;
        this.readStories = readStories;
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }

    public List<Story> getFavouriteStories() {
        return favouriteStories;
    }

    public void setFavouriteStories(List<Story> favouriteStories) {
        this.favouriteStories = favouriteStories;
    }

    public List<Story> getReadStories() {
        return readStories;
    }

    public void setReadStories(List<Story> readStories) {
        this.readStories = readStories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
