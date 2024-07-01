package com.example.project.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.EmailActivity;
import com.example.project.R;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonLayout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonLayout extends Fragment {
    private UserPreferences userPreferences;
    private TextView profileName, profileEmail;
    private ImageView profileImageView;
    private Button chooseImageButton,change_password_button;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_PERMISSION = 100;
    private static final int REQUEST_PICK_IMAGE = 101;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonLayout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Person_Layout.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonLayout newInstance(String param1, String param2) {
        PersonLayout fragment = new PersonLayout();
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
        View view = inflater.inflate(R.layout.person_layout, container, false);

        userPreferences = new UserPreferences(getContext());
        User user = userPreferences.getUser();

        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        profileImageView = view.findViewById(R.id.profile_image);
        chooseImageButton = view.findViewById(R.id.choose_image_button);
        change_password_button = view.findViewById(R.id.change_password_button);
        change_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , EmailActivity.class);
                startActivity(intent);
            }
        });
        profileName.setText(user.getUsername());
        profileEmail.setText(user.getEmail());

        ImageView profileImageView = view.findViewById(R.id.profile_image);
        int drawableId = R.drawable.thegioihoanmy;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);

        Bitmap circularBitmap = getCircularBitmap(bitmap);

        profileImageView.setImageBitmap(circularBitmap);
        chooseImageButton.setOnClickListener(v -> checkPermissionAndOpenImagePicker());
        return view;
    }

    // Kiểm tra quyền và mở trình chọn ảnh
    private void checkPermissionAndOpenImagePicker() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        } else {
            openImagePicker();
        }
    }

    // Phương thức để mở trình chọn tệp ảnh
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    // Xử lý kết quả của yêu cầu quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Xử lý kết quả trả về sau khi người dùng chọn ảnh
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                Bitmap circularBitmap = getCircularBitmap(bitmap);
                profileImageView.setImageBitmap(circularBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Chuyển đổi Bitmap thành Bitmap hình tròn
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