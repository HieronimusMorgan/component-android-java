package com.morg.mycomponent.component;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.morg.mycomponent.R;

public class BottomSheetComponent extends BottomSheetDialogFragment {
    private TextView tvTitle, tvDescription;
    private MaterialButton btnYes, btnNo;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams layoutParams;

    public BottomSheetComponent(Context context) {

        linearLayout = new LinearLayout(context);
        linearLayout.setPadding(20, 50, 20, 20);
       linearLayout.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(layoutParams);

        tvDescription = new TextView(linearLayout.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setLayoutParams(layoutParams);

        LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setWeightSum(2);
        linearLayout1.setLayoutParams(layoutParams);

        btnYes = new MaterialButton(linearLayout1.getContext());
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.setMargins(10, 10, 10, 10);
        btnYes.setLayoutParams(layoutParams);

        btnNo = new MaterialButton(context, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        btnNo.setLayoutParams(layoutParams);

        linearLayout1.addView(btnNo);
        linearLayout1.addView(btnYes);

        linearLayout.addView(tvTitle);
        linearLayout.addView(tvDescription);
        linearLayout.addView(linearLayout1);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        linearLayout.setLayoutParams(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return linearLayout;
    }

    public BottomSheetComponent setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    public BottomSheetComponent setDescription(String description) {
        tvDescription.setText(description);
        return this;
    }

    public BottomSheetComponent isButtonYes(Boolean button) {
        if (button) {
            btnNo.setVisibility(View.GONE);
        }
        return this;
    }

    public BottomSheetComponent setButtonYes(String btnYes) {
        this.btnYes.setText(btnYes);
        return this;
    }

    public BottomSheetComponent setButtonNo(String btnNo) {
        this.btnNo.setText(btnNo);
        return this;
    }

    public BottomSheetComponent setOnClickButtonYes(View.OnClickListener onClickListener) {
        btnYes.setOnClickListener(view -> {
            onClickListener.onClick(view);
            dismiss();
        });
        return this;
    }

    public BottomSheetComponent setOnClickButtonNo(View.OnClickListener onClickListener) {
        btnNo.setOnClickListener(view -> {
            onClickListener.onClick(view);
            dismiss();
        });
        return this;
    }
}
