/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.morg.mycomponent.R;

@SuppressLint("AppCompatCustomView")
public class TextHeader extends LinearLayout {
    private TextView textView;

    public TextHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textView = new TextView(context);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(20);
        addView(textView);
    }

    public TextHeader setLabel(String label) {
        textView.setText(label);
        return this;
    }
}
