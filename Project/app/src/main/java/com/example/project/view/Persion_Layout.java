package com.example.project.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Persion_Layout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Persion_Layout extends Fragment {
    private UserPreferences userPreferences;

    private TextView profile_name,profile_email;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Persion_Layout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Persion_Layout.
     */
    // TODO: Rename and change types and number of parameters
    public static Persion_Layout newInstance(String param1, String param2) {
        Persion_Layout fragment = new Persion_Layout();
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
        View view = inflater.inflate(R.layout.persion_layout, container, false);

        userPreferences = new UserPreferences(getContext());
        User user = userPreferences.getUser();

        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);

        profile_name.setText(user.getUsername());
        profile_email.setText(user.getEmail());
        ImageView profileImageView = view.findViewById(R.id.profile_image);
        int drawableId = R.drawable.thegioihoanmy;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);

        Bitmap circularBitmap = getCircularBitmap(bitmap);

        profileImageView.setImageBitmap(circularBitmap);

        return view;
    }
    private Bitmap getCircularBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int size = Math.min(width, height);

        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        float radius = size / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        return output;
    }

}