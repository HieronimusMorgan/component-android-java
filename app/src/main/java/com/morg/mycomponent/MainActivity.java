package com.morg.mycomponent;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.morg.mycomponent.component.BottomSheetComponent;
import com.morg.mycomponent.component.ProgressDialogComponent;
import com.morg.mycomponent.databinding.ActivityMainBinding;
import com.morg.mycomponent.model.CardList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProgressDialogComponent progressDialogComponent = new ProgressDialogComponent(this);
        progressDialogComponent.setMessage("Please Wait...");
        MaterialButton materialButton = binding.progressCircular;
        materialButton.setOnClickListener(view -> {
//            progressDialogComponent.show();
//            new Handler().postDelayed(() -> progressDialogComponent.hide(), 10000);
            binding.step.next();
        });
        binding.progressCircularA.setOnClickListener(view -> {
            binding.step.previous();
        });
//        CardComponent cardComponent = new CardComponent(this);
        List<CardList> cardLists = new ArrayList<>();
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
//        cardComponent.cardComponentList(cardLists);
        binding.card.cardComponentList(cardLists);
//        binding.as.addView(cardComponent);
        binding.passwordMatch.isPasswordMatch(true);
        binding.passwordNotMatch.isPasswordMatch(false);
        List<String> titles = new ArrayList<>();
        titles.add("User Profile 1");
        titles.add("User Profile 2");
        titles.add("User Profile 3");
        titles.add("User Profile 4");
        titles.add("User Profile 5");
        binding.step.setTitle(titles);
        binding.bottomSheet.setOnClickListener(view -> {
            BottomSheetComponent bottomSheetComponent = new BottomSheetComponent(this);
            bottomSheetComponent.setTitle("Peringatan");
            bottomSheetComponent.setDescription("Declare all the views and call them by id as specified in the Bottom Sheet layout. Finally, we will diplay the dialog using bottomSheetDialog.show().");
            bottomSheetComponent.setButtonYes("Lanjutkan");
            bottomSheetComponent.setButtonNo("Batal");
            bottomSheetComponent.isButtonYes(false);
            bottomSheetComponent.setCancelable(false);
            bottomSheetComponent.setOnClickButtonYes(view1 -> binding.step.next());
            bottomSheetComponent.setOnClickButtonNo(view1 -> binding.step.previous());
            bottomSheetComponent.show(getSupportFragmentManager(), bottomSheetComponent.getTag());

        });
    }
}