package com.ushi.example.chackable.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;

import com.ushi.example.chackable.R;


public class CheckableFrameLayout extends FrameLayout implements Checkable {

    private boolean mChecked;

    private static final int[] STATE_CHECKED = {android.R.attr.state_checked};

    public CheckableFrameLayout(Context context) {
        this(context, null);
    }

    public CheckableFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckableFrameLayout, defStyleAttr, 0);
        setChecked(ta.getBoolean(R.styleable.CheckableFrameLayout_android_checked, false));
        ta.recycle();
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked == checked) return;

        mChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + 1);
        if (mChecked) {
            mergeDrawableStates(states, STATE_CHECKED);
        }

        return states;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
