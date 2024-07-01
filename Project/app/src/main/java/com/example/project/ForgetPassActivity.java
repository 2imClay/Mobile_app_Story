package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.dao.UserDAO;

public class ForgetPassActivity extends AppCompatActivity {

    private EditText usernameText, emailText;
    private UserDAO userDAO;
    private Button but_Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forget_pass_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameText = findViewById(R.id.usernameText);
        emailText = findViewById(R.id.emailText);
        userDAO = new UserDAO(this);
        but_Submit = findViewById(R.id.but_Submit);

        but_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString().trim();
                String email = emailText.getText().toString().trim();

                if(userDAO.checkUser(username,email) == true){
                    Intent intent = new Intent(ForgetPassActivity.this, EmailActivity.class);
                    intent.putExtra("Email",email);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                }else{
                    Toast.makeText(ForgetPassActivity.this, "Không tồn tại tài khoản có username với email đó", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}