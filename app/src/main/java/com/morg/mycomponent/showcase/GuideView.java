/*
 * Copyright (c) Hieronimus Fredy Morgan
 */
package com.morg.mycomponent.showcase;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.Build;
import android.text.Spannable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.morg.mycomponent.showcase.config.DismissType;
import com.morg.mycomponent.showcase.config.Gravity;
import com.morg.mycomponent.showcase.config.PointerType;
import com.morg.mycomponent.showcase.listener.GuideListener;

@SuppressLint("ViewConstructor")
public class GuideView extends FrameLayout {

    private static final int INDICATOR_HEIGHT = 40;
    private static final int MESSAGE_VIEW_PADDING = 5;
    private static final int SIZE_ANIMATION_DURATION = 700;
    private static final int APPEARING_ANIMATION_DURATION = 400;
    private static final int CIRCLE_INDICATOR_SIZE = 6;
    private static final int LINE_INDICATOR_WIDTH_SIZE = 3;
    private static final int STROKE_CIRCLE_INDICATOR_SIZE = 3;
    private static final int RADIUS_SIZE_TARGET_RECT = 15;
    private static final int MARGIN_INDICATOR = 15;

    private static final int BACKGROUND_COLOR = 0x99000000;
    private static final int CIRCLE_INNER_INDICATOR_COLOR = 0xffcccccc;
    private static final int CIRCLE_INDICATOR_COLOR = Color.WHITE;
    private static final int LINE_INDICATOR_COLOR = Color.WHITE;

    private final Paint selfPaint = new Paint();
    private final Paint paintLine = new Paint();
    private final Paint paintCircle = new Paint();
    private final Paint paintCircleInner = new Paint();
    private final Paint targetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Xfermode xFerModeClear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    private int index;
    private final View target;
    private final Rect selfRect = new Rect();
    private final float density;
    private final GuideMessageView mMessageView;
    private RectF targetRect;
    private float stopY;
    private boolean isTop;
    private boolean mIsShowing;
    private int yMessageView = 0;
    private float startYLineAndCircle;
    private float circleIndicatorSize = 0;
    private float circleIndicatorSizeFinal;
    private float circleInnerIndicatorSize = 0;
    private float lineIndicatorWidthSize;
    private int messageViewPadding;
    private float marginGuide;
    private float strokeCircleWidth;
    private float indicatorHeight;
    private boolean isPerformedAnimationSize = false;
    private GuideListener mGuideListener;
    private Gravity mGravity;
    private DismissType dismissType;
    private PointerType pointerType;
    private TextView mCount;
    MaterialButton mBtnSkip;
    MaterialButton mBtnNext;
    private LinearLayout linearLayout;

