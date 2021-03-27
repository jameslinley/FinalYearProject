package com.example.housem8;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HouseMateAdapter extends RecyclerView.Adapter<HouseMateAdapter.HouseMateHolder> {

    private ArrayList<HouseMate> housemates;
    private Context context;
    private OnHouseMateClickListener onHouseMateClickListener;

    public HouseMateAdapter(ArrayList<HouseMate> housemates, Context context, OnHouseMateClickListener onHouseMateClickListener) {
        this.housemates = housemates;
        this.context = context;
        this.onHouseMateClickListener = onHouseMateClickListener;
    }

    interface OnHouseMateClickListener{
        void onHouseMateClicked(int position);
    }

    @NonNull
    @Override
    public HouseMateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.housemate_holder, parent, false);
        return new HouseMateHolder(view);
    }

    @Override
    public int getItemCount() {
        return housemates.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HouseMateHolder holder, int position) {
        holder.nameOfHouseMate.setText(housemates.get(position).getName());
    }

    public class HouseMateHolder extends RecyclerView.ViewHolder {
        private TextView nameOfHouseMate;
        public HouseMateHolder(@NonNull View itemView) {
            super(itemView);
            
            itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view){
                   onHouseMateClickListener.onHouseMateClicked(getAdapterPosition());


               }
            });
    
            nameOfHouseMate = itemView.findViewById(R.id.name_of_housemate);
            
        }
    }
}
