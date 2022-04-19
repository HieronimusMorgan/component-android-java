/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.morg.mycomponent.R;

public class TextDescription extends LinearLayout {
    private TextView textView;

    public TextDescription(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textView = new TextView(context);
        textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(16);
        addView(textView);
    }

    public TextDescription setLabel(String label) {
        textView.setText(label);
        return this;
    }
}
