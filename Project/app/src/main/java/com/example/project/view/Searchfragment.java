package com.example.project.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;
import com.example.project.service.ChapterService;
import com.example.project.service.StoryService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Searchfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Searchfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private StoryService storyService;

    private ChapterService chapterService;

    public Searchfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Searchfragment newInstance(String param1, String param2) {
        Searchfragment fragment = new Searchfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        storyService = new StoryService(getContext());
        chapterService = new ChapterService(getContext());

        Bundle args = getArguments();
        if (args != null) {
            String searchQuery = args.getString("search_query", "");
            List<Story> storyList = storyService.searchStoriesByTitle(searchQuery);
            GridLayout gridLayout = view.findViewById(R.id.storySearchLayout);
            for (Story story : storyList) {
                List<String> listChapterTitle = chapterService.getListTitleChapterByIdStory(story.getIdstory());

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



                LinearLayout textLayout = new LinearLayout(getContext());
                textLayout.setOrientation(LinearLayout.VERTICAL);
                textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                TextView textTitle = new TextView(getContext());
                textTitle.setText(story.getTitle());
                textTitle.setTextSize(20);

                TextView textChapter= new TextView(getContext());

                String chapterText = listChapterTitle.get(listChapterTitle.size()-1).substring(0,8).trim();
                textChapter.setText("Cập nhật đến "+chapterText);
                textChapter.setTextSize(13);

                textLayout.addView(textTitle);
                textLayout.addView(textChapter);
                linearLayout.addView(imageView);
                linearLayout.addView(textLayout);

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
        return view;
    }
}