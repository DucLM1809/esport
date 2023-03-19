package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esport.R;
import com.example.esport.model.UserAuthen;
import com.example.esport.model.UserResponse;
import com.example.esport.presenter.UserPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity implements UserAuthView {
    TextView tvHadAccount;
    ImageView btnRegister;
    UserPresenter userPresenter;
    TextInputEditText etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvHadAccount = findViewById(R.id.had_account);
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.emailRegister);
        etPassword = findViewById(R.id.passwordRegister);
        etConfirmPassword = findViewById(R.id.confirmPasswordRegister);

        userPresenter = new UserPresenter(this);

        tvHadAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(SignupActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals("")) {
                    Toast.makeText(SignupActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (confirmPassword.equals("")) {
                    Toast.makeText(SignupActivity.this, "Confirm password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!confirmPassword.equals(password)) {
                    Toast.makeText(SignupActivity.this, "Confirm password is not same as password", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserAuthen user = new UserAuthen(email, password);
                if (userPresenter.register(user)) {
                    Toast.makeText(SignupActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void userAuthReady(UserResponse user) {

    }
}