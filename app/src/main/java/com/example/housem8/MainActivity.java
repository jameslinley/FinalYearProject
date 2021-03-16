package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar();
        calFrag();

    }

    //method to show toolbar on each page
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //method to change to fragment once button is clicked
    public void calFrag(){
        testButton = findViewById(R.id.testButton);
        
        testButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                Calendar cal = new Calendar();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.mainLayout, cal).commit();
            }
        });

        


    }



}
