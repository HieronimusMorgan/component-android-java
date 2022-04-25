/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import com.morg.mycomponent.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class TextInputComponent extends LinearLayout {
    private TextView textView;
    private LayoutParams textViewParams, clpTextInputLayout;
    private TextInputEditText inputEditText;
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView autoCompleteTextView;
    private TypedArray values;
    private String header;
    private String hint;
    private String helper;
    private String prefix;
    private String button;
    private String type;
    private MaterialButton materialButton;
    private List<String> phoneNumbers;

    public TextInputComponent(@NonNull Context context) {
        super(context);
        inputText();
    }

    public TextInputComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        header = values.getString(R.styleable.component_header);
        hint = values.getString(R.styleable.component_hint);
        helper = values.getString(R.styleable.component_helper);
        prefix = values.getString(R.styleable.component_prefix);
        button = values.getString(R.styleable.component_button);
        validation(type);
    }

    public TextInputComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        type = values.getString(R.styleable.component_type);
        header = values.getString(R.styleable.component_header);
        hint = values.getString(R.styleable.component_hint);
        helper = values.getString(R.styleable.component_helper);
        prefix = values.getString(R.styleable.component_prefix);
        button = values.getString(R.styleable.component_button);
        validation(type);
    }


    private void validation(String type) {
        switch (type) {
            case "helper":
                inputTextWithHelper();
                break;
            case "prefix":
                inputTextWithPrefix();
                break;
            case "password":
                inputPassword();
                break;
            case "phone":
                phoneNumber();
                break;
            case "textbutton":
                inputTextButton();
                break;
            default:
                inputText();
                break;
        }
    }


    private TextInputComponent inputText() {
        textView = new TextView(getContext());
        setOrientation(VERTICAL);
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);

        textInputLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox));
        textInputLayout.setHintEnabled(false);
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));
        clpTextInputLayout = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(clpTextInputLayout);

        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setHint(hint);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(12, 12, 12, 12);

        addView(textInputLayout);
        return this;
    }

    private TextInputComponent inputTextWithHelper() {
        textView = new TextView(getContext());
        setOrientation(VERTICAL);
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);
        textInputLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox));
        textInputLayout.setHintEnabled(false);
        textInputLayout.setHelperTextEnabled(true);
        textInputLayout.setHelperText(helper);
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));
        clpTextInputLayout = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(clpTextInputLayout);

        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setHint(hint);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(12, 12, 12, 12);

        addView(textInputLayout);
        return this;
    }

    private TextInputComponent inputTextWithPrefix() {
        textView = new TextView(getContext());
        setOrientation(VERTICAL);
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);

        textInputLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox));
        textInputLayout.setHintEnabled(false);

        textInputLayout.setPrefixText(prefix);
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));
        textInputLayout.setExpandedHintEnabled(false);
        clpTextInputLayout = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(clpTextInputLayout);

        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setHint(hint);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(12, 12, 12, 12);

        addView(textInputLayout);
        return this;
    }

    private TextInputComponent phoneNumber() {
        textView = new TextView(getContext());
        setOrientation(VERTICAL);
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(layoutParams);

        textInputLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox));
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));
        RelativeLayout.LayoutParams clpTextInputLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        clpTextInputLayout.addRule(RelativeLayout.ALIGN_START, getId());
        clpTextInputLayout.addRule(RelativeLayout.ALIGN_BOTTOM, getId());
        clpTextInputLayout.addRule(RelativeLayout.ALIGN_TOP, getId());
        clpTextInputLayout.addRule(RelativeLayout.ALIGN_END, getId());

        textInputLayout.setLayoutParams(clpTextInputLayout);
        textInputLayout.setHintEnabled(false);
        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputEditText.setPadding(190, 30, 30, 30);
        inputEditText.setHint(hint);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(12, 12, 12, 12);

        TextInputLayout dropDownLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_FilledBox_ExposedDropdownMenu));
        dropDownLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED);
        RelativeLayout.LayoutParams dropDownLayoutParams = new RelativeLayout.LayoutParams(
                180, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dropDownLayoutParams.addRule(RelativeLayout.ALIGN_START, dropDownLayout.getId());
        dropDownLayoutParams.addRule(RelativeLayout.ALIGN_TOP, dropDownLayout.getId());
        dropDownLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, dropDownLayout.getId());
        dropDownLayoutParams.setMargins(3, 3, 0, 0);

        dropDownLayout.setPadding(0, 0, 0, 0);
        dropDownLayout.setBoxCornerRadii(5, 5, 5, 5);
        dropDownLayout.setBoxStrokeWidth(0);

        dropDownLayout.setBoxStrokeWidthFocused(0);
        dropDownLayout.setLayoutParams(dropDownLayoutParams);

        autoCompleteTextView = new AutoCompleteTextView(dropDownLayout.getContext());
        autoCompleteTextView.setInputType(InputType.TYPE_NULL);
        autoCompleteTextView.setText("+62", false);
        autoCompleteTextView.setTextSize(16F);
        dropDownLayout.addView(autoCompleteTextView);
        relativeLayout.addView(textInputLayout);
        relativeLayout.addView(dropDownLayout);
        addView(relativeLayout);
        setGravity(Gravity.VERTICAL_GRAVITY_MASK);
        return this;
    }

    private void inputListPhoneNumber(List<String> phoneNumbers) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                R.layout.dropdown_menu_popup_item, phoneNumbers);
        autoCompleteTextView.setAdapter(dataAdapter);
    }

    private TextInputComponent inputPassword() {
        header = values.getString(R.styleable.component_header);
        hint = values.getString(R.styleable.component_hint);
        textView = new TextView(getContext());
        setOrientation(VERTICAL);
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);

        textInputLayout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.TextInputLayoutStyle));
        textInputLayout.setHintEnabled(false);
        textInputLayout.setPasswordVisibilityToggleEnabled(true);
        textInputLayout.setPasswordVisibilityToggleDrawable(getResources().getDrawable(R.drawable.show_password_selector));

        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));
        clpTextInputLayout = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(clpTextInputLayout);
        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setHint(hint);
        inputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(12, 12, 12, 12);

        addView(textInputLayout);
        return this;
    }


    @SuppressLint("RestrictedApi")
    private TextInputComponent inputTextButton() {
        setOrientation(VERTICAL);
        textView = new TextView(getContext());
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(header);
        addView(textView);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setWeightSum(2);
        LayoutParams linearLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);

        textInputLayout = new TextInputLayout(new ContextThemeWrapper(linearLayout.getContext(), R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox));
        textInputLayout.setHintEnabled(false);
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(ContextCompat.getColor(textInputLayout.getContext(), android.R.color.transparent));

        clpTextInputLayout = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, (float) 1.9);
        textInputLayout.setLayoutParams(clpTextInputLayout);
        inputEditText = new TextInputEditText(textInputLayout.getContext());
        inputEditText.setHint(hint);
        textInputLayout.addView(inputEditText);
        textInputLayout.setBoxCornerRadii(6, 0, 6, 0);

        materialButton = new MaterialButton(linearLayout.getContext());
        LayoutParams materialButtonLayout = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 0.1);
        materialButtonLayout.setMargins(0, -12, 0, -12);
        materialButton.setLayoutParams(materialButtonLayout);
        materialButton.setBackground(getResources().getDrawable(R.drawable.button_text_1_border));
        materialButton.setAutoSizeTextTypeWithDefaults(TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE);
        materialButton.setText(button);
        linearLayout.addView(textInputLayout);
        linearLayout.addView(materialButton);
        addView(linearLayout);
        return this;
    }

    public String getText() {
        return inputEditText.getText().toString();
    }

    public void onClickTextButton(OnClickListener clickListener) {
        materialButton.setOnClickListener(clickListener);
    }

    public TextInputComponent isPasswordMatch(boolean password) {
        if (password) {
            textInputLayout.setHelperText("Password is match!");
            inputEditText.setBackground(getResources().getDrawable(R.drawable.password_match_border));
            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        } else {
            inputEditText.setBackground(getResources().getDrawable(R.drawable.password_not_match_border));
            textInputLayout.setHelperText("Password not match!");
            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        }
        return this;
    }
}
