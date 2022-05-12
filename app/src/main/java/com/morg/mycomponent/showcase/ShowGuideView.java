package com.morg.mycomponent.showcase;

import android.content.Context;
import android.view.View;

import com.morg.mycomponent.showcase.config.DismissType;
import com.morg.mycomponent.showcase.config.Gravity;
import com.morg.mycomponent.showcase.config.PointerType;
import com.morg.mycomponent.showcase.model.GuideModel;
import com.morg.mycomponent.showcase.storage.SessionGuide;

import java.util.List;

public class ShowGuideView {
    private boolean isOnlyFirstTime;
    private GuideView mGuideView;
    private GuideView.Builder builder;
    private int color;
    private boolean isAllowToShowCheckBox;
    private List<GuideModel> guideModelList;

    public ShowGuideView(int background, Boolean isAllowToShowCheckBox) {
        this.color = background;
        this.isAllowToShowCheckBox = isAllowToShowCheckBox;
    }

    public void showGuide(Context context, List<GuideModel> guideModelList, String sessionKey) {
        SessionGuide.beginInitialization(context);
        this.guideModelList = guideModelList;
        if (!SessionGuide.getSessionGlobalBoolean(sessionKey) && guideModelList.size() > 0) {
            builder = new GuideView.Builder(context)
                    .setTitle(guideModelList.get(0).getTitle())
                    .setCount(1 + " of " + guideModelList.size())
                    .setContentText(guideModelList.get(0).getMessage())
                    .setGravity(Gravity.CENTER)
                    .setDismissType(DismissType.TARGET_VIEW)
                    .setPointerType(PointerType.CIRCLE)
                    .setLastIndex(guideModelList.size())
                    .setTargetView(guideModelList.get(0).getView())
                    .setGuideListener((view, index) -> {
                        index++;
                        if (index < guideModelList.size()) {
                            setTitleMessageGuide(guideModelList.get(index).getTitle()
                                    , guideModelList.get(index).getMessage()
                                    , guideModelList.get(index).getView()
                                    , guideModelList.get(index).getLinkClass()
                                    , guideModelList.get(index).getLinkText()
                                    , guideModelList.get(index).isGotoNewClassWithButton()
                                    , index);
                        } else {
                            return;
                        }

                        mGuideView = builder.build(index, isAllowToShowCheckBox);
                        mGuideView.show();
                    });

            mGuideView = builder.build(0, isAllowToShowCheckBox);
            mGuideView.show();
        }
    }

    private void setTitleMessageGuide(String title, String message, View view, Class<?> linkClass, String linkText, boolean gotoNewClassWithButton, int index) {

        builder.setLinkClass(linkClass);
        builder.setGoToNewClassWithButton(gotoNewClassWithButton);
        builder.setLinkText(linkText);

        builder.setTitle(title).setCount((index + 1) + " of " + guideModelList.size())
                .setContentText(message)
                .setLastIndex(guideModelList.size())
                .setTargetView(view).build(index, isAllowToShowCheckBox);

    }

}
