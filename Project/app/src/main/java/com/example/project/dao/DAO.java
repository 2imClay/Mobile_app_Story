package com.example.project.dao;

import java.util.List;

public interface  DAO <T> {
    public List<T> selectAll();
    public long insert(T t);
    public boolean update(String id);

    public boolean delete(String id);
}
