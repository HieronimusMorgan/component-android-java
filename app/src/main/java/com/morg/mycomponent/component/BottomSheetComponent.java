/*
 * Copyright (c) Hieronimus Fredy Morgan
 */

package com.morg.mycomponent.component;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.morg.mycomponent.R;
import com.morg.mycomponent.model.BottomSheetList;

import java.util.List;

public class BottomSheetComponent extends BottomSheetDialogFragment {
    private TextView tvTitle, tvDescription;
    private MaterialButton btnYes, btnNo;
    private List<BottomSheetList> bottomSheetListList;
    private GridView gridView;
    private LinearLayout linearLayout;
    private int numColumn = 3;
    private LinearLayout.LayoutParams layoutParams;
    private String title = "Title";

    public BottomSheetComponent(Context context) {

        linearLayout = new LinearLayout(context);
        linearLayout.setPadding(20, 50, 20, 20);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(linearLayout.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(layoutParams);

        tvDescription = new TextView(linearLayout.getContext());
        tvDescription.setTextSize(14);
        tvDescription.setLayoutParams(layoutParams);

        LinearLayout linearLayout1 = new LinearLayout(linearLayout.getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setWeightSum(2);
        linearLayout1.setLayoutParams(layoutParams);

        btnYes = new MaterialButton(linearLayout1.getContext());
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.setMargins(10, 10, 10, 10);
        btnYes.setLayoutParams(layoutParams);

        btnNo = new MaterialButton(context, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        btnNo.setLayoutParams(layoutParams);

        linearLayout1.addView(btnNo);
        linearLayout1.addView(btnYes);

        linearLayout.addView(tvTitle);
        linearLayout.addView(tvDescription);
        linearLayout.addView(linearLayout1);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        linearLayout.setLayoutParams(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return linearLayout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetUndrag);
    }

    public BottomSheetComponent setTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
        return this;
    }

    public BottomSheetComponent setDescription(String description) {
        tvDescription.setText(description);
        return this;
    }

    public BottomSheetComponent isButtonYes(Boolean button) {
        if (button) {
            btnNo.setVisibility(View.GONE);
        }
        return this;
    }

    public BottomSheetComponent setButtonYes(String btnYes) {
        this.btnYes.setText(btnYes);
        return this;
    }

    public BottomSheetComponent setButtonNo(String btnNo) {
        this.btnNo.setText(btnNo);
        return this;
    }

    public BottomSheetComponent setOnClickButtonYes(View.OnClickListener onClickListener) {
        btnYes.setOnClickListener(view -> {
            onClickListener.onClick(view);
            dismiss();
        });
        return this;
    }

    public BottomSheetComponent setOnClickButtonNo(View.OnClickListener onClickListener) {
        btnNo.setOnClickListener(view -> {
            onClickListener.onClick(view);
            dismiss();
        });
        return this;
    }

    public BottomSheetComponent setNumColumn(int numColumn) {
        this.numColumn = numColumn;
        return this;
    }

    public BottomSheetComponent setListData(List<BottomSheetList> bottomSheetListList) {
        this.bottomSheetListList = bottomSheetListList;
        linearLayout.removeAllViews();
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        tvTitle = new TextView(linearLayout.getContext());
        tvTitle.setTextSize(20);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 20);
        tvTitle.setLayoutParams(layoutParams);
        tvTitle.setText(title);
        gridView = new GridView(linearLayout.getContext());
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(layoutParams);
        gridView.setNumColumns(numColumn);
        gridView.setHorizontalSpacing(10);
        gridView.setVerticalSpacing(5);
        gridView.setPadding(20, 20, 20, 20);
        CustomAdapter customAdapter = new CustomAdapter(linearLayout.getContext(), bottomSheetListList);
        gridView.setAdapter(customAdapter);
        linearLayout.addView(tvTitle);
        linearLayout.addView(gridView);
        return this;
    }
}

class CustomAdapter extends BaseAdapter {
    Context context;
    List<BottomSheetList> bottomSheetListList;

    public CustomAdapter(Context applicationContext, List<BottomSheetList> bottomSheetListList) {
        this.context = applicationContext;
        this.bottomSheetListList = bottomSheetListList;
    }

    @Override
    public int getCount() {
        return bottomSheetListList.size();
    }

    @Override
    public BottomSheetList getItem(int i) {
        return bottomSheetListList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MaterialCardView materialCardView = new MaterialCardView(context);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(20, 20, 20, 20);
//        materialCardView.setLayoutParams(linearLayoutParams);
        materialCardView.setRadius(20);
//        materialCardView.setCardElevation(10);
        LinearLayout linearLayout = new LinearLayout(materialCardView.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParams.setMargins(20, 20, 20, 20);
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(linearLayout.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.5F);
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(20, 20, 20, 20);
        imageView.setImageDrawable(bottomSheetListList.get(i).getIcon());

        TextView textView = new TextView(linearLayout.getContext());
        linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(linearLayoutParams);
        textView.setText(bottomSheetListList.get(i).getName());
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        materialCardView.addView(linearLayout);
        materialCardView.setOnClickListener(view1 -> context.startActivity(bottomSheetListList.get(i).getIntent()));
        return materialCardView;
    }
}
