package com.example.freagmentactivitypractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment1 extends Fragment {

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//
//        View view=inflater.inflate(R.layout.fragment_blank1, container, false);
//        TextView textView=view.findViewById(R.id.textView1);
//
        return inflater.inflate(R.layout.fragment_blank1, container, false);

    }
}