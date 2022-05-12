package com.morg.mycomponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.morg.mycomponent.component.BottomSheetComponent;
import com.morg.mycomponent.databinding.ActivityBottomSheetBinding;
import com.morg.mycomponent.model.BottomSheetList;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetActivity extends AppCompatActivity {
    private ActivityBottomSheetBinding binding;
    private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomSheet1.setOnClickListener(view -> {
            bottomSheetList();
        });
        binding.bottomSheet2.setOnClickListener(view -> {
            BottomSheetComponent bottomSheetComponent = new BottomSheetComponent(this);
            bottomSheetComponent.setTitle("Peringatan");
            bottomSheetComponent.setDescription("Declare all the views and call them by id as specified in the Bottom Sheet layout. Finally, we will diplay the dialog using bottomSheetDialog.show().");
            bottomSheetComponent.setButtonYes("Lanjutkan");
            bottomSheetComponent.setButtonNo("Batal");
            bottomSheetComponent.isButtonYes(false);
            bottomSheetComponent.setCancelable(true);
            bottomSheetComponent.show(getSupportFragmentManager(), bottomSheetComponent.getTag());
        });
        binding.bottomSheet3.setOnClickListener(view -> {
//            File outputFile = new File(Environment.getExternalStoragePublicDirectory
//                    (Environment.DIRECTORY_DOWNLOADS), "example.pdf");
//            Uri uri = Uri.fromFile(outputFile);

            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("application/pdf");

            startActivity(share);
        });


        bottomSheetBehavior = BottomSheetBehavior.from(binding.includePengaturanBottomSheet.bottomSheetDrawer);
        bottomSheetBehavior.setDraggable(false);
        binding.includePengaturanBottomSheet.ivExtendArrow.setOnClickListener(viewOnClickExtend -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                binding.scrim.setVisibility(View.GONE);
            }
        });

        binding.includePengaturanBottomSheet.ivLicense.setOnClickListener(viewOnClickLicense ->
                Toast.makeText(this, "anda mengklik", Toast.LENGTH_SHORT).show()
        );

        binding.scrim.setOnClickListener(viewScrim -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            binding.scrim.setVisibility(View.GONE);
        });

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED)
                    binding.scrim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                binding.includePengaturanBottomSheet.ivExtendArrow.setRotation(slideOffset * 180);
            }
        });
    }

    private void bottomSheetList() {
        List<BottomSheetList> bottomSheetListList = new ArrayList<>();
//        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        List<ResolveInfo> pkgAppsList = this.getPackageManager().queryIntentActivities(mainIntent, 0);
//        for (int i = 0; i < pkgAppsList.size(); i++) {
//            String[] names = pkgAppsList.get(i).activityInfo.name.split("\\.");
//            String name = names[names.length - 1];
//            bottomSheetListList.add(new BottomSheetList(name, getResources().getDrawable(R.drawable.bucket_ic), new Intent(this, MainActivity.class)));
//        }
        bottomSheetListList.add(new BottomSheetList("Peraturan 1", getResources().getDrawable(R.drawable.bucket_ic), new Intent(this, MainActivity.class)));
        bottomSheetListList.add(new BottomSheetList("Peraturan 2", getResources().getDrawable(R.drawable.bucket_ic), new Intent(this, MainActivity.class)));
        bottomSheetListList.add(new BottomSheetList("Peraturan 3", getResources().getDrawable(R.drawable.bucket_ic), new Intent(this, MainActivity.class)));
        bottomSheetListList.add(new BottomSheetList("Peraturan 4", getResources().getDrawable(R.drawable.bucket_ic), new Intent(this, MainActivity.class)));
        BottomSheetComponent bottomSheetComponent = new BottomSheetComponent(this);
        bottomSheetComponent.setListData(bottomSheetListList);
        bottomSheetComponent.show(getSupportFragmentManager(), bottomSheetComponent.getTag());
    }
}