/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.morg.mycomponent.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

@SuppressLint("UseCompatLoadingForDrawables")
public class SnackBarComponent {
    private final Context context;

    public SnackBarComponent(Context context) {
        this.context = context;
    }

    public void toast(String message) {
        MaterialCardView view = new MaterialCardView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_rounded_background));

        LinearLayout linearLayout = new LinearLayout(view.getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(60, 40, 60, 40);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView textView = new TextView(linearLayout.getContext());
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        linearLayout.addView(textView);
        view.addView(linearLayout);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 80);
        toast.setView(view);
        toast.show();

    }

    public void toastButton(String message, String button, View.OnClickListener onClickListener) {
        MaterialCardView view = new MaterialCardView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_rounded_background));

        LinearLayout linearLayout = new LinearLayout(view.getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(60, 0, 60, 0);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setWeightSum(2);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(linearLayout.getContext());
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.9F);
        textViewParams.setMargins(0, 0, 8, 0);
        textView.setText(message);
        textView.setLayoutParams(textViewParams);
        textView.setTextColor(context.getResources().getColor(R.color.white));

        MaterialButton materialButton = new MaterialButton(linearLayout.getContext());
        LinearLayout.LayoutParams materialButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.1F);
        materialButton.setLayoutParams(materialButtonParams);
        materialButton.setText(button);
        linearLayout.addView(textView);
        linearLayout.addView(materialButton);
        view.addView(linearLayout);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 80);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastCounting(String message, Drawable drawable, int second, Retry retry) {
        MaterialCardView view = new MaterialCardView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_rounded_background));

        LinearLayout linearLayout = new LinearLayout(view.getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(60, 40, 45, 40);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setWeightSum(2);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        if (drawable != null) {
            ImageView imageView = new ImageView(linearLayout.getContext());
            LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.1F);
            imageViewParams.setMargins(0, 0, 10, 0);
            imageView.setLayoutParams(imageViewParams);
            imageView.setImageDrawable(drawable);
            linearLayout.addView(imageView);
        }

        TextView textView = new TextView(linearLayout.getContext());
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.9F);
        textView.setLayoutParams(textViewParams);
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        linearLayout.addView(textView);

        view.addView(linearLayout);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 80);
        toast.setView(view);
        new CountDownTimer((second * 1000), 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                textView.setText(String.format("%s. Retry in %s seconds", message, String.format("%d", seconds)));
                toast.show();
            }

            public void onFinish() {
                toast.show();
                retry.retry();
            }
        }.start();

    }

    public interface Retry {
        void retry();
    }

}
