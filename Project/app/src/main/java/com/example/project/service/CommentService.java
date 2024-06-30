package com.example.project.service;

import android.content.Context;

import com.example.project.dao.CommentDAO;
import com.example.project.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentService implements IService<Comment>{

    CommentDAO dao;

    public CommentService(Context context){
        dao = new CommentDAO(context);
    }
    @Override
    public List<Comment> getAll() {
        return dao.selectAll();
    }

    @Override
    public long insert(Comment comment) {
        return dao.insert(comment);
    }

    @Override
    public boolean update(String id) {
        return dao.update(id);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }

    public ArrayList<String> getCommentByIdStory(String idStory){
        return dao.getContent(idStory);
    }
}
