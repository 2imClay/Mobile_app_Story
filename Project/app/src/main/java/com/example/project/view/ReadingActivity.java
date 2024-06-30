package com.example.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.dao.ChapterDAO;
import com.example.project.model.Chapter;
import com.example.project.model.Story;

import java.util.List;

public class ReadingActivity extends AppCompatActivity {

    private TextView textView_nameStory,textView_nameChapter,editText_ReadingMain;
    private ImageButton imgbtn_back, btn_previousChapter, btn_nextChapter;
    private AutoCompleteTextView autoCompleteTextView;
    private ChapterDAO chapterDAO;
    private Story story;
    private Chapter currentChapter;

    private List<String> listChapters;

    private List<Chapter> chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.readding_layout);
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("Story");
        currentChapter = (Chapter) intent.getSerializableExtra("Chapter");
        chapterDAO = new ChapterDAO(this);
        listChapters = chapterDAO.listTitleChapter(story.getIdstory());
        chapters = chapterDAO.selectAllByIdStory(story.getIdstory());


        //ánh xạ
        textView_nameStory = findViewById(R.id.textView_nameStory);
        textView_nameChapter = findViewById(R.id.textView_nameChapter);
        editText_ReadingMain = findViewById(R.id.editText_ReadingMain);
//        button
        imgbtn_back = findViewById(R.id.imgbtn_back);
        btn_previousChapter = findViewById(R.id.btn_previousChapterBot);
        btn_nextChapter = findViewById(R.id.btn_nextChaterBot);
        setEnableForButton();
        //action
        textView_nameStory.setText(story.getTitle());
        textView_nameChapter.setText(currentChapter.getTitle());
        editText_ReadingMain.setText(currentChapter.getContent());

        autoCompleteTextView = findViewById(R.id.autoCompleteTextViewBot);
        autoCompleteTextView.setText(currentChapter.getTitle());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listChapters);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String chapterTitle = listChapters.get(position);
//
//                for (Chapter chapter: chapters
//                ) {
//                    if(chapter.getTitle().equalsIgnoreCase(chapterTitle)){
//                        textView_nameStory.setText(story.getTitle());
//                        textView_nameChapter.setText(chapter.getTitle());
//                        editText_ReadingMain.setText(chapter.getContent());
//                        currentChapter = chapter;
//                    }
//                }
//
                setCompleteTextViewForCurrentChapter(position);
            }
        });

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        chuyen chapter

        btn_nextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chapter nextChapter = chapterDAO.selectNextChapter(story.getIdstory(), currentChapter.getId());
                if (nextChapter == null) {
                    Toast.makeText(ReadingActivity.this, "No more chapters", Toast.LENGTH_SHORT).show();
                } else {
                    textView_nameStory.setText(story.getTitle());
                    textView_nameChapter.setText(nextChapter.getTitle());
                    editText_ReadingMain.setText(nextChapter.getContent());
                    currentChapter = nextChapter;

                    editText_ReadingMain.setText(currentChapter.getContent());
                }

                setEnableForButton();
                setCompleteTextViewForCurrentChapter(getCurrentChapterOrder());
            }
        });
        btn_previousChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chapter previousChapter = chapterDAO.selectPreviousChapter(story.getIdstory(), currentChapter.getId());
                if (previousChapter == null) {
                    Toast.makeText(ReadingActivity.this, "No more chapters", Toast.LENGTH_SHORT).show();
                } else {
                    textView_nameStory.setText(story.getTitle());
                    textView_nameChapter.setText(previousChapter.getTitle());
                    editText_ReadingMain.setText(previousChapter.getContent());
                    currentChapter = previousChapter;

                    editText_ReadingMain.setText(currentChapter.getContent());
                }

                setEnableForButton();
                setCompleteTextViewForCurrentChapter(getCurrentChapterOrder());
            }
        });
    }
    private void setEnableForButton() {
//                kiem tra xem co phai la chapter dau hoac chapter cuoi khong
        int beginOrEnd = chapterDAO.checkIsBeginningOrEndingChapter(story.getIdstory(), currentChapter.getId());
        // Kiểm tra chapter hiện tại
        if (beginOrEnd == -1) {
            // Nếu là chương đầu tiên, vô hiệu hóa nút Previous
            btn_previousChapter.setEnabled(false);
            btn_nextChapter.setEnabled(true);
        } else if (beginOrEnd == 1) {
            // Nếu là chương cuối cùng, vô hiệu hóa nút Next
            btn_previousChapter.setEnabled(true);
            btn_nextChapter.setEnabled(false);
        } else {
            // Nếu không phải là chương đầu hoặc chương cuối, bật cả hai nút
            btn_previousChapter.setEnabled(true);
            btn_nextChapter.setEnabled(true);
        }
    }
    private void setCompleteTextViewForCurrentChapter(int position) {
        String chapterTitle = listChapters.get(position);

        for (Chapter chapter: chapters
        ) {
            if(chapter.getTitle().equalsIgnoreCase(chapterTitle)){
                textView_nameStory.setText(story.getTitle());
                textView_nameChapter.setText(chapter.getTitle());
                editText_ReadingMain.setText(chapter.getContent());
                currentChapter = chapter;
                setEnableForButton();
                autoCompleteTextView.setText(chapterTitle);
                autoCompleteTextView.clearFocus();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listChapters);
                autoCompleteTextView.setAdapter(adapter);
            }
        }
    }
    private int getCurrentChapterOrder() {
        for (int i = 0; i < chapters.size(); i++) {
            if (chapters.get(i).getId().equals(currentChapter.getId())) {
                return i;
            }
        }
        return -1; // Nếu không tìm thấy
    }
}