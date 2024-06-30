package com.example.project.service;

import java.util.List;

public interface IService<T> {

    public List<T> getAll();
    public long insert(T t);
    public boolean update(String id);

    public boolean delete(String id);
}
