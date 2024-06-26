package com.example.project;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.model.User;
import com.example.project.model.UserPreferences;
import com.example.project.view.FavoriteStoriesFragment;
import com.example.project.view.HistoryStoriesFragment;
import com.example.project.view.NewStoriesFragment;
import com.example.project.view.NoLoginFragment;
import com.example.project.view.PopularStoriesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<TextView> textViews = new ArrayList<TextView>();
    boolean isLogin = false;
    private UserPreferences userPreferences;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        showFragment(new PopularStoriesFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView popularStories = view.findViewById(R.id.popularStories);
        TextView newStories = view.findViewById(R.id.newStories);
        TextView favoriteStories = view.findViewById(R.id.favoriteStories);
        TextView historyStoriesRead = view.findViewById(R.id.historyStoriesRead);

        textViews.add(popularStories);
        userPreferences = new UserPreferences(getContext());
        User user = userPreferences.getUser();
        if (user != null){
            isLogin = true;
        }
        changBackgroundColor(textViews);
        popularStories.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                textViews.add(popularStories);
                showFragment(new PopularStoriesFragment());
                changBackgroundColor(textViews);
            }
        });

        newStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews.add(newStories);
                showFragment(new NewStoriesFragment());
                changBackgroundColor(textViews);

            }
        });
        favoriteStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews.add(favoriteStories);
                if(isLogin){
                    showFragment(new FavoriteStoriesFragment());
                }else {
                    showFragment(new NoLoginFragment());
                }
                changBackgroundColor(textViews);

            }
        });
        historyStoriesRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews.add(historyStoriesRead);

                if(isLogin){
                    showFragment(new HistoryStoriesFragment());
                }else {
                    showFragment(new NoLoginFragment());
                }

                changBackgroundColor(textViews);
            }
        });
        return view;
    }
    public void changBackgroundColor(List<TextView> textViews){
        int size = textViews.size();
        if(textViews.size() ==0){
            TextView textView = textViews.get(size-1);
            Drawable purple = new ColorDrawable(Color.parseColor("#F58EFF"));
            int white = Color.parseColor("#FFFFFF");
            textView.setBackground(purple);
            textView.setTextColor(white);
        }else if (textViews.size() >1) {
            TextView textView = textViews.get(size-1);
            TextView textView1 = textViews.get(size-2);
            Drawable purple = new ColorDrawable(Color.parseColor("#F58EFF"));
            Drawable whiteBackGround = new ColorDrawable(Color.parseColor("#FFFFFF"));
            int white = Color.parseColor("#FFFFFF");
            int black = Color.parseColor("black");
            textView.setBackground(purple);
            textView.setTextColor(white);
            textView1.setBackground(whiteBackGround);
            textView1.setTextColor(black);
        }else {

        }


    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}