package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {

    private EditText nameTxt, emailTxt, pwordTxt;
    private Button registerBtn;
    private TextView alreadyRegisteredTxt, loginRegisterHereTxt;
    private boolean isRegistering;

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
        loginRegisterHereTxt = findViewById(R.id.loginregisterbutton);
        isRegistering = true;

        loggedIn();
        changeText();
        setUpButtons();
    }


    public void changeText(){
        loginRegisterHereTxt.setOnClickListener(view -> {
            if (isRegistering){
                isRegistering = false;
                registerBtn.setText("Log in");
                alreadyRegisteredTxt.setText("Not registered?");
                loginRegisterHereTxt.setText("REGISTER HERE");
                nameTxt.setVisibility(View.GONE);
            } else {
                isRegistering = true;
                registerBtn.setText("Register");
                alreadyRegisteredTxt.setText("Already registered?");
                loginRegisterHereTxt.setText("LOG IN HERE");
                nameTxt.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setUpButtons() {
        registerBtn.setOnClickListener(view -> {
            if(isRegistering){
                register();
            } else {
                logIn();
            }
        });
    }

    public void register(){
        if (emailTxt.getText().toString().isEmpty()) {
            Toast.makeText(LogInActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
        } else if (pwordTxt.getText().toString().isEmpty()){
            Toast.makeText(LogInActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
        } else if (nameTxt.getText().toString().isEmpty()){
            Toast.makeText(LogInActivity.this, "Name field is empty", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailTxt.getText().toString(),
                            pwordTxt.getText().toString())
                    .addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("user/"+ Objects.requireNonNull(FirebaseAuth.getInstance()
                            .getCurrentUser())
                            .getUid())
                            .setValue(new HouseMate(nameTxt.getText().toString()));
                    Toast.makeText(LogInActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    postRegistered();
                } else {
                    Toast.makeText(LogInActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void postRegistered(){
        isRegistering = false;
        registerBtn.setText("Log in");
        alreadyRegisteredTxt.setText("Not registered?");
        loginRegisterHereTxt.setText("REGISTER HERE");
        nameTxt.setVisibility(View.GONE);
        nameTxt.setText("");
        emailTxt.setText("");
        pwordTxt.setText("");
    }

    public void logIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailTxt.getText().toString(), pwordTxt.getText().toString())
                .addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(LogInActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogInActivity.this, MainActivity.class));
            } else {
                Toast.makeText(LogInActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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