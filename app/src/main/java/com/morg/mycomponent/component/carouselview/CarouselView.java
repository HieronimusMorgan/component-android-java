package com.morg.mycomponent.component.carouselview;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.morg.mycomponent.R;

import java.util.Timer;
import java.util.TimerTask;

public class CarouselView extends FrameLayout {

    private static final int DEFAULT_GRAVITY = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private static final int DEFAULT_SLIDE_INTERVAL = 3500;
    private static final int DEFAULT_SLIDE_VELOCITY = 400;
    public static final int DEFAULT_INDICATOR_VISIBILITY = 0;
    private int pageCount;
    private int slideInterval = DEFAULT_SLIDE_INTERVAL;
    private int indicatorGravity = DEFAULT_GRAVITY;
    private int indicatorMarginVertical;
    private int indicatorMarginHorizontal;

    private CarouselViewPager containerViewPager;
    private CirclePageIndicator circleIndicator;
    private ViewListener viewListener = null;
    private ImageListener imageListener = null;

    private Timer swipeTimer;
    private SwipeTask swipeTask;

    private boolean autoPlay;
    private boolean disableAutoPlayOnUserInteraction;

    private int previousState;

    private ViewPager.PageTransformer pageTransformer;
    private boolean animateOnBoundary = true;

    public CarouselView(Context context) {
        super(context);
    }

