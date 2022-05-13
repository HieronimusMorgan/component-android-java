package com.morg.mycomponent.component.carouselview;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CarouselViewPagerScroller extends Scroller {

    private int scrollDuration = 600;

    public CarouselViewPagerScroller(Context context) {
        super(context);
    }

    public CarouselViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public int getScrollDuration() {
        return scrollDuration;
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, scrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, scrollDuration);
    }


}