    private GuideView(Context context, View view) {
        super(context);
        setWillNotDraw(false);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        this.target = view;
        density = context.getResources().getDisplayMetrics().density;
        init();

        if (view instanceof Targetable) {
            targetRect = ((Targetable) view).boundingRect();
        } else {
            int[] locationTarget = new int[2];
            target.getLocationOnScreen(locationTarget);
            targetRect = new RectF(
                    locationTarget[0],
                    locationTarget[1],
                    locationTarget[0] + target.getWidth(),
                    locationTarget[1] + target.getHeight()
            );
        }

        mMessageView = new GuideMessageView(getContext());
        mMessageView.setPadding(
                messageViewPadding,
                messageViewPadding,
                messageViewPadding,
                messageViewPadding
        );
        mMessageView.setColor(Color.WHITE);

        addView(
                mMessageView,
                new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        setMessageLocation(resolveMessageViewLocation());

        ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                setMessageLocation(resolveMessageViewLocation());

                if (target instanceof Targetable) {
                    targetRect = ((Targetable) target).boundingRect();
                } else {
                    int[] locationTarget = new int[2];
                    target.getLocationOnScreen(locationTarget);
                    targetRect = new RectF(
                            locationTarget[0],
                            locationTarget[1],
                            locationTarget[0] + target.getWidth(),
                            locationTarget[1] + target.getHeight()
                    );
                }

                selfRect.set(
                        getPaddingLeft(),
                        getPaddingTop(),
                        getWidth() - getPaddingRight(),
                        getHeight() - getPaddingBottom()
                );

                marginGuide = (int) (isTop ? marginGuide : -marginGuide);
                startYLineAndCircle = (isTop ? targetRect.bottom : targetRect.top) + marginGuide;
                stopY = yMessageView + indicatorHeight;
                startAnimationSize();
                getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }

    private GuideView(Context context, View view, int lastIndex, String sessionKey, int index, Boolean isAllowToShowCheckBox, Boolean isOnlyFirstTime, boolean gotoNewClassWithButton, Class<?> linkClass) {
        super(context);
        setWillNotDraw(false);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        this.target = view;
        this.index = index;
        density = context.getResources().getDisplayMetrics().density;
        init();

        if (view instanceof Targetable) {
            targetRect = ((Targetable) view).boundingRect();
        } else {
            int[] locationTarget = new int[2];
            target.getLocationOnScreen(locationTarget);
            targetRect = new RectF(
                    locationTarget[0],
                    locationTarget[1],
                    (float) locationTarget[0] + (float) target.getWidth(),
                    (float) locationTarget[1] + (float) target.getHeight()
            );
        }

        mMessageView = new GuideMessageView(getContext());
        mMessageView.setPadding(
                messageViewPadding,
                messageViewPadding,
                messageViewPadding,
                messageViewPadding
        );
        mMessageView.setColor(Color.WHITE);

        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(2);
        linearLayout.setPadding(30, 40, 30, 10);
        mCount = new TextView(linearLayout.getContext());
        mCount.setTextColor(Color.GRAY);
        mCount.setGravity(android.view.Gravity.START);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.4F);
        layoutParams.setMargins(0, 0, 60, 0);
        linearLayout.addView(mCount, layoutParams);
        if (index != lastIndex) {
            mBtnSkip = new MaterialButton(linearLayout.getContext(), null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
            mBtnSkip.setText("Skip");
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.3F);
            layoutParams.setMargins(6, 0, 6, 0);
            linearLayout.addView(mBtnSkip, layoutParams);
            mBtnSkip.setOnClickListener(view1 -> dismiss(lastIndex));

            mBtnNext = new MaterialButton(linearLayout.getContext());
            mBtnNext.setText("Next");
            mBtnNext.setOnClickListener(view1 -> dismiss(index));
            linearLayout.addView(mBtnNext, layoutParams);
        } else {
            mBtnSkip = new MaterialButton(linearLayout.getContext());
            mBtnSkip.setText("Finish");
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.3F);
            layoutParams.setMargins(6, 0, 6, 0);
            linearLayout.addView(mBtnSkip, layoutParams);

        }
        mMessageView.addView(
                linearLayout,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        addView(
                mMessageView,
                new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        setMessageLocation(resolveMessageViewLocation());

        ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                setMessageLocation(resolveMessageViewLocation());

                if (target instanceof Targetable) {
                    targetRect = ((Targetable) target).boundingRect();
                } else {
                    int[] locationTarget = new int[2];
                    target.getLocationOnScreen(locationTarget);
                    targetRect = new RectF(
                            locationTarget[0],
                            locationTarget[1],
                            (float) locationTarget[0] + (float) target.getWidth(),
                            (float) locationTarget[1] + (float) target.getHeight()
                    );
                }

                selfRect.set(
                        getPaddingLeft(),
                        getPaddingTop(),
                        getWidth() - getPaddingRight(),
                        getHeight() - getPaddingBottom()
                );

                marginGuide = (int) (isTop ? marginGuide : -marginGuide);
                startYLineAndCircle = (isTop ? targetRect.bottom : targetRect.top) + marginGuide;
                stopY = yMessageView + indicatorHeight;
                startAnimationSize();
                getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }


    private void startAnimationSize() {
        if (!isPerformedAnimationSize) {
            final ValueAnimator circleSizeAnimator = ValueAnimator.ofFloat(
                    0f,
                    circleIndicatorSizeFinal
            );
            circleSizeAnimator.addUpdateListener(valueAnimator -> {
                circleIndicatorSize = (float) circleSizeAnimator.getAnimatedValue();
                circleInnerIndicatorSize = (float) circleSizeAnimator.getAnimatedValue() - density;
                postInvalidate();
            });

            final ValueAnimator linePositionAnimator = ValueAnimator.ofFloat(
                    stopY,
                    startYLineAndCircle
            );
            linePositionAnimator.addUpdateListener(valueAnimator -> {
                startYLineAndCircle = (float) linePositionAnimator.getAnimatedValue();
                postInvalidate();
            });

            linePositionAnimator.setDuration(SIZE_ANIMATION_DURATION);
            linePositionAnimator.start();
            linePositionAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    //empty method
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    circleSizeAnimator.setDuration(SIZE_ANIMATION_DURATION);
                    circleSizeAnimator.start();
                    isPerformedAnimationSize = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    //empty method
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                    //empty method
                }
            });
        }
    }

    private void init() {
        lineIndicatorWidthSize = LINE_INDICATOR_WIDTH_SIZE * density;
        marginGuide = MARGIN_INDICATOR * density;
        indicatorHeight = INDICATOR_HEIGHT * density;
        messageViewPadding = (int) (MESSAGE_VIEW_PADDING * density);
        strokeCircleWidth = STROKE_CIRCLE_INDICATOR_SIZE * density;
        circleIndicatorSizeFinal = CIRCLE_INDICATOR_SIZE * density;
    }

    private int getNavigationBarSize() {
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private boolean isLandscape() {
        int displayMode = getResources().getConfiguration().orientation;
        return displayMode != Configuration.ORIENTATION_PORTRAIT;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (target != null) {

            selfPaint.setColor(BACKGROUND_COLOR);
            selfPaint.setStyle(Paint.Style.FILL);
            selfPaint.setAntiAlias(true);
            canvas.drawRect(selfRect, selfPaint);

            paintLine.setStyle(Paint.Style.FILL);
            paintLine.setColor(LINE_INDICATOR_COLOR);
            paintLine.setStrokeWidth(lineIndicatorWidthSize);
            paintLine.setAntiAlias(true);

            paintCircle.setStyle(Paint.Style.STROKE);
            paintCircle.setColor(CIRCLE_INDICATOR_COLOR);
            paintCircle.setStrokeCap(Paint.Cap.ROUND);
            paintCircle.setStrokeWidth(strokeCircleWidth);
            paintCircle.setAntiAlias(true);

            paintCircleInner.setStyle(Paint.Style.FILL);
            paintCircleInner.setColor(CIRCLE_INNER_INDICATOR_COLOR);
            paintCircleInner.setAntiAlias(true);

            final float x = (targetRect.left / 2 + targetRect.right / 2);

            switch (pointerType) {
                case CIRCLE:
                    canvas.drawLine(x, startYLineAndCircle, x, stopY, paintLine);
                    canvas.drawCircle(x, startYLineAndCircle, circleIndicatorSize, paintCircle);
                    canvas.drawCircle(x, startYLineAndCircle, circleInnerIndicatorSize, paintCircleInner);
                    break;
                case ARROW:
                    canvas.drawLine(x, startYLineAndCircle, x, stopY, paintLine);
                    Path path = new Path();
                    if (isTop) {
                        path.moveTo(x, startYLineAndCircle - (circleIndicatorSize * 2));
                    } else {
                        path.moveTo(x, startYLineAndCircle + (circleIndicatorSize * 2));
                    }
                    path.lineTo(x + circleIndicatorSize, startYLineAndCircle);
                    path.lineTo(x - circleIndicatorSize, startYLineAndCircle);
                    path.close();
                    canvas.drawPath(path, paintCircle);
                    break;
                case NONE:
                    //draw no line and no pointer
                    break;
            }
            targetPaint.setXfermode(xFerModeClear);
            targetPaint.setAntiAlias(true);

            if (target instanceof Targetable) {
                canvas.drawPath(((Targetable) target).guidePath(), targetPaint);
            } else {
                canvas.drawRoundRect(
                        targetRect,
                        RADIUS_SIZE_TARGET_RECT,
                        RADIUS_SIZE_TARGET_RECT,
                        targetPaint
                );
            }
        }
    }

    public boolean isShowing() {
        return mIsShowing;
    }

    public void dismiss(int index) {
        ((ViewGroup) ((Activity) getContext()).getWindow().getDecorView()).removeView(this);
        mIsShowing = false;
        if (mGuideListener != null) {
            mGuideListener.onDismiss(target, index);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onTouchSwitch(x, y);
            return true;
        }
        return false;
    }

    public void onTouchSwitch(float x, float y) {
        switch (dismissType) {
            case OUTSIDE:
                if (!isViewContains(mMessageView, x, y)) dismiss(index);
                break;
            case ANYWHERE:
                dismiss(index);
                break;
            case TARGET_VIEW:
                if (targetRect.contains(x, y)) {
                    target.performClick();
                    dismiss(index);
                }
                break;
            case SELF_VIEW:
                if (isViewContains(mMessageView, x, y)) dismiss(index);
                break;

            case OUTSIDE_TARGET_AND_MESSAGE:
                if (!(targetRect.contains(x, y) || isViewContains(mMessageView, x, y)))
                    dismiss(index);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dismissType);
        }
    }

    private boolean isViewContains(View view, float rx, float ry) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int w = view.getWidth();
        int h = view.getHeight();

        return !(rx < x || rx > x + w || ry < y || ry > y + h);
    }

    private void setMessageLocation(Point p) {
        mMessageView.setX(p.x);
        mMessageView.setY(p.y);
        postInvalidate();
    }

    public void updateGuideViewLocation() {
        requestLayout();
    }

    private Point resolveMessageViewLocation() {

        int xMessageView;
        if (mGravity == Gravity.CENTER) {
            xMessageView = (int) (targetRect.left - mMessageView.getWidth() / 2 + target.getWidth() / 2);
        } else {
            xMessageView = (int) (targetRect.right) - mMessageView.getWidth();
        }

        if (isLandscape()) {
            xMessageView -= getNavigationBarSize();
        }

        if (xMessageView + mMessageView.getWidth() > getWidth()) {
            xMessageView = getWidth() - mMessageView.getWidth();
        }
        if (xMessageView < 0) {
            xMessageView = 0;
        }

        //set message view bottom
        if ((targetRect.top + (indicatorHeight)) > getHeight() / 2f) {
            isTop = false;
            yMessageView = (int) (targetRect.top - mMessageView.getHeight() - indicatorHeight);
        }
        //set message view top
        else {
            isTop = true;
            yMessageView = (int) (targetRect.top + target.getHeight() + indicatorHeight);
        }

        if (yMessageView < 0) {
            yMessageView = 0;
        }

        return new Point(xMessageView, yMessageView);
    }

    public void show() {
        this.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        this.setClickable(false);

        ((ViewGroup) ((Activity) getContext()).getWindow().getDecorView()).addView(this);
        AlphaAnimation startAnimation = new AlphaAnimation(0.0f, 1.0f);
        startAnimation.setDuration(APPEARING_ANIMATION_DURATION);
        startAnimation.setFillAfter(true);
        this.startAnimation(startAnimation);
        mIsShowing = true;
    }

    public void setTitle(String str) {
        mMessageView.setTitle(str);
    }

    public void setCount(String str) {
        mCount.setText(str);
    }

    public void setContentText(String str) {
        mMessageView.setContentText(str);
    }

    public void setContentSpan(Spannable span) {
        mMessageView.setContentSpan(span);
    }

    public void setTitleTypeFace(Typeface typeFace) {
        mMessageView.setTitleTypeFace(typeFace);
    }

    public void setContentTypeFace(Typeface typeFace) {
        mMessageView.setContentTypeFace(typeFace);
    }

    public void setTitleTextSize(int size) {
        mMessageView.setTitleTextSize(size);
    }

    public void setContentTextSize(int size) {
        mMessageView.setContentTextSize(size);
    }

    public static class Builder {

        private View targetView;
        private int lastIndex;
        private String sessionKey;
        private int backGroundColor;
        private String title;
        private String contentText;
        private String count;
        private Gravity gravity;
        private DismissType dismissType;
        private PointerType pointerType;
        private final Context context;
        private Spannable contentSpan;
        private Typeface titleTypeFace, contentTypeFace;
        private GuideListener guideListener;
        private int titleTextSize;
        private int contentTextSize;
        private float lineIndicatorHeight;
        private float lineIndicatorWidthSize;
        private float circleIndicatorSize;
        private float circleInnerIndicatorSize;
        private float strokeCircleWidth;
        private String linkText;
        private Class<?> linkClass;
        private boolean isOnlyFirstTime;
        private boolean gotoNewClassWithButton;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTargetView(View view) {
            this.targetView = view;
            return this;
        }

        public Builder setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
            return this;
        }

        public Builder setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
            return this;
        }

        public Builder setOnlyFirstTime(boolean onlyFirstTime) {
            this.isOnlyFirstTime = onlyFirstTime;
            return this;
        }

        public Builder setBackGroundColor(int color) {
            this.backGroundColor = color;
            return this;
        }

        public Builder setGravity(Gravity gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCount(String count) {
            this.count = count;
            return this;
        }

        public Builder setLinkText(String linkText) {
            this.linkText = linkText;
            return this;
        }

        public Builder setLinkClass(Class<?> linkClass) {
            this.linkClass = linkClass;
            return this;
        }

        public Builder setGoToNewClassWithButton(boolean gotoNewClassWithButton) {
            this.gotoNewClassWithButton = gotoNewClassWithButton;
            return this;
        }

        public Builder setContentSpan(Spannable span) {
            this.contentSpan = span;
            return this;
        }

        public Builder setContentTypeFace(Typeface typeFace) {
            this.contentTypeFace = typeFace;
            return this;
        }

        public Builder setGuideListener(GuideListener guideListener) {
            this.guideListener = guideListener;
            return this;
        }

        public Builder setTitleTypeFace(Typeface typeFace) {
            this.titleTypeFace = typeFace;
            return this;
        }

        public Builder setContentTextSize(int size) {
            this.contentTextSize = size;
            return this;
        }

        public Builder setTitleTextSize(int size) {
            this.titleTextSize = size;
            return this;
        }

        public Builder setDismissType(DismissType dismissType) {
            this.dismissType = dismissType;
            return this;
        }

        public Builder setIndicatorHeight(float height) {
            this.lineIndicatorHeight = height;
            return this;
        }

        public Builder setIndicatorWidthSize(float width) {
            this.lineIndicatorWidthSize = width;
            return this;
        }

        public Builder setCircleIndicatorSize(float size) {
            this.circleIndicatorSize = size;
            return this;
        }

        public Builder setCircleInnerIndicatorSize(float size) {
            this.circleInnerIndicatorSize = size;
            return this;
        }

        public Builder setCircleStrokeIndicatorSize(float size) {
            this.strokeCircleWidth = size;
            return this;
        }

        public Builder setPointerType(PointerType pointerType) {
            this.pointerType = pointerType;
            return this;
        }

        public GuideView build(int index, boolean isAllowToShowCheckBox) {
            GuideView guideView = new GuideView(context, targetView, lastIndex, sessionKey
                    , index, isAllowToShowCheckBox
                    , isOnlyFirstTime, gotoNewClassWithButton, linkClass);
            guideView.mGravity = gravity != null ? gravity : Gravity.AUTO;
            guideView.dismissType = dismissType != null ? dismissType : DismissType.TARGET_VIEW;
            guideView.pointerType = pointerType != null ? pointerType : PointerType.CIRCLE;
            float density = context.getResources().getDisplayMetrics().density;

            guideView.setTitle(title);
            guideView.setCount(count);
            if (contentText != null) {
                guideView.setContentText(contentText);
            }
            if (titleTextSize != 0) {
                guideView.setTitleTextSize(titleTextSize);
            }
            if (contentTextSize != 0) {
                guideView.setContentTextSize(contentTextSize);
            }
            if (contentSpan != null) {
                guideView.setContentSpan(contentSpan);
            }
            if (titleTypeFace != null) {
                guideView.setTitleTypeFace(titleTypeFace);
            }
            if (contentTypeFace != null) {
                guideView.setContentTypeFace(contentTypeFace);
            }
            if (guideListener != null) {
                guideView.mGuideListener = guideListener;
            }
            if (lineIndicatorHeight != 0) {
                guideView.indicatorHeight = lineIndicatorHeight * density;
            }
            if (lineIndicatorWidthSize != 0) {
                guideView.lineIndicatorWidthSize = lineIndicatorWidthSize * density;
            }
            if (circleIndicatorSize != 0) {
                guideView.circleIndicatorSize = circleIndicatorSize * density;
            }
            if (circleInnerIndicatorSize != 0) {
                guideView.circleInnerIndicatorSize = circleInnerIndicatorSize * density;
            }
            if (strokeCircleWidth != 0) {
                guideView.strokeCircleWidth = strokeCircleWidth * density;
            }
            if (index == (lastIndex - 1)) {
                guideView.mBtnSkip.setVisibility(INVISIBLE);
                guideView.mBtnNext.setText("FINISH");
            }

            return guideView;
        }
    }
}


