package com.example.project.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;

    private String username;
    private String password;

    private String role;

    private List<Story> favouriteStories;
    private List<Story> readStories;
    private List<Comment> comments;

    public User(int id, String name, String email, String username, String password,String role, List<Story> favouriteStories, List<Story> readStories, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.favouriteStories = favouriteStories;
        this.readStories = readStories;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
