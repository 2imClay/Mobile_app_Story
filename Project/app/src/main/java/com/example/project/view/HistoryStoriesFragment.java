package com.example.project.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

public class HistoryStoriesFragment extends Fragment {
    private UserPreferences userPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userPreferences = new UserPreferences(getContext());
        User user = userPreferences.getUser();

        View view = inflater.inflate(R.layout.history_stories_read_layout, container, false);
        // Khởi tạo và hiển thị nội dung của popular stories ở đây
        return view;
    }
}
