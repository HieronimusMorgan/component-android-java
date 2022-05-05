package com.morg.mycomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.morg.mycomponent.component.EmptySpaceComponent;

public class EmptySpaceActivity extends AppCompatActivity {
    EmptySpaceComponent emptySpaceComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_space);
        emptySpaceComponent = findViewById(R.id.empty_space);
        emptySpaceComponent.setButtonActive(true);
        emptySpaceComponent.setTextButton("Add Product");
        emptySpaceComponent.setButtonOnClickListener(view -> {
            Toast.makeText(this, "Add Product", Toast.LENGTH_SHORT).show();
        });
    }
}