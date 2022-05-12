package com.morg.mycomponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.morg.mycomponent.component.DialogComponent;
import com.morg.mycomponent.component.ProgressDialogComponent;
import com.morg.mycomponent.component.SnackBarComponent;
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

        binding.next.setOnClickListener(view -> {
            binding.step.next();
        });
        binding.previous.setOnClickListener(view -> {
            binding.step.previous();
        });
        List<CardList> cardLists = new ArrayList<>();
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        cardLists.add(new CardList("Name", "Aldi Taher"));
        binding.card.cardComponentList(cardLists);

        binding.dialog1.setOnClickListener(view -> {
            DialogComponent dialogComponent = new DialogComponent(this);
            dialogComponent.withImage(false);
            dialogComponent.setHeader("Confirmation");
            dialogComponent.setDescription("Are you sure want to Logout from the application?");
            dialogComponent.setLabelSubmitButton("Yes, Logout");
            dialogComponent.setLabelCancelButton("Cancel");
            dialogComponent.setSubmitOnClickListener(view1 -> Toast.makeText(this, "Logout Confirm", Toast.LENGTH_SHORT).show());
            dialogComponent.show();
        });

        binding.showCase.setOnClickListener(view ->
                startActivity(new Intent(this, ShowCaseViewActivity.class)));

        binding.dialog2.setOnClickListener(view -> {
            DialogComponent dialogComponent = new DialogComponent(this);
            dialogComponent.withImage(false);
            dialogComponent.setHeader("Warning");
            dialogComponent.setDescription("Are you sure want to Logout from the application?");
            dialogComponent.setLabelSubmitButton("Yes, Logout");
            dialogComponent.setLabelCancelButton("Cancel");
            dialogComponent.withImage(true);
            dialogComponent.imageWarning();
            dialogComponent.setSubmitOnClickListener(view1 -> Toast.makeText(this, "Logout Confirm", Toast.LENGTH_SHORT).show());
            dialogComponent.show();
        });
        binding.dialog3.setOnClickListener(view -> {
            DialogComponent dialogComponent = new DialogComponent(this);
            dialogComponent.withImage(false);
            dialogComponent.setHeader("Success");
            dialogComponent.setDescription("Your data was successfully saved!");
            dialogComponent.setLabelSubmitButton("OK");
            dialogComponent.setLabelCancelButton("Cancel");
            dialogComponent.withImage(true);
            dialogComponent.imageSuccess();
            dialogComponent.setSubmitOnClickListener(view1 -> Toast.makeText(this, "Logout Confirm", Toast.LENGTH_SHORT).show());
            dialogComponent.show();
        });

        binding.emptySpace.setOnClickListener(view -> startActivity(new Intent(this, EmptySpaceActivity.class)));

        binding.passwordMatch.isPasswordMatch(true);
        binding.passwordNotMatch.isPasswordMatch(false);
        List<String> titles = new ArrayList<>();
        titles.add("User Profile 1");
        titles.add("User Profile 2");
        titles.add("User Profile 3");
        titles.add("User Profile 4");
        titles.add("User Profile 5");
        titles.add("User Profile 6");
        titles.add("User Profile 7");
        binding.step.setTitle(titles);
        binding.bottomSheet.setOnClickListener(view -> {
//            BottomSheetComponent bottomSheetComponent = new BottomSheetComponent(this);
//            bottomSheetComponent.setTitle("Peringatan");
//            bottomSheetComponent.setDescription("Declare all the views and call them by id as specified in the Bottom Sheet layout. Finally, we will diplay the dialog using bottomSheetDialog.show().");
//            bottomSheetComponent.setButtonYes("Lanjutkan");
//            bottomSheetComponent.setButtonNo("Batal");
//            bottomSheetComponent.isButtonYes(false);
//            bottomSheetComponent.setCancelable(false);
//            bottomSheetComponent.setOnClickButtonYes(view1 -> binding.step.next());
//            bottomSheetComponent.setOnClickButtonNo(view1 -> binding.step.previous());
//            bottomSheetComponent.show(getSupportFragmentManager(), bottomSheetComponent.getTag());
            startActivity(new Intent(this, BottomSheetActivity.class));
        });
        binding.cardActivity.setOnClickListener(view -> {
            startActivity(new Intent(this, CardComponentActivity.class));
        });

        SnackBarComponent snackBarComponent = new SnackBarComponent(this);
        binding.toast.setOnClickListener(view -> snackBarComponent.toast("Message"));
        binding.toastBtn.setOnClickListener(view -> snackBarComponent.toastButton("Message", "Return", View::onCancelPendingInputEvents));
        binding.toastCount.setOnClickListener(view -> snackBarComponent.toastCounting("Counting", getResources().getDrawable(R.drawable.image_icon), 5, () -> binding.step.next()));
    }

}