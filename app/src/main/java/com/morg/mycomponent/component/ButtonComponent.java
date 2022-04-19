/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class ButtonComponent extends MaterialButton {
    private static final String DEFAULT_TEXT = "Default Button";
    private static final String DEFAULT_TEXT_COLOR = "#FFFFFFFF";
    private static final String DEFAULT_BUTTON_COLOR = "#9B3789";
    private static final int DEFAULT_CORNER_RADIUS = 15;

    public ButtonComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setText(DEFAULT_TEXT);
        setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        setBackgroundColor(Color.parseColor(DEFAULT_BUTTON_COLOR));
        setCornerRadius(DEFAULT_CORNER_RADIUS);
    }

    public void setButtonColor(String buttonColor) {
        setBackgroundColor(Color.parseColor(buttonColor));
    }

    public void setButtonText(String buttonText) {
        setText(buttonText);
    }

    public void setCornerRad(int cornerRadius) {
        setCornerRadius(cornerRadius);
    }
}