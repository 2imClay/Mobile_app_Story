package com.example.project.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.dao.ChapterDAO;
import com.example.project.dao.CommentDAO;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Chapter;
import com.example.project.model.Comment;
import com.example.project.model.Story;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

import java.util.ArrayList;
import java.util.List;

public class Item_content_Activity extends AppCompatActivity {
    EditText etxt_cmt;
    Button btn_cmt;
    ListView listView_cmt;
    ArrayList<String> arrList_cmt=null;
    ArrayAdapter<String> adapter_cmt=null;
    private ImageView ic_back;
    private TextView textViewAuthor,textViewNameStory;
    private Button btn_read;
    private boolean isFilled = false;
    private ChapterDAO daoChapter;
    private StoryDAO daoStory;

    private CommentDAO daoComment;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("Story");

        daoChapter =new ChapterDAO(this);
        daoStory = new StoryDAO(this);
        daoComment = new CommentDAO(this);
        userPreferences = new UserPreferences(this);
        User user = userPreferences.getUser();
        List<Story> storyList = new ArrayList<>();

        List<Chapter> chapters = daoChapter.selectAllByIdStory(story.getIdstory());
      List<String> listChapterTitle = daoChapter.listTitleChapter(story.getIdstory());
        for (String title: listChapterTitle
             ) {
            System.out.println(title);
        }
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_content_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.item), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ic_back = findViewById(R.id.search_back);
        ImageView imageView = findViewById(R.id.imgStory);
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                200,
                0,
                1
        );
        imageViewParams.setMargins(0, 5, 0, 0);
        imageView.setLayoutParams(imageViewParams);

        textViewAuthor  = findViewById(R.id.textViewAuthor);
        textViewNameStory = findViewById(R.id.textViewNameStory);

        textViewNameStory.setText(story.getTitle());
        textViewAuthor.setText(story.getAuthor());
        int resId = getResources().getIdentifier(story.getImageurl(), "drawable", this.getPackageName());



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt_noiDungChinh = findViewById(R.id.txt_noiDungChinh);

        String text = story.getDescription();

        txt_noiDungChinh.setText(text);



        ListView lv=(ListView) findViewById(R.id.list_chapter);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, listChapterTitle);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chapterTitle = listChapterTitle.get(position);
                Intent intent = new Intent(Item_content_Activity.this, Reading_Activity.class);
                for (Chapter chapter: chapters
                     ) {
                    if(chapter.getTitle().equalsIgnoreCase(chapterTitle)){
                        intent.putExtra("Chapter", chapter);

                    }
                }
                intent.putExtra("Story", story);

                startActivity(intent);
            }
        });

        etxt_cmt = (EditText) findViewById(R.id.etxt_cmt);
        btn_cmt = (Button) findViewById(R.id.btn_cmt);
        listView_cmt = (ListView) findViewById(R.id.list_cmt);

        arrList_cmt = daoComment.getContent(story.getIdstory());
        adapter_cmt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList_cmt);
        listView_cmt.setAdapter(adapter_cmt);

        btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user == null){
                    Toast.makeText(Item_content_Activity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }else {
                    String content = user.getUsername()+": "+ etxt_cmt.getText();
                    Comment comment = new Comment(user.getUsername(),story.getIdstory(),content);
                    daoComment.insert(comment);
                    arrList_cmt.add(user.getUsername()+": "+ etxt_cmt.getText());
                    adapter_cmt.notifyDataSetChanged();


                }

            }
        });

        ImageView heartImageView = findViewById(R.id.heart);

        if(user!=null){
            storyList = daoStory.getFavoriteStory(user.getUsername());

            for (Story str: storyList
                 ) {
                if(str.getIdstory().equalsIgnoreCase(story.getIdstory())){
                    System.out.println(true);
                    isFilled = true;
                    heartImageView.setImageResource(R.drawable.heart_filled);

                }
            }
        }
        heartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null){
                    Toast.makeText(Item_content_Activity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();

                }else{
                    isFilled = !isFilled;

                    if (isFilled) {
                        heartImageView.setImageResource(R.drawable.heart_filled);
                        Toast.makeText(Item_content_Activity.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                        daoStory.insertFavorite(user.getUsername(),story.getIdstory());
                    } else {
                        daoStory.deleteFavorite(user.getUsername(),story.getIdstory());
                        heartImageView.setImageResource(R.drawable.heart_outline);
                        Toast.makeText(Item_content_Activity.this, "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item_content_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_read = findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent readIntent = new Intent(Item_content_Activity.this, Reading_Activity.class);
                readIntent.putExtra("Story", story);
                readIntent.putExtra("Chapter", chapters.get(0));
                startActivity(readIntent);
            }
        });

    }
}