package com.morg.mycomponent.component;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.morg.mycomponent.R;

public class ProgressDialogComponent extends Dialog {

    private TextView textHeader;
    private CircularProgressIndicator circularProgressIndicator;
    private LinearLayout linearLayout;

    public ProgressDialogComponent(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(60, 30, 60, 30);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(60, 0, 60, 0);
        circularProgressIndicator = new CircularProgressIndicator(linearLayout.getContext());
        circularProgressIndicator.setIndeterminate(true);
        linearLayout.addView(circularProgressIndicator);

        textHeader = new TextView(linearLayout.getContext());
        LinearLayout.LayoutParams textHeaderParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textHeaderParam.setMargins(0, 30, 0, 0);
        textHeader.setGravity(Gravity.CENTER);
        textHeader.setLayoutParams(textHeaderParam);
        linearLayout.addView(textHeader);
        addContentView(linearLayout, linearLayoutParams);
    }

    public void setMessage(String message) {
        textHeader.setText(message);
    }

    @Override
    public void onBackPressed() {
        //Nonactive BackPressed
//        super.onBackPressed();
    }
}
