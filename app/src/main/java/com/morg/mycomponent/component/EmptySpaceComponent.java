/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.morg.mycomponent.R;

public class EmptySpaceComponent extends LinearLayout {

    private TypedArray values;
    private String message;
    private Drawable image;

    public EmptySpaceComponent(Context context) {
        super(context);
    }

    public EmptySpaceComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        message = values.getString(R.styleable.component_message);
        image = values.getDrawable(R.styleable.component_image);
        init();
    }

    public EmptySpaceComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        message = values.getString(R.styleable.component_message);
        image = values.getDrawable(R.styleable.component_image);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        ImageView imageView = new ImageView(getContext());
        LayoutParams imageParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageParams);
        imageView.setImageDrawable(image);
        TextView textView = new TextView(getContext());
        LayoutParams textViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(message);
        addView(imageView);
        addView(textView);
    }


}
