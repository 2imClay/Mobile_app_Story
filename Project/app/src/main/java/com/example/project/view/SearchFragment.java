package com.example.project.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment{
        private StoryDAO dao;
        private GridLayout storyLayout;

        public SearchFragment(Context context) {
            this.dao = new StoryDAO(context);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.search_fragment, container, false);
            storyLayout = view.findViewById(R.id.storyLayout);
            return view;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
            inflater.inflate(R.menu.search_menu, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    List<Story> filteredStories = filterStories(query);
                    updateGridLayout(filteredStories);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }

        private List<Story> filterStories(String query) {
            return dao.searchByKeyword(query);
        }

        private void updateGridLayout(List<Story> stories) {
            storyLayout.removeAllViews();
            for (Story story : stories) {
                View storyView = LayoutInflater.from(getContext()).inflate(R.layout.story_item, storyLayout, false);
                // Gán giá trị cho các view con trong layout story_item
                // TextView title = storyView.findViewById(R.id.storyTitle);
                // title.setText(story.getTitle());
                storyLayout.addView(storyView);
            }
        }
}
