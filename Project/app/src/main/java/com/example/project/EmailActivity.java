package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.dao.UserDAO;
import com.example.project.model.Email;
import com.example.project.model.User;
import com.example.project.model.UserPreferences;
import com.example.project.view.LoginFragment;

import java.util.Random;

public class EmailActivity extends AppCompatActivity {
   private EditText emailEditText;
   private EditText textCode, textPasswordNew, textConfirmPassword;
   private Button submitButton, sendButton;
    private UserPreferences userPreferences;
    private ImageButton imgButtonBack;

    private UserDAO  userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_layout);
        Intent intent = getIntent();
        String emailReceive = intent.getStringExtra("Email");
        String usernameReceive = intent.getStringExtra("Username");
        if(emailReceive==null){
            userPreferences = new UserPreferences(this);
            User user = userPreferences.getUser();
            userDAO = new UserDAO(this);


            textCode = findViewById(R.id.textCode);
            textPasswordNew = findViewById(R.id.textNewPassword);
            textConfirmPassword = findViewById(R.id.textConfirmPassword);
            sendButton = findViewById(R.id.sendButton);
            submitButton = findViewById(R.id.submitButton);
            imgButtonBack = findViewById(R.id.imgButtonBack);

            int numberRandom = randomNumber();

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String subject = "Mã Xác Nhận";
                    String body = "Đây là mã xác nhận của bạn: "+numberRandom;
                    new Email(user.getEmail(),subject,body).execute();


                }
            });
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code = textCode.getText().toString().trim();
                    String newPassword = textPasswordNew.getText().toString().trim();
                    String confirmPassword = textConfirmPassword.getText().toString().trim();
                    if(!newPassword.equalsIgnoreCase(confirmPassword)){
                        Toast.makeText(EmailActivity.this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();

                    }else{
                        if(code.equalsIgnoreCase(numberRandom+"")){
                            userDAO.changePassword(user.getUsername(), newPassword);
                            finish();
                        }else{
                            Toast.makeText(EmailActivity.this, "Sai mã. Hãy nhập lại", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            });
            imgButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            ;
            userDAO = new UserDAO(this);
            textCode = findViewById(R.id.textCode);
            textPasswordNew = findViewById(R.id.textNewPassword);
            textConfirmPassword = findViewById(R.id.textConfirmPassword);
            sendButton = findViewById(R.id.sendButton);
            submitButton = findViewById(R.id.submitButton);
            imgButtonBack = findViewById(R.id.imgButtonBack);
            int numberRandom = randomNumber();

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String subject = "Mã Xác Nhận";
                    String body = "Đây là mã xác nhận của bạn: "+numberRandom;
                    new Email(emailReceive,subject,body).execute();


                }
            });
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code = textCode.getText().toString().trim();
                    String newPassword = textPasswordNew.getText().toString().trim();
                    String confirmPassword = textConfirmPassword.getText().toString().trim();
                    if(!newPassword.equalsIgnoreCase(confirmPassword)){
                        Toast.makeText(EmailActivity.this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();

                    }else{
                        if(code.equalsIgnoreCase(numberRandom+"")){
                            userDAO.changePassword(usernameReceive, newPassword);
                            Intent intent1 = new Intent(EmailActivity.this,MainActivity.class);
                            startActivity(intent1);
                            Toast.makeText(EmailActivity.this, "Bạn đã đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(EmailActivity.this, "Sai mã. Hãy nhập lại", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            });
            imgButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }
    public int randomNumber(){
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);

        return number;
    }
}