package com.morg.mycomponent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.morg.mycomponent.databinding.ActivityCardComponentBinding;

public class CardComponentActivity extends AppCompatActivity {
    private ActivityCardComponentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardComponentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}