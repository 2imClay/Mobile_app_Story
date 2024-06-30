package com.example.project.service;

import android.content.Context;

import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;

import java.util.List;

public class StoryService implements IService<Story> {
    private StoryDAO dao;

    public StoryService(Context context){
      dao = new StoryDAO(context);
    }

    @Override
    public List<Story> getAll() {
        return dao.selectAll();
    }

    @Override
    public long insert(Story story) {
        return dao.insert(story);
    }

    @Override
    public boolean update(String id) {
        return dao.update(id);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }

    public List<Story> getFavoriteStories(String username){
        return dao.getFavoriteStory(username);
    }

    public long insertFavoriteStory(String username, String idStory){
        return dao.insertFavorite(username,idStory);
    }

    public long deleteFavoriteStory(String username, String idStory){
        return dao.deleteFavorite(username,idStory);
    }


    public long insertHistoryStory(String username, String idStory){
        return dao.insertHistory(username,idStory);
    }

    public List<Story> getHistoryStories(String username){
        return dao.getHistoryStory(username);
    }

    public List<Story> getStoryById(List<String> strGenre){
        return dao.getStoryById(strGenre);
    }

    public List<Story> searchStoriesByTitle(String keyword){
        return  dao.searchStoriesByTitle(keyword);
    }

    public List<String> getAllStoryByTitle(){
        return dao.selectAllTitle();
    }

    public List<Story> findStoryByKeyword(String keyword){
        return dao.selectStoryByWord(keyword);
    }
}
