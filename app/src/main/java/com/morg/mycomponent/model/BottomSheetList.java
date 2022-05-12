package com.morg.mycomponent.model;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class BottomSheetList {
    private String name;
    private Drawable icon;
    private Intent intent;

    public BottomSheetList() {
    }

    public BottomSheetList(String name, Drawable icon, Intent intent) {
        this.name = name;
        this.icon = icon;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
