package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    private EditText nameTxt, emailTxt, pwordTxt;
    private Button registerBtn;
    private TextView alreadyRegisteredTxt;
    private boolean isRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        nameTxt = findViewById(R.id.edit_name);
        emailTxt = findViewById(R.id.edit_email);
        pwordTxt = findViewById(R.id.edit_password);
        registerBtn = findViewById(R.id.button_register);
        alreadyRegisteredTxt = findViewById(R.id.text_have_account);
        isRegistered = true;

        isRegistered();
    }

    public void isRegistered(){
        alreadyRegisteredTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isRegistered == true){
                    isRegistered = false;
                    registerBtn.setText("Log in");
                    alreadyRegisteredTxt.setText("Don't have an account? Register here");
                    nameTxt.setVisibility(View.GONE);
                } else {
                    isRegistered = true;
                    registerBtn.setText("Register");
                    alreadyRegisteredTxt.setText("Already registered? Log in here");
                    nameTxt.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}