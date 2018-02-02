package com.ushi.example.chackable.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Checkable;

import com.ushi.example.chackable.R;


public class CheckableImageView extends AppCompatImageView implements Checkable {

    private boolean mChecked;

    private static final int[] STATE_CHECKED = {android.R.attr.state_checked};

    public CheckableImageView(Context context) {
        this(context, null);
    }

    public CheckableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckableImageView, defStyleAttr, 0);
        setChecked(ta.getBoolean(R.styleable.CheckableImageView_android_checked, false));
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
