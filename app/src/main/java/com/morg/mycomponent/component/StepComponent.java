package com.morg.mycomponent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.morg.mycomponent.R;

import java.util.ArrayList;
import java.util.List;

public class StepComponent extends LinearLayout {
    private int totalStep;
    private String steps = "";
    private LinearLayout linearLayout1, linearLayout2;
    private LinearLayout.LayoutParams layoutParams;
    private TextView tvTitle, tvSteps;
    private TypedArray values;
    private String type;
    private List<View> views;
    private List<ImageView> imageViews;
    private List<String> titles;
    private int count = 0;

    public StepComponent(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public StepComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        totalStep = values.getInteger(R.styleable.component_count, 1);
        steps = " of " + totalStep;
        steps = " of " + totalStep;
        type = values.getString(R.styleable.component_type);
        validation();
    }

    public StepComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        values = getContext().obtainStyledAttributes(attrs, R.styleable.component, 0, 0);
        totalStep = values.getInteger(R.styleable.component_count, 1);
        steps = " of " + totalStep;
        type = values.getString(R.styleable.component_type);
    }

    private void validation() {
        if (type.equalsIgnoreCase("normal")) {
            views = new ArrayList<>();
            stepNormal();
        } else if (type.equalsIgnoreCase("check")) {
            imageViews = new ArrayList<>();
            stepCheck();
        }
    }

    private StepComponent stepNormal() {
        linearLayout1 = new LinearLayout(getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout1.setLayoutParams(layoutParams);
        linearLayout1.setOrientation(HORIZONTAL);
        linearLayout1.setGravity(Gravity.CENTER);
        linearLayout1.setPadding(30, 30, 30, 30);
        linearLayout1.setWeightSum(2);

        tvTitle = new TextView(linearLayout1.getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5F);
        tvTitle.setLayoutParams(layoutParams);
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);

        tvSteps = new TextView(linearLayout1.getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5F);
        tvSteps.setLayoutParams(layoutParams);
        tvSteps.setGravity(Gravity.RIGHT);
        tvSteps.setText(String.format("Step %d%s", count + 1, steps));

        linearLayout1.addView(tvTitle);
        linearLayout1.addView(tvSteps);

        linearLayout2 = new LinearLayout(getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(HORIZONTAL);
        linearLayout2.setWeightSum(totalStep);

        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5, 1);
        for (int i = 0; i < totalStep; i++) {
            View view = new View(linearLayout2.getContext());
            view.setLayoutParams(layoutParams);
            if (i == 0) view.setBackground(getResources().getDrawable(R.drawable.line_steps_ic));
            else view.setBackground(getResources().getDrawable(R.drawable.line_steps_white_ic));
            views.add(view);
            linearLayout2.addView(view);
        }

        addView(linearLayout1);
        addView(linearLayout2);
        return this;
    }

    private StepComponent stepCheck() {
        linearLayout2 = new LinearLayout(getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(HORIZONTAL);
        linearLayout2.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout2.setWeightSum(totalStep);
        linearLayout2.setPadding(30, 30, 30, 30);

        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5, 1);
        ImageView view;
        for (int i = 0; i < totalStep; i++) {
            view = new ImageView(linearLayout2.getContext());
            view.setPadding(5,5,5,5);
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            view.setLayoutParams(layoutParams);

            if (i == 0)
                view.setBackground(getResources().getDrawable(R.drawable.step_check_ic));
            else
                view.setBackground(getResources().getDrawable(R.drawable.step_uncheck_ic));
            imageViews.add(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            linearLayout2.addView(view);
        }
//        view = new ImageView(linearLayout2.getContext());
//        layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//        view.setLayoutParams(layoutParams);
//        view.setBackground(getResources().getDrawable(R.drawable.uncheck));
//        imageViews.add(view);
//        linearLayout2.addView(view);

        linearLayout1 = new LinearLayout(getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout1.setLayoutParams(layoutParams);
        linearLayout1.setOrientation(VERTICAL);
        linearLayout1.setGravity(Gravity.LEFT);
        linearLayout1.setWeightSum(2);

        tvSteps = new TextView(linearLayout1.getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5F);
        tvSteps.setLayoutParams(layoutParams);
        tvSteps.setText(String.format("%d%s", count + 1, steps));

        tvTitle = new TextView(linearLayout1.getContext());
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5F);
        tvTitle.setLayoutParams(layoutParams);
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);

        linearLayout1.addView(tvSteps);
        linearLayout1.addView(tvTitle);

        addView(linearLayout2);
        addView(linearLayout1);

        return this;
    }

    public StepComponent setTitle(List<String> title) {
        titles = title;
        tvTitle.setText(title.get(0));
        return this;
    }

    public StepComponent next() {
        if (type.equalsIgnoreCase("normal")) {
            if (count < totalStep - 1) {
                count++;
                views.get(count).setBackground(getResources().getDrawable(R.drawable.line_steps_ic));
                tvTitle.setText(titles.get(count));
                tvSteps.setText(String.format("Step %d%s", count + 1, steps));
            }
        } else if (type.equalsIgnoreCase("check")) {
            count++;
            if (count < totalStep) {
                imageViews.get(count).setBackground(getResources().getDrawable(R.drawable.step_check_ic));
                imageViews.get(count).setScaleType(ImageView.ScaleType.FIT_XY);
                tvTitle.setText(titles.get(count));
                tvSteps.setText(String.format("%d%s", count + 1, steps));
            }
        }

        return this;
    }

    public StepComponent previous() {
        if (type.equalsIgnoreCase("normal")) {
            if (count > 0) {
                views.get(count).setBackground(getResources().getDrawable(R.drawable.line_steps_white_ic));
                tvSteps.setText(String.format("Step %d%s", count, steps));
                count--;
                tvTitle.setText(titles.get(count));
            }
        } else if (type.equalsIgnoreCase("check") && count > 0) {
            if (count == totalStep) {
                imageViews.get(count).setBackground(getResources().getDrawable(R.drawable.uncheck));
                imageViews.get(count).setScaleType(ImageView.ScaleType.FIT_XY);
//                imageViews.get(count).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            } else if (count > 0) {
                imageViews.get(count).setBackground(getResources().getDrawable(R.drawable.step_uncheck_ic));
                imageViews.get(count).setScaleType(ImageView.ScaleType.FIT_XY);
//                imageViews.get(count).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            tvSteps.setText(String.format("%d%s", count, steps));
            count--;
            tvTitle.setText(titles.get(count));
        }
        return this;
    }

}
