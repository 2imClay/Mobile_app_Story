package com.example.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.dao.UserDAO;
import com.example.project.model.User;
import com.example.project.service.UserService;

public class RegisterFragment extends Fragment {

    private TextView textLogin;
    private EditText textUsername, textPassword, textPassAgain, textEmail;
    private Button btnRegister;
    private UserService userService;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userService = new UserService(getContext());
        View view = inflater.inflate(R.layout.register_layout, container, false);
        textLogin = view.findViewById(R.id.textLogin);
        textUsername = view.findViewById(R.id.textUsername);
        textPassword = view.findViewById(R.id.textPassword);
        textPassAgain = view.findViewById(R.id.textPassAgain);
        textEmail = view.findViewById(R.id.textEmail);

        btnRegister = view.findViewById(R.id.btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textUsername.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String passAgain = textPassAgain.getText().toString().trim();
                String email = textEmail.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()|| passAgain.isEmpty()) {
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(!password.equals(passAgain)){
                        Toast.makeText(getContext(), "Mặt khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                        System.out.println(password);
                        System.out.println(passAgain);
                    }else {

                        if(userService.checkUser(username)){
                            Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }else {

                            User user = new User(username, password,email);
                            userService.insert(user);
                            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), MainActivity.class));


                        }
                    }
                }
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new LoginFragment());
            }
        });


        return view;
    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
