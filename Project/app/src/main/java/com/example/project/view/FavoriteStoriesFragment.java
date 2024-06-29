package com.example.project.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.dao.UserDAO;
import com.example.project.model.Story;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

import java.util.List;

public class FavoriteStoriesFragment extends Fragment {
    private UserDAO userDAO;
    private DatabaseHelper dbHelper;
    private UserPreferences userPreferences;
    private StoryDAO daoStory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_stories_layout, container, false);
        userPreferences = new UserPreferences(getContext());
        User user = userPreferences.getUser();
        dbHelper = new DatabaseHelper(getContext());
        daoStory = new StoryDAO(getContext());

        List<Story> storyList = daoStory.getFavoriteStory(user.getUsername());
        GridLayout gridLayout = view.findViewById(R.id.storyLayout);
        for (Story story : storyList) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 8, 0, 12);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(180, 220);

            imageLayoutParams.setMargins(8, 0, 8, 0);
            imageView.setLayoutParams(imageLayoutParams);

            int resId = getResources().getIdentifier(story.getImageurl(), "drawable", getContext().getPackageName());

            if (resId == 0) {
                Log.e(TAG, "Không tìm thấy tài nguyên hình ảnh: " + story.getImageurl());
                continue;
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            TextView textView = new TextView(getContext());
            textView.setText(story.getTitle());
            textView.setTextSize(20);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);

           linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemContentActivity.class);
                    intent.putExtra("Story", story);
                    context.startActivity(intent);
                }
            });

            gridLayout.addView(linearLayout);
        }

        return view;
    }
}
