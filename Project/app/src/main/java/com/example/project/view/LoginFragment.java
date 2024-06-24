package com.example.project.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.FragmentHome;
import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.dao.DatabaseHelper;
import com.example.project.dao.UserDAO;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;

public class LoginFragment extends Fragment  {

    private TextView textViewRegister;
    private Button buttonLogin;
    private EditText editTextUsername, editTextPassword;
    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;
    private UserPreferences userPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);

        textViewRegister = view.findViewById(R.id.textViewRegister);
        buttonLogin = view.findViewById(R.id.buttonLogin);

        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        databaseHelper = new DatabaseHelper(getContext());
        userDAO = new UserDAO(getContext());
        userPreferences = new UserPreferences(getContext());
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new RegisterFragment());
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty() ){
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ tài khoản và mặt khẩu", Toast.LENGTH_SHORT).show();
                }else {
                    if(userDAO.authercationUser(username,password)){
                        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        User user = userDAO.getUserByUsername(username);
                        userPreferences.saveUser(user);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        System.out.println(username);
                        System.out.println(password);
                        for (User user: userDAO.selectAll()
                             ) {
                            System.out.println(user.toString());
                        }
                        System.out.println();
                        Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return view;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Thêm vào stack để có thể quay lại
        transaction.commit();
    }
}
