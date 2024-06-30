package com.example.project;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.dao.GenreDAO;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Genre;
import com.example.project.model.Story;
import com.example.project.service.GenreService;
import com.example.project.service.StoryService;
import com.example.project.view.ItemContentActivity;

import java.util.List;

public class GenreDetail extends AppCompatActivity {

    public TextView textViewGenre;



    private GenreService genreService;

    private StoryService storyService;

    private ImageButton imgBtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.genre_detail_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewGenre = findViewById(R.id.textViewGenre);
        imgBtn_back = findViewById(R.id.imgBtn_back);
        storyService = new StoryService(this);
        genreService = new GenreService(this);

        Intent intent = getIntent();
        Genre genre = (Genre) intent.getSerializableExtra("Genre");

        List<String> strGenreList = genreService.getIdStoryById(genre.getIdgenre());
        System.out.println(strGenreList.toArray());
        List<Story> stories = storyService.getStoryById(strGenreList);

        textViewGenre.setText(genre.getName());

        imgBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GridLayout gridLayout = findViewById(R.id.gridListStory);
        for (Story story : stories) {
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 8, 0, 12);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(180, 220);

            imageLayoutParams.setMargins(8, 0, 8, 0);
            imageView.setLayoutParams(imageLayoutParams);

            int resId = getResources().getIdentifier(story.getImageurl(), "drawable",this.getPackageName());

            if (resId == 0) {
                Log.e(TAG, "Không tìm thấy tài nguyên hình ảnh: " + story.getImageurl());
                continue;
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            TextView textView = new TextView(this);
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


    }
}