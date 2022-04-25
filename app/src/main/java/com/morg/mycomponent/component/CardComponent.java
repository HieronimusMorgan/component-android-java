/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.morg.mycomponent.R;
import com.morg.mycomponent.model.CardList;

import java.util.List;

public class CardComponent extends MaterialCardView {
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivImage;
    private LinearLayout linearLayout;
    private TypedArray values;
    private LinearLayout.LayoutParams linearLayoutParams;
    private String type;
    private String button1 = "buttonFirst";
    private String button2 = "buttonSecond";
    MaterialButton buttonFirst, buttonSecond;
    private String title = "Title";
    private String description = "Description";
    private Drawable image;
    private ShapeableImageView imageView;

    public CardComponent(Context context) {
        super(context);
        cardComponent();
        setRadius(20);
    }

    public CardComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        title = values.getString(R.styleable.component_title);
        description = values.getString(R.styleable.component_description);
        button1 = values.getString(R.styleable.component_buttonFirst) != null ? values.getString(R.styleable.component_buttonFirst) : "buttonFist";
        button2 = values.getString(R.styleable.component_buttonSecond) != null ? values.getString(R.styleable.component_buttonSecond) : "buttonSecond";
        image = values.getDrawable(R.styleable.component_image);
        setRadius(20);
        setCardElevation(4);
        validation(type);
    }


    public CardComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        title = values.getString(R.styleable.component_title);
        description = values.getString(R.styleable.component_description);
        image = values.getDrawable(R.styleable.component_image);
        button1 = values.getString(R.styleable.component_buttonFirst);
        button2 = values.getString(R.styleable.component_buttonSecond);
        setRadius(20);
        setCardElevation(12);
        validation(type);
    }

    private void validation(String type) {
        if (type == null) {
            cardComponents();
        } else if (type.equalsIgnoreCase("normal")) {
            cardComponent();
        } else if (type.equalsIgnoreCase("card_picture_rounded")) {
            cardComponentPictureRounded();
        } else if (type.equalsIgnoreCase("card_picture")) {
            cardComponentPicture();
        }
    }

    private CardComponent cardComponent() {
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(20, 20, 20, 20);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(linearLayoutParams);
        tvTitle.setText(title);

        tvDescription = new TextView(linearLayout.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setText(description);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 10);
        tvDescription.setLayoutParams(linearLayoutParams);

        linearLayout.addView(tvTitle);
        linearLayout.addView(tvDescription);

        addView(linearLayout);

        return this;
    }

    public CardComponent cardComponentList(List<CardList> cardLists) {
        removeAllViews();
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(20, 20, 20, 20);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < cardLists.size(); i++) {
            LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setWeightSum(2);
            linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayoutParams.setMargins(10, 5, 10, 5);
            linearLayout1.setLayoutParams(linearLayoutParams);

            TextView textView = new TextView(linearLayout1.getContext());
            linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            textView.setText(cardLists.get(i).getTitle());
            textView.setTextColor(Color.parseColor("#818181"));
            textView.setGravity(Gravity.LEFT);
            textView.setLayoutParams(linearLayoutParams);

            TextView textView1 = new TextView(linearLayout1.getContext());
            linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            textView1.setText(cardLists.get(i).getDescription());
            textView1.setTextColor(Color.parseColor("#424242"));
            textView1.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView1.setGravity(Gravity.RIGHT);
            textView1.setLayoutParams(linearLayoutParams);

            linearLayout1.addView(textView);
            linearLayout1.addView(textView1);
            linearLayout.addView(linearLayout1);
        }
        addView(linearLayout);

        return this;
    }

    private CardComponent cardComponentPictureRounded() {
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setWeightSum(2);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        imageView = new ShapeableImageView(new ContextThemeWrapper(linearLayout.getContext(), R.style.ShapeAppearanceOverlay_App_CornerSize50Percent));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.5F);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(image);
        linearLayout.addView(imageView);

        LinearLayout linearLayout1 = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.5F);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParams.setMargins(40, 20, 0, 20);
        linearLayout1.setLayoutParams(linearLayoutParams);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout1.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(linearLayoutParams);
        tvTitle.setText(title);

        tvDescription = new TextView(linearLayout1.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setText(description);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 10);
        tvDescription.setLayoutParams(linearLayoutParams);

        linearLayout1.addView(tvTitle);
        linearLayout1.addView(tvDescription);

        linearLayout.addView(linearLayout1);

        addView(linearLayout);
        return this;
    }

    private CardComponent cardComponentPicture() {
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setWeightSum(2);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView imageView = new ImageView(linearLayout.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.5F);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(image);
        linearLayout.addView(imageView);

        LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.5F);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParams.setMargins(40, 20, 0, 20);
        linearLayout1.setLayoutParams(linearLayoutParams);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout1.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(linearLayoutParams);
        tvTitle.setText(title);

        tvDescription = new TextView(linearLayout1.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setText(description);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 10);
        tvDescription.setLayoutParams(linearLayoutParams);

        LinearLayout linearLayout2 = new LinearLayout(linearLayout1.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(linearLayoutParams);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setPadding(10, 10, 10, 10);

        MaterialButton buttonFirst = new MaterialButton(linearLayout2.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 20, 0);
        buttonFirst.setLayoutParams(linearLayoutParams);
        buttonFirst.setText(button1);

        MaterialButton buttonSecond = new MaterialButton(linearLayout2.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonSecond.setLayoutParams(linearLayoutParams);
        buttonSecond.setText(button2);
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(buttonSecond, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        linearLayout2.addView(buttonFirst);
        linearLayout2.addView(buttonSecond);


        linearLayout1.addView(tvTitle);
        linearLayout1.addView(tvDescription);
        linearLayout1.addView(linearLayout2);

        linearLayout.addView(linearLayout1);

        addView(linearLayout);
        return this;
    }

    public CardComponent cardComponents() {
        linearLayout = new LinearLayout(getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setWeightSum(2);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        imageView = new ShapeableImageView(new ContextThemeWrapper(linearLayout.getContext(), R.style.ShapeAppearanceOverlay_App_CornerSize50Percent));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.5F);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(image);
        imageView.setVisibility(GONE);
        linearLayout.addView(imageView);

        ivImage = new ImageView(linearLayout.getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.5F);
        ivImage.setLayoutParams(layoutParams1);
        ivImage.setVisibility(GONE);
        linearLayout.addView(ivImage);

        LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.5F);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParams.setMargins(40, 20, 0, 20);
        linearLayout1.setLayoutParams(linearLayoutParams);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout1.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        tvTitle.setVisibility(GONE);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(linearLayoutParams);


        tvDescription = new TextView(linearLayout1.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setVisibility(GONE);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 10);
        tvDescription.setLayoutParams(linearLayoutParams);

        LinearLayout linearLayout2 = new LinearLayout(linearLayout1.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(linearLayoutParams);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setPadding(10, 10, 10, 10);

        buttonFirst = new MaterialButton(linearLayout2.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 20, 0);
        buttonFirst.setVisibility(GONE);
        buttonFirst.setLayoutParams(linearLayoutParams);
        buttonFirst.setText(button1);

        buttonSecond = new MaterialButton(linearLayout2.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonSecond.setVisibility(GONE);
        buttonSecond.setLayoutParams(linearLayoutParams);
        buttonSecond.setText(button2);

        linearLayout2.addView(buttonFirst);
        linearLayout2.addView(buttonSecond);


        linearLayout1.addView(tvTitle);
        linearLayout1.addView(tvDescription);
        linearLayout1.addView(linearLayout2);

        linearLayout.addView(linearLayout1);

        addView(linearLayout);
        return this;
    }

    public CardComponent addTitle(String message) {
        tvTitle.setText(message);
        tvTitle.setVisibility(VISIBLE);
        return this;
    }

    public CardComponent addDescription(String description) {
        tvDescription.setText(description);
        tvDescription.setVisibility(VISIBLE);
        return this;
    }

    public CardComponent addPicture(Drawable drawable) {
        ivImage.setImageDrawable(drawable);
        imageView.setImageDrawable(drawable);
        ivImage.setVisibility(VISIBLE);
        return this;
    }

    public CardComponent isPictureRounded(boolean picture) {
        if (picture) {
            ivImage.setVisibility(GONE);
            imageView.setVisibility(VISIBLE);
        } else {
            ivImage.setVisibility(VISIBLE);
            imageView.setVisibility(GONE);
        }
        return this;
    }

    public CardComponent addButtonFirst(String button1, View.OnClickListener onClickListener) {
        buttonFirst.setText(button1);
        buttonFirst.setOnClickListener(onClickListener);
        return this;
    }

    public CardComponent addButtonSecond(String button2, View.OnClickListener onClickListener) {
        buttonSecond.setText(button2);
        buttonSecond.setOnClickListener(onClickListener);
        return this;
    }

    public CardComponent setComponentHashMap(List<CardList> cardLists) {
        for (int i = 0; i < cardLists.size(); i++) {
            LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setWeightSum(2);
            linearLayout1.setLayoutParams(linearLayoutParams);

            TextView textView = new TextView(linearLayout1.getContext());
            linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            textView.setText(cardLists.get(i).getTitle());
            textView.setTextColor(Color.parseColor("#818181"));
            textView.setGravity(Gravity.LEFT);
            textView.setLayoutParams(linearLayoutParams);

            TextView textView1 = new TextView(linearLayout1.getContext());
            linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            textView1.setText(cardLists.get(i).getTitle());
            textView1.setTextColor(Color.parseColor("#424242"));
            textView1.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView1.setGravity(Gravity.RIGHT);
            textView1.setLayoutParams(linearLayoutParams);

            linearLayout1.addView(textView);
            linearLayout1.addView(textView1);
            linearLayout.addView(linearLayout1);
        }
        return this;
    }

}
