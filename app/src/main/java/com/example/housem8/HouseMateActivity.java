package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HouseMateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HouseMate> housemates;
    private HouseMateAdapter houseMateAdapter;
    HouseMateAdapter.OnHouseMateClickListener onHouseMateClickListener;

    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housemates);

        housemates = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout = findViewById(R.id.swipe_layout);

        houseMateAdapter = new HouseMateAdapter(housemates, HouseMateActivity.this, onHouseMateClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(HouseMateActivity.this));
        recyclerView.setAdapter(houseMateAdapter);

        toolBar();

        getHouseMates();
        onHmCl();
        refreshListener();
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.settings_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.settings_menu_item) {
            startActivity(new Intent(HouseMateActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
    }

    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("House mates");
        setSupportActionBar(toolbar);

        //below was getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void onHmCl(){
        onHouseMateClickListener = new HouseMateAdapter.OnHouseMateClickListener() {
            @Override
            public void onHouseMateClicked(int position) {
                Toast.makeText(HouseMateActivity.this, "Tapped on " + housemates.get(position).getName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HouseMateActivity.this, MessagesActivity.class)
                        .putExtra("housemate_name", housemates.get(position).getName())
                        .putExtra("housemate_email", housemates.get(position).getEmail())
                        .putExtra("housemate_houseID", housemates.get(position).getHouseID())
                );
            }
        };
    }

    public void refreshListener(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHouseMates();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getHouseMates(){
        housemates.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()){
                    housemates.add(d.getValue(HouseMate.class));
                }
                houseMateAdapter = new HouseMateAdapter(housemates, HouseMateActivity.this, onHouseMateClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(HouseMateActivity.this));
                recyclerView.setAdapter(houseMateAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}