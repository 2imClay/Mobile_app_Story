package com.example.project.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.dao.UserDAO;
import com.example.project.model.Story;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

import java.util.List;

public class PopularStoriesFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private StoryDAO daoStory;
    private UserDAO daoUser;
    private UserPreferences userPreferences;
    private  User user;
    private static final String TAG = "PopularStoriesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userPreferences = new UserPreferences(getContext());
        user = userPreferences.getUser();
        View view = inflater.inflate(R.layout.popular_stories_layout, container, false);
        dbHelper = new DatabaseHelper(getContext());
        daoStory = new StoryDAO(getContext());
        displayBooks(view);

        return view;
    }

    private void displayBooks(View view) {
        GridLayout gridLayout = view.findViewById(R.id.grid_layout);
        List<Story> stories = daoStory.selectAll();

        for (Story story : stories) {
            System.out.println(story.toString());

            LinearLayout storyLayout = new LinearLayout(getContext());

            GridLayout.LayoutParams gridLayoutParams = new GridLayout.LayoutParams();
            gridLayoutParams.width = 190;
            gridLayoutParams.height = 260;
            gridLayoutParams.setMargins(12, 12, 12, 12);
            storyLayout.setLayoutParams(gridLayoutParams);

            storyLayout.setOrientation(LinearLayout.VERTICAL);
            storyLayout.setBackgroundResource(R.drawable.button_boder);

            // Tạo TextView cho tên sách
            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(textViewParams);
            textView.setText(story.getTitle());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(getResources().getColor(R.color.black));

            // Tạo ImageView cho hình ảnh sách
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            imageViewParams.setMargins(0, 5, 0, 0);
            imageView.setLayoutParams(imageViewParams);

            // Lấy ID của tài nguyên hình ảnh từ tên trong cơ sở dữ liệu
            int resId = getResources().getIdentifier(story.getImageurl(), "drawable", getContext().getPackageName());

            // Kiểm tra nếu không tìm thấy tài nguyên hình ảnh
            if (resId == 0) {
                Log.e(TAG, "Không tìm thấy tài nguyên hình ảnh: " + story.getImageurl());
                continue; // Bỏ qua nếu không tìm thấy
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // Đảm bảo hình ảnh được cắt để vừa chiều rộng

            storyLayout.addView(imageView);
            storyLayout.addView(textView);

            storyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemContentActivity.class);
                    intent.putExtra("Story", story);
                    context.startActivity(intent);
                    System.out.println("đã chọn" + story.getIdstory() + " - " + user.getUsername());
                    daoStory.insertHistory(user.getUsername(), story.getIdstory());
                }
            });

            // Thêm LinearLayout vào GridLayout
            gridLayout.addView(storyLayout);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Thêm vào stack để có thể quay lại
        transaction.commit();
    }
    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
