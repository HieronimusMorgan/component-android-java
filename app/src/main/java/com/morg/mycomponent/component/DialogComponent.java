/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.morg.mycomponent.R;
import com.google.android.material.button.MaterialButton;

public class DialogComponent extends Dialog {
    private TextView textHeader;
    private ImageView imageView;
    private TextView textDescription;
    private MaterialButton submitButton;
    private MaterialButton cancelButton;
    private LinearLayout linearLayout;

    public DialogComponent(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
        setCanceledOnTouchOutside(false);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setPadding(30, 30, 30, 30);
        lineLayoutParams.setMargins(30, 20, 30, 20);

        imageView = new ImageView(context);
        LinearLayout.LayoutParams imageViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageViewParam.setMargins(30, 10, 30, 40);
        imageView.setLayoutParams(imageViewParam);

        linearLayout.addView(imageView);

        textHeader = new TextView(context);
        LinearLayout.LayoutParams textHeaderParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textHeaderParam.setMargins(0, 0, 0, 30);
        textHeader.setGravity(Gravity.CENTER);
        textHeader.setLayoutParams(textHeaderParam);
        textHeader.setAllCaps(true);
        textHeader.setTypeface(textHeader.getTypeface(), Typeface.BOLD);
        textHeader.setTextSize(20);

        linearLayout.addView(textHeader);

        textDescription = new TextView(context);
        LinearLayout.LayoutParams textDescriptionParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textDescriptionParam.setMargins(0, 0, 0, 30);
        textDescription.setGravity(Gravity.CENTER);
        textDescription.setLayoutParams(textDescriptionParam);
        textDescription.setTextSize(16);

        linearLayout.addView(textDescription);

        submitButton = new MaterialButton(context);
        LinearLayout.LayoutParams submitButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        submitButtonParams.setMargins(0, 0, 0, 0);
        submitButton.setLayoutParams(submitButtonParams);


        cancelButton = new MaterialButton(context, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        LinearLayout.LayoutParams cancelButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        cancelButtonParams.setMargins(0, 0, 0, 0);
        cancelButton.setLayoutParams(cancelButtonParams);
        cancelButton.setOnClickListener(view -> dismiss());
        linearLayout.addView(submitButton);
        linearLayout.addView(cancelButton);

        addContentView(linearLayout, lineLayoutParams);
    }

    public DialogComponent setLabel(String header, String description) {
        textHeader.setText(header);
        textDescription.setText(description);
        return this;
    }

    public DialogComponent setLabelSubmitButton(String submit) {
        submitButton.setText(submit);
        return this;
    }

    public DialogComponent setLabelCancelButton(String cancel) {
        cancelButton.setText(cancel);
        return this;
    }

    public DialogComponent removeCancelButton() {
        cancelButton.setVisibility(View.GONE);
        return this;
    }

    public DialogComponent imageSuccess() {
        imageView.setImageResource(R.drawable.success_icon);
        return this;
    }

    public DialogComponent noImage() {
        imageView.setVisibility(View.GONE);
        return this;
    }

    public DialogComponent imageWarning() {
        imageView.setImageResource(R.drawable.warning_icon);
        return this;
    }

    public DialogComponent setOnClickListener(View.OnClickListener click) {
        submitButton.setOnClickListener((View.OnClickListener) click);
        dismiss();
        return this;
    }
}
