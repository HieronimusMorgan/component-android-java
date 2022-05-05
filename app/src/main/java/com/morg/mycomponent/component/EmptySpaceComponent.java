/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.morg.mycomponent.R;

public class EmptySpaceComponent extends LinearLayout {

    private TypedArray values;
    private String message;
    private String description;
    private Drawable image;
    private MaterialButton buttonAdd;

    public EmptySpaceComponent(Context context) {
        super(context);
    }

    public EmptySpaceComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        message = values.getString(R.styleable.component_message);
        description = values.getString(R.styleable.component_description);
        image = values.getDrawable(R.styleable.component_image);
        init();
    }

    public EmptySpaceComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        message = values.getString(R.styleable.component_message);
        description = values.getString(R.styleable.component_message);
        image = values.getDrawable(R.styleable.component_image);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setWeightSum(2);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.9F);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(image);
        linearLayout.addView(imageView);

        TextView textView = new TextView(getContext());
        LayoutParams textViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(60, 80, 60, 0);
        textView.setLayoutParams(textViewParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(message);
        textView.setTextSize(20);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        linearLayout.addView(textView);

        textView = new TextView(getContext());
        textViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setPadding(100, 20, 100, 0);
        textView.setTextColor(getResources().getColor(R.color.grey_1));
        textView.setGravity(Gravity.CENTER);
        textView.setText(description);
        linearLayout.addView(textView);

        addView(linearLayout);

        buttonAdd = new MaterialButton(getContext());
        LinearLayout.LayoutParams submitButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.1F);
        submitButtonParams.setMargins(30, 0, 30, 0);
        buttonAdd.setPadding(10, 20, 10, 20);
        buttonAdd.setLayoutParams(submitButtonParams);
        buttonAdd.setText("Add");
        buttonAdd.setVisibility(GONE);
        addView(buttonAdd);
    }

    public EmptySpaceComponent setButtonActive(boolean button) {
        if (button)
            buttonAdd.setVisibility(VISIBLE);
        else
            buttonAdd.setVisibility(GONE);
        return this;
    }

    public EmptySpaceComponent setTextButton(String button) {
        buttonAdd.setText(button);
        return this;
    }

    public void setButtonOnClickListener(View.OnClickListener clickListener) {
        buttonAdd.setOnClickListener(clickListener);
    }

}
