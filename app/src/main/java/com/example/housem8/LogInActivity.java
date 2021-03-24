package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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


        loggedIn();
        isRegistered();
        setUpBtns();
    }

    public void isRegistered(){
        alreadyRegisteredTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isRegistered){
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

    public void setUpBtns() {
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isRegistered){
                    register();
                } else {
                    logIn();
                }
            }
        });
    }

    public void register(){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(emailTxt
                .getText()
                .toString(), pwordTxt
                .getText()
                .toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void logIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailTxt.getText().toString(), pwordTxt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LogInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loggedIn(){
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        }
    }

}