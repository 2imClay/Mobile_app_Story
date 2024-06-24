package com.example.project.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class UserPreferences {

    private static final String USER_PREFS = "UserPrefs";
    private static final String USER_KEY = "User";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userJson = gson.toJson(user);
        editor.putString(USER_KEY, userJson);
        editor.apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString(USER_KEY, null);
        if (userJson != null) {
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }

    public void clearUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_KEY);
        editor.apply();
    }
}
