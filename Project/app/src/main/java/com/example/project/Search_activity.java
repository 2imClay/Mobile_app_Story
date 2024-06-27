package com.example.project;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;
import com.example.project.view.Persion_Layout;
import com.example.project.view.Search_fragment;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

public class Search_activity extends AppCompatActivity {

    private EditText text_search;
    private ImageButton ic_search,search_back;


    private StoryDAO daoStory;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        text_search = findViewById(R.id.text_search);
        ic_search = findViewById(R.id.ic_search);
        search_back = findViewById(R.id.search_back);
        dbHelper = new DatabaseHelper(this);
        daoStory = new StoryDAO(this);
        String str = getIntent().getStringExtra("query");
       if(str!=null){
           text_search.setText(str);
           String text = text_search.getText().toString().trim();
           text = normalizeString(text);
           Bundle bundle = new Bundle();
           bundle.putString("search_query", text);

           // Tạo Fragment mới và truyền dữ liệu qua Bundle
           Search_fragment fragment = new Search_fragment();
           fragment.setArguments(bundle);

           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragment_contain_search, fragment)
                   .commit();
       }

        text_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = text_search.getText().toString().trim();
                text = normalizeString(text);

                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    if(text.isEmpty()){
                        Toast.makeText(Search_activity.this, "Bạn chưa nhập dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("search_query", text);

                        // Tạo Fragment mới và truyền dữ liệu qua Bundle
                        Search_fragment fragment = new Search_fragment();
                        fragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_contain_search, fragment)
                                .commit();
                    }
                    return true;
                }
                return false;
            }
        });

        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = text_search.getText().toString().trim();
                text = normalizeString(text);
                if(text.isEmpty()){
                    Toast.makeText(Search_activity.this, "Bạn chưa nhập dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    List<Story> storyList = daoStory.selectStoryByWord(text);

                    List<String> str = daoStory.selectAllTitle();

                    Bundle bundle = new Bundle();
                    bundle.putString("search_query", text);

                    Search_fragment fragment = new Search_fragment();
                    fragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_contain_search, fragment)
                            .commit();
                }
            }
        });
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static String normalizeString(String input) {
        // Chuẩn hóa chuỗi Unicode
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFC);
        // Chuyển về chữ thường
        normalized = normalized.toLowerCase(Locale.ROOT);
        return normalized;
    }

}