package com.example.project.view;

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

import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.StoryDAO;
import com.example.project.model.Story;

import java.util.List;

public class PopularStoriesFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private StoryDAO daoStory;
    private static final String TAG = "PopularStoriesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getContext());
        daoStory = new StoryDAO(getContext());
        View view = inflater.inflate(R.layout.popular_stories_layout, container, false);
        displayBooks(view);
        return view;
    }

    private void displayBooks(View view) {
        GridLayout gridLayout = view.findViewById(R.id.grid_layout);
        List<Story> books = daoStory.selectAll();

        for (Story book : books) {
            // Tạo LinearLayout cho từng sách
            LinearLayout bookLayout = new LinearLayout(getContext());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    new ViewGroup.LayoutParams(150, 200));
            params.setMargins(12, 12, 12, 12);
            bookLayout.setLayoutParams(params);
            bookLayout.setOrientation(LinearLayout.VERTICAL);
            bookLayout.setBackgroundResource(R.drawable.button_boder);

            // Tạo TextView cho tên sách
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    40 // Chiều cao cố định
            ));
            textView.setText("Truyện " + book.getTitle());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(getResources().getColor(R.color.black));

            // Tạo ImageView cho hình ảnh sách
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    160 // Chiều cao cố định
            ));

            // Lấy ID của tài nguyên hình ảnh từ tên trong cơ sở dữ liệu
            int resId = getResources().getIdentifier(book.getImageurl(), "drawable", getContext().getPackageName());

            // Kiểm tra nếu không tìm thấy tài nguyên hình ảnh
            if (resId == 0) {
                Log.e(TAG, "Không tìm thấy tài nguyên hình ảnh: " + book.getImageurl());
                continue; // Bỏ qua nếu không tìm thấy
            }

            // Đọc và hiển thị hình ảnh từ tài nguyên
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            imageView.setImageBitmap(bitmap);

            // Thêm ImageView và TextView vào LinearLayout
            bookLayout.addView(imageView);
            bookLayout.addView(textView);

            // Thêm sự kiện nhấn vào LinearLayout
//            bookLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Chapter_layout.class); // Đảm bảo tên Activity đúng
//                    intent.putExtra("BOOK_ID", book.getId()); // Truyền bookId là String
//                    startActivity(intent);
//                }
//            });

            // Thêm LinearLayout vào GridLayout
            gridLayout.addView(bookLayout);
        }
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
