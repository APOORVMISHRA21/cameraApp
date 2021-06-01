package com.example.lawnics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.lawnics.databinding.ActivityShowdocBinding;
import com.squareup.picasso.Picasso;

public class showdoc extends AppCompatActivity {

    ActivityShowdocBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowdocBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.showdocTitle.setText(getIntent().getStringExtra("doc_title"));
        Picasso.get().load(getIntent().getStringExtra("doc_image")).into(binding.showdocImage);
    }
}