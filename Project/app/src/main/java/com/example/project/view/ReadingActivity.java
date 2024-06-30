package com.example.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.dao.ChapterDAO;
import com.example.project.model.Chapter;
import com.example.project.model.Story;

import java.util.List;

public class ReadingActivity extends AppCompatActivity {

    private TextView textView_nameStory,textView_nameChapter,editText_ReadingMain;
    private ImageButton imgbtn_back;
    private AutoCompleteTextView autoCompleteTextView;
    private ChapterDAO chapterDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.readding_layout);
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("Story");
        Chapter chapter = (Chapter) intent.getSerializableExtra("Chapter");
        chapterDAO = new ChapterDAO(this);
        List<String> listChapters = chapterDAO.listTitleChapter(story.getIdstory());
        List<Chapter> chapters = chapterDAO.selectAllByIdStory(story.getIdstory());


        //ánh xạ
        textView_nameStory = findViewById(R.id.textView_nameStory);
        textView_nameChapter = findViewById(R.id.textView_nameChapter);
        editText_ReadingMain = findViewById(R.id.editText_ReadingMain);
        imgbtn_back = findViewById(R.id.imgbtn_back);

        //action
        textView_nameStory.setText(story.getTitle());
        textView_nameChapter.setText(chapter.getTitle());
        editText_ReadingMain.setText(chapter.getContent());

       autoCompleteTextView = findViewById(R.id.autoCompleteTextViewBot);
        autoCompleteTextView.setText(chapter.getTitle());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listChapters);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chapterTitle = listChapters.get(position);

                for (Chapter chapter: chapters
                ) {
                    if(chapter.getTitle().equalsIgnoreCase(chapterTitle)){
                        textView_nameStory.setText(story.getTitle());
                        textView_nameChapter.setText(chapter.getTitle());
                        editText_ReadingMain.setText(chapter.getContent());

                    }
                }


            }
        });

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}