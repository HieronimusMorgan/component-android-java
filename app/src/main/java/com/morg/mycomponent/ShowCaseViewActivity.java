package com.morg.mycomponent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.morg.mycomponent.databinding.ActivityShowCaseViewBinding;
import com.morg.mycomponent.showcase.ShowGuideView;
import com.morg.mycomponent.showcase.model.GuideModel;

import java.util.ArrayList;
import java.util.List;

public class ShowCaseViewActivity extends AppCompatActivity {
    private ActivityShowCaseViewBinding binding;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private View view7;
    private View view8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCaseViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        view1 = binding.periode;
        view2 = binding.acApprovalType;
        view3 = binding.acRegion;
        view4 = binding.acApprover;
        view5 = binding.acSend;
        view6 = binding.username;
        view7 = binding.btnProcess;
        view8 = binding.btnReset;

        List<GuideModel> list = new ArrayList<>();
        list.add(new GuideModel(view1, "ini title view 1", "ini message view 1"));
        list.add(new GuideModel(view2, "ini title view 2", "ini message view 2"));
        list.add(new GuideModel(view3, "ini title view 3", "ini message view 3"));
        list.add(new GuideModel(view4, "ini title view 4", "ini message view 4"));
        list.add(new GuideModel(view5, "ini title view 5", "ini message view 5"));
        list.add(new GuideModel(view6, "ini title view 6", "ini message view 6"));
        list.add(new GuideModel(view7, "ini title view 7", "ini message view 7"));
        list.add(new GuideModel(view8, "ini title view 8", "ini message view 8"));
        ShowGuideView showGuideView = new ShowGuideView(getResources().getColor(androidx.cardview.R.color.cardview_dark_background), false);
        showGuideView.showGuide(this, list, "guideLogin");
    }

}