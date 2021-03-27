package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HouseMateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HouseMate> housemates;
    private HouseMateAdapter houseMateAdapter;
    HouseMateAdapter.OnHouseMateClickListener onHouseMateClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housemates);

        housemates = new ArrayList<>();
        recyclerView = findViewById(R.id.hmRecyclerView);

        onHouseMateClickListener = new HouseMateAdapter.OnHouseMateClickListener() {
            @Override
            public void onHouseMateClicked(int position) {
                startActivity(new Intent(HouseMateActivity.this, MessagesActivity.class)
                        .putExtra("housemate_name", housemates.get(position).getName())
                        .putExtra("housemate_email", housemates.get(position).getEmail())
                );


            }
        };

        getHouseMates();
    }

    public void getHouseMates(){
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()){
                    housemates.add(d.getValue(HouseMate.class));
                }
                houseMateAdapter = new HouseMateAdapter(housemates,HouseMateActivity.this, onHouseMateClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(HouseMateActivity.this));
                recyclerView.setAdapter(houseMateAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}