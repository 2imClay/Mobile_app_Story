package com.example.project.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.GenreDetail;
import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.GenreDAO;
import com.example.project.model.Genre;

import java.util.List;

public class ListGenresFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private GenreDAO genreDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getContext());
        genreDAO = new GenreDAO(getContext());
        View view = inflater.inflate(R.layout.list_genres, container,false);
        display(view);

        return view;
    }


    public void display(View view) {
        GridLayout gridLayout = view.findViewById(R.id.list_genres);
        List<Genre> genres = genreDAO.selectAll();
        int totalGenres = genres.size();

        for (int i = 0; i < totalGenres; i++) {
            Genre genre = genres.get(i);
            LinearLayout genreLayout = new LinearLayout(getContext());

            GridLayout.LayoutParams gridLayoutParams = new GridLayout.LayoutParams();
            gridLayoutParams.width = 0;
            gridLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            gridLayoutParams.columnSpec = GridLayout.spec(i % 2, 1f);
            gridLayoutParams.setMargins(12, 12, 12, 12);
            genreLayout.setLayoutParams(gridLayoutParams);

            genreLayout.setOrientation(LinearLayout.VERTICAL);
            genreLayout.setBackgroundResource(R.drawable.button_boder);
            genreLayout.setPadding(4, 8, 4, 8);

            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(textViewParams);
            textView.setText(genre.getName());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(getResources().getColor(R.color.black));

            genreLayout.addView(textView);
            genreLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, GenreDetail.class);
                    intent.putExtra("Genre", genre);
                    context.startActivity(intent);
                }
            });
            gridLayout.addView(genreLayout);

        }
    }
}
