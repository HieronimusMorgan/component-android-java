package com.morg.mycomponent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.morg.mycomponent.component.carouselview.CarouselView;

public class ShoppingActivity extends AppCompatActivity {
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.stopCarousel();
        carouselView.setImageListener((position, imageView) -> imageView.setImageResource(sampleImages[position]));
    }
}