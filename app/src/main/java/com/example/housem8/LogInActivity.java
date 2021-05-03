package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

/**
 * LogInActivity class
 * Author: Maid Rondić (2020)
 * Title: Build Chat App in Android with Java and Firebase
 * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
 * Lesson: 2
 *
 */
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


    /**
     * changeText() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method to change the text of registerBtn, alreadyRegisteredTxt and loginRegisterHereTxt
     * depending on if the user is registered or not
     */
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

    /**
     * setUpButtons() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method when registerBtn is clicked to call either register() or login(),
     * depending on if the user is registered or not
     */
    public void setUpButtons() {
        registerBtn.setOnClickListener(view -> {
            if(isRegistering){
                register();
            } else {
                logIn();
            }
        });
    }

    /**
     * register() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method to register a user to Firebase database
     */
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

    /**
     * postRegistered() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method to change text values after user is registered
     */
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

    /**
     * logIn() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method to log the user in after successful registration
     */
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

    /**
     * loggedIn() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 2
     *
     * method to check whether user is already logged in after closing app
     */
    public void loggedIn(){
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        }
    }

}