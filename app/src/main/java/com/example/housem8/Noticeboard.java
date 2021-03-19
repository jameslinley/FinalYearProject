package com.example.housem8;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Noticeboard extends Fragment {

    Button toMainButton;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_noticeboard, container, false);

        toMainButton = v.findViewById(R.id.toMainButton);
        toMainButton.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), MainActivity.class)));

        return v;
    }







}