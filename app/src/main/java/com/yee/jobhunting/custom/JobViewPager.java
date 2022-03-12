package com.yee.jobhunting.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class JobViewPager extends ViewPager {

// 自定义一个viewPager : 给setCurrentItem方法设置一个默认参数false, 解决切换时的多页闪烁问题

    public JobViewPager(@NonNull Context context) {
        super(context);
    }

    public JobViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        // smoothScroll=false 这个参数能解决切换时的多页闪烁问题
        super.setCurrentItem(item,false);
    }
}
