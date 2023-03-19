package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esport.App;
import com.example.esport.R;
import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserAuthen;
import com.example.esport.model.UserResponse;
import com.example.esport.presenter.UserPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class SigninActivity extends AppCompatActivity implements UserAuthView {
    TextView tvCreateAccount;
    ImageView btnSignIn;
    UserPresenter userPresenter;
    TextInputEditText etEmail, etPassword;
    Context context = App.getInstance().getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        tvCreateAccount = findViewById(R.id.create_account);
        btnSignIn = findViewById(R.id.btnSignIn);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        userPresenter = new UserPresenter(this);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(SigninActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals("")) {
                    Toast.makeText(SigninActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.matches("^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?\\W).*$")) {
                    Toast.makeText(SigninActivity.this, "Password must contain at least one lower character, one upper character, digit or special symbol", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserAuthen user = new UserAuthen(email, password);
                userPresenter.login(user);
            }
        });
    }

    @Override
    public void userAuthReady(UserResponse user) {

    }

    @Override
    public void loginReady(TokenResponse tokenResponse) {
        if (tokenResponse == null) {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("accessToken", tokenResponse.getAccessToken());
            editor.apply();
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            userPresenter = new UserPresenter(this);
            userPresenter.getUser();
        }
    }

    @Override
    public void userReady(UserResponse userResponse) {
        if (userResponse != null) {
            Log.d("Email", userResponse.getEmail());
            Log.d("isAdmin", userResponse.getIsAdmin() + "");
            if (userResponse.getIsAdmin()) {
                Intent intent = new Intent(SigninActivity.this, AdminActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}