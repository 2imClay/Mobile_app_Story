package com.example.project.dao;

import com.example.project.model.Comment;

import java.util.List;

public class CommentDAO implements DAO<Comment>{
    @Override
    public List<Comment> selectAll() {
        return null;
    }

    @Override
    public boolean insert(Comment comment) {
        return false;
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