    public CarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public CarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        if (!isInEditMode()) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_carousel, this, true);
            containerViewPager = (CarouselViewPager) view.findViewById(R.id.containerViewPager);
            circleIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

            containerViewPager.addOnPageChangeListener(carouselOnPageChangeListener);

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CarouselView, defStyleAttr, 0);
            try {
                indicatorMarginVertical = a.getDimensionPixelSize(R.styleable.CarouselView_indicatorMarginVertical, getResources().getDimensionPixelSize(R.dimen.default_indicator_margin_vertical));
                indicatorMarginHorizontal = a.getDimensionPixelSize(R.styleable.CarouselView_indicatorMarginHorizontal, getResources().getDimensionPixelSize(R.dimen.default_indicator_margin_horizontal));
                setPageTransformInterval(a.getInt(R.styleable.CarouselView_pageTransformInterval, DEFAULT_SLIDE_VELOCITY));
                setSlideInterval(a.getInt(R.styleable.CarouselView_slideInterval, DEFAULT_SLIDE_INTERVAL));
                setOrientation(a.getInt(R.styleable.CarouselView_indicatorOrientation, LinearLayout.HORIZONTAL));
                setIndicatorGravity(a.getInt(R.styleable.CarouselView_indicatorGravity, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL));
                setAutoPlay(a.getBoolean(R.styleable.CarouselView_autoPlay, true));
                setDisableAutoPlayOnUserInteraction(a.getBoolean(R.styleable.CarouselView_disableAutoPlayOnUserInteraction, false));
                setAnimateOnBoundary(a.getBoolean(R.styleable.CarouselView_animateOnBoundary, true));
                setPageTransformer(a.getInt(R.styleable.CarouselView_pageTransformer, CarouselViewPagerTransformer.DEFAULT));

                int indicatorVisibility = a.getInt(R.styleable.CarouselView_indicatorVisibility, CarouselView.DEFAULT_INDICATOR_VISIBILITY);

                setIndicatorVisibility(indicatorVisibility);

                checkIndicatorVisibility(indicatorVisibility, a);
            } finally {
                a.recycle();
            }
        }
    }

    private void checkIndicatorVisibility(int indicatorVisibility, TypedArray a) {
        if (indicatorVisibility == View.VISIBLE) {
            int fillColor = a.getColor(R.styleable.CarouselView_fillColor, 0);
            if (fillColor != 0) {
                setFillColor(fillColor);
            }
            int pageColor = a.getColor(R.styleable.CarouselView_pageColor, 0);
            if (pageColor != 0) {
                setPageColor(pageColor);
            }
            float radius = a.getDimensionPixelSize(R.styleable.CarouselView_radius, 0);
            if (radius != 0) {
                setRadius(radius);
            }
            setSnap(a.getBoolean(R.styleable.CarouselView_snap, getResources().getBoolean(R.bool.default_circle_indicator_snap)));
            int strokeColor = a.getColor(R.styleable.CarouselView_strokeColor, 0);
            if (strokeColor != 0) {
                setStrokeColor(strokeColor);
            }
            float strokeWidth = a.getDimensionPixelSize(R.styleable.CarouselView_strokeWidth, 0);
            if (strokeWidth != 0) {
                setStrokeWidth(strokeWidth);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetScrollTimer();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        playCarousel();
    }

    public int getSlideInterval() {
        return slideInterval;
    }

    public void setSlideInterval(int slideInterval) {
        this.slideInterval = slideInterval;

        if (null != containerViewPager) {
            playCarousel();
        }
    }

    public void resetSlideInterval(int slideInterval) {
        setSlideInterval(slideInterval);
        if (null != containerViewPager) {
            playCarousel();
        }
    }

    public void setPageTransformInterval(int pageTransformInterval) {
        containerViewPager.setTransitionVelocity(pageTransformInterval);
    }

    public ViewPager.PageTransformer getPageTransformer() {
        return pageTransformer;
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        this.pageTransformer = pageTransformer;
        containerViewPager.setPageTransformer(true, pageTransformer);
    }

    public void setPageTransformer(@CarouselViewPagerTransformer.Transformer int transformer) {
        setPageTransformer(new CarouselViewPagerTransformer(transformer));
    }

    public void setAnimateOnBoundary(boolean animateOnBoundary) {
        this.animateOnBoundary = animateOnBoundary;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public boolean isDisableAutoPlayOnUserInteraction() {
        return disableAutoPlayOnUserInteraction;
    }

    private void setDisableAutoPlayOnUserInteraction(boolean disableAutoPlayOnUserInteraction) {
        this.disableAutoPlayOnUserInteraction = disableAutoPlayOnUserInteraction;
    }

    private void setData() {
        CarouselPagerAdapter carouselPagerAdapter = new CarouselPagerAdapter(getContext());
        containerViewPager.setAdapter(carouselPagerAdapter);
        if (getPageCount() > 1) {
            circleIndicator.setViewPager(containerViewPager);
            circleIndicator.requestLayout();
            circleIndicator.invalidate();
            containerViewPager.setOffscreenPageLimit(getPageCount());
            playCarousel();
        }
    }

    private void stopScrollTimer() {
        if (null != swipeTimer) swipeTimer.cancel();
        if (null != swipeTask) swipeTask.cancel();
    }


    private void resetScrollTimer() {
        stopScrollTimer();
        swipeTask = new SwipeTask();
        swipeTimer = new Timer();
    }

    public void playCarousel() {
        resetScrollTimer();
        if (autoPlay && slideInterval > 0 && containerViewPager.getAdapter() != null && containerViewPager.getAdapter().getCount() > 1) {
            swipeTimer.schedule(swipeTask, slideInterval, slideInterval);
        }
    }

    public void pauseCarousel() {
        resetScrollTimer();
    }

    public void stopCarousel() {
        this.autoPlay = false;
    }

    private class CarouselPagerAdapter extends PagerAdapter {
        private Context mContext;

        public CarouselPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            Object objectToReturn = null;
            if (imageListener != null) {
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                objectToReturn = imageView;
                imageListener.setImageForPosition(position, imageView);
                collection.addView(imageView);
            } else if (viewListener != null) {

                View view = viewListener.setViewForPosition(position);

                if (view != null) {
                    objectToReturn = view;
                    collection.addView(view);
                }
            }

            return objectToReturn;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return getPageCount();
        }
    }

    ViewPager.OnPageChangeListener carouselOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //Programmatic scroll
        }

        @Override
        public void onPageSelected(int position) {
            //Empty Method
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (previousState == ViewPager.SCROLL_STATE_DRAGGING
                    && state == ViewPager.SCROLL_STATE_SETTLING) {
                if (disableAutoPlayOnUserInteraction) pauseCarousel();
                else playCarousel();
            }

            previousState = state;

        }
    };

    private class SwipeTask extends TimerTask {
        public void run() {
            containerViewPager.post(() -> {
                int nextPage = (containerViewPager.getCurrentItem() + 1) % getPageCount();
                containerViewPager.setCurrentItem(nextPage, 0 != nextPage || animateOnBoundary);
            });
        }
    }

    public void setImageListener(ImageListener mImageListener) {
        this.imageListener = mImageListener;
    }

    public void setViewListener(ViewListener mViewListener) {
        this.viewListener = mViewListener;
    }

    public void setImageClickListener(ImageClickListener imageClickListener) {
        containerViewPager.setImageClickListener(imageClickListener);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int mPageCount) {
        this.pageCount = mPageCount;
        setData();
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        containerViewPager.addOnPageChangeListener(listener);
    }

    public void clearOnPageChangeListeners() {
        containerViewPager.clearOnPageChangeListeners();
    }

    public void setCurrentItem(int item) {
        containerViewPager.setCurrentItem(item);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        containerViewPager.setCurrentItem(item, smoothScroll);
    }

    public int getCurrentItem() {
        return containerViewPager.getCurrentItem();
    }

    public int getIndicatorMarginVertical() {
        return indicatorMarginVertical;
    }

    public void setIndicatorMarginVertical(int indicatorMarginVertical) {
        this.indicatorMarginVertical = indicatorMarginVertical;
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.topMargin = this.indicatorMarginVertical;
        params.bottomMargin = this.indicatorMarginVertical;
    }

    public int getIndicatorMarginHorizontal() {
        return indicatorMarginHorizontal;
    }

    public CarouselViewPager getContainerViewPager() {
        return containerViewPager;
    }

    public void setIndicatorMarginHorizontal(int indicatorMarginHorizontal) {
        this.indicatorMarginHorizontal = indicatorMarginHorizontal;
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.leftMargin = this.indicatorMarginHorizontal;
        params.rightMargin = this.indicatorMarginHorizontal;
    }

    public int getIndicatorGravity() {
        return indicatorGravity;
    }

    public void setIndicatorGravity(int gravity) {
        indicatorGravity = gravity;
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.gravity = indicatorGravity;
        params.setMargins(indicatorMarginHorizontal, indicatorMarginVertical, indicatorMarginHorizontal, indicatorMarginVertical);
        circleIndicator.setLayoutParams(params);
    }

    public void setIndicatorVisibility(int visibility) {
        circleIndicator.setVisibility(visibility);
    }

    public int getOrientation() {
        return circleIndicator.getOrientation();
    }

    public int getFillColor() {
        return circleIndicator.getFillColor();
    }

    public int getStrokeColor() {
        return circleIndicator.getStrokeColor();
    }

    public void setSnap(boolean snap) {
        circleIndicator.setSnap(snap);
    }

    public void setRadius(float radius) {
        circleIndicator.setRadius(radius);
    }

    public float getStrokeWidth() {
        return circleIndicator.getStrokeWidth();
    }

    public Drawable getIndicatorBackground() {
        return circleIndicator.getBackground();
    }

    public void setFillColor(int fillColor) {
        circleIndicator.setFillColor(fillColor);
    }

    public int getPageColor() {
        return circleIndicator.getPageColor();
    }

    public void setOrientation(int orientation) {
        circleIndicator.setOrientation(orientation);
    }

    public boolean isSnap() {
        return circleIndicator.isSnap();
    }

    public void setStrokeColor(int strokeColor) {
        circleIndicator.setStrokeColor(strokeColor);
    }

    public float getRadius() {
        return circleIndicator.getRadius();
    }

    public void setPageColor(int pageColor) {
        circleIndicator.setPageColor(pageColor);
    }

    public void setStrokeWidth(float strokeWidth) {
        circleIndicator.setStrokeWidth(strokeWidth);
        int padding = (int) strokeWidth;
        circleIndicator.setPadding(padding, padding, padding, padding);
    }
}
