package com.example.project.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment{
    DatabaseHelper databaseHelper;
    private StoryDAO dao;

    List<Story> stories;
     ListView listView;

     public SearchFragment(Context context){
         this.dao = new StoryDAO(context);
         this.stories = new ArrayList<>();

     }
}
