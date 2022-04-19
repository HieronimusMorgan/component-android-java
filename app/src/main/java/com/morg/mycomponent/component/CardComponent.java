/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.morg.mycomponent.R;

public class CardComponent extends MaterialCardView {
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivImage;
    private LinearLayout linearLayout;
    private TypedArray values;
    private LinearLayout.LayoutParams linearLayoutParams;
    private String type;
    private String title;
    private String description;
    private Drawable image;

    public CardComponent(Context context) {
        super(context);
        cardComponent();
    }

    public CardComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        title = values.getString(R.styleable.component_title);
        description = values.getString(R.styleable.component_description);
        validation(type);
    }

    private void validation(String type) {
        if (type.equalsIgnoreCase("normal")){
            cardComponent();
        }else if(type.equalsIgnoreCase("pictureRounded")){
            cardComponentPictureRounded();
        }
    }

    public CardComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        title = values.getString(R.styleable.component_title);
        description = values.getString(R.styleable.component_description);
    }

    private CardComponent cardComponent() {
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 20, 0, 10);
        tvTitle.setLayoutParams(linearLayoutParams);

        tvDescription = new TextView(linearLayout.getContext());
        tvDescription.setTextSize(14);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 10);
        tvDescription.setLayoutParams(linearLayoutParams);


        linearLayout.addView(tvTitle);
        linearLayout.addView(tvDescription);


        addView(linearLayout);

        return this;
    }


    private CardComponent cardComponentPictureRounded() {

        return this;
    }

}
