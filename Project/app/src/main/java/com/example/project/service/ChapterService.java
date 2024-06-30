package com.example.project.service;

import android.content.Context;

import com.example.project.dao.ChapterDAO;
import com.example.project.model.Chapter;

import java.util.List;

public class ChapterService implements IService<Chapter> {

    ChapterDAO dao;
    public ChapterService(Context context){
        dao = new ChapterDAO(context);
    }
    @Override
    public List<Chapter> getAll() {
        return dao.selectAll();
    }

    @Override
    public long insert(Chapter chapter) {
        return dao.insert(chapter);
    }

    @Override
    public boolean update(String id) {
        return dao.update(id);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }

    public List<String> getListTitleChapterByIdStory(String storyID){
        return dao.listTitleChapter(storyID);
    }

    public List<Chapter> getAllByIdStory(String storyId){
        return dao.selectAllByIdStory(storyId);
    }
}
