package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;
import com.example.project.model.Story;

import java.util.List;

public class StoryAdapter extends BaseAdapter {
    private Context context;
    private List<Story> stories;

    public StoryAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.story_item, parent, false);
        }
        // Gán giá trị cho các view con trong layout story_item
        // TextView title = convertView.findViewById(R.id.storyTitle);
        // title.setText(stories.get(position).getTitle());
        return convertView;
    }

    public void updateStories(List<Story> newStories) {
        stories.clear();
        stories.addAll(newStories);
        notifyDataSetChanged();
    }
}
