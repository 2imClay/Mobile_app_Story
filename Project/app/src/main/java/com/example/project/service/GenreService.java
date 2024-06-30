package com.example.project.service;

import android.content.Context;

import com.example.project.dao.GenreDAO;
import com.example.project.model.Genre;

import java.util.List;

public class GenreService implements IService<Genre> {

    private GenreDAO dao;

    public GenreService(Context context){
        dao = new GenreDAO(context);
    }
    @Override
    public List<Genre> getAll() {
        return dao.selectAll();
    }

    @Override
    public long insert(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public boolean update(String id) {
        return dao.update(id);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }
    public Genre getGenreById(String idGenre){
        return dao.getGenreById(idGenre);
    }
    public List<String> getIdStoryById(String idGenre){
        return dao.selectIdStoryById(idGenre);
    }
}
