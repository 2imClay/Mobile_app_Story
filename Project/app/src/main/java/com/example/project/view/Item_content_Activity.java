package com.example.project.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.project.model.Story;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

import java.util.ArrayList;

public class Item_content_Activity extends AppCompatActivity {
    EditText etxt_cmt;
    Button btn_cmt;
    ListView listView_cmt;
    ArrayList<String> arrList_cmt=null;
    ArrayAdapter<String> adapter_cmt=null;
    private ImageView ic_back;
    private TextView textViewAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("Story");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_content_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.item), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        UserPreferences userPreferences = new UserPreferences(this);
        User user = userPreferences.getUser();

        ic_back = findViewById(R.id.ic_back);
        ImageView imageView = findViewById(R.id.imgStory);
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                200,
                0,
                1
        );
        imageViewParams.setMargins(0, 5, 0, 0);
        imageView.setLayoutParams(imageViewParams);

        textViewAuthor  = findViewById(R.id.textViewAuthor);
        textViewAuthor.setText(story.getAuthor());
        int resId = getResources().getIdentifier(story.getImageurl(), "drawable", this.getPackageName());



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt_noiDungChinh = findViewById(R.id.txt_noiDungChinh);

        String text = story.getDescription();

        txt_noiDungChinh.setText(text);


        //1. Khởi tạo dữ liệu cho mảng arr (còn gọi là data source)
        final String[] arr ={"Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5","Chapter 6","Chapter 7","Chapter 8"};
        //2. Lấy đối tượng Listview dựa vào id
        ListView lv=(ListView) findViewById(R.id.list_chapter);
        //3. Gán Data source vào ArrayAdapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        //4. Đưa Data source vào ListView
        lv.setAdapter(adapter);


        etxt_cmt = (EditText) findViewById(R.id.etxt_cmt);
        btn_cmt = (Button) findViewById(R.id.btn_cmt);
        listView_cmt = (ListView) findViewById(R.id.list_cmt);

        arrList_cmt = new ArrayList<String>();
        adapter_cmt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList_cmt);
        listView_cmt.setAdapter(adapter_cmt);

        btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user == null){
                    Toast.makeText(Item_content_Activity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }else {
                    arrList_cmt.add(user.getUsername()+": "+ etxt_cmt.getText());
                    adapter_cmt.notifyDataSetChanged();
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

    }
}