package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton calButton;


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
        Calendar cal = new Calendar(); //may need to move this
        calButton = findViewById(R.id.calButton);
        calButton.setOnClickListener(v -> {
            calButton.setVisibility(View.GONE);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.mainLayout, cal).commit();
        });

        


    }



}
