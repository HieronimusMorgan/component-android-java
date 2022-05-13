package com.morg.mycomponent.component.carouselview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class CarouselViewPager extends ViewPager {
    private ImageClickListener imageClickListener;
    private float oldX = 0;
    private static final float SENS = 5;
    private CarouselViewPagerScroller mScroller = null;

    public void setImageClickListener(ImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public CarouselViewPager(Context context) {
        super(context);
        postInitViewPager();
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    private void postInitViewPager() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            mScroller = new CarouselViewPagerScroller(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
            Log.e("CarouselViewPager", "postInitViewPager: ", e);
        }
    }

    public void setTransitionVelocity(int scrollFactor) {
        mScroller.setScrollDuration(scrollFactor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                float newX = ev.getX();
                if (Math.abs(oldX - newX) < SENS) {
                    if (imageClickListener != null)
                        imageClickListener.onClick(getCurrentItem());
                    return true;
                }
                oldX = 0;
                break;
            default:
                //empty default
                break;
        }
        return super.onTouchEvent(ev);
    }

}