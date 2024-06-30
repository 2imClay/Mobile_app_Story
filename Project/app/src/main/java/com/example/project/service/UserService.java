package com.example.project.service;

import android.content.Context;

import com.example.project.dao.UserDAO;
import com.example.project.model.User;

import java.util.List;

public class UserService implements IService<User> {

    private UserDAO dao;

    public UserService(Context context){
        dao = new UserDAO(context);
    }
    @Override
    public List<User> getAll() {
        return dao.selectAll();
    }

    @Override
    public long insert(User user) {
        return dao.insert(user);
    }

    @Override
    public boolean update(String id) {
        return dao.update(id);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }

    public boolean authercationUser(String username, String password){
        return dao.authercationUser(username,password);
    }

    public boolean checkUser(String username){
        return dao.checkUser(username);
    }

    public User getUserByUsername(String username){
        return dao.getUserByUsername(username);
    }
}
