package com.yee.jobhunting.utils;

import android.app.Activity;

import com.gyf.immersionbar.ImmersionBar;
import com.yee.jobhunting.R;

public class StatusBarUtils {



    public static void initStatusBar(Activity activity) {
        ImmersionBar.with(activity)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
    }


    public static void initWhiteStatusBar(Activity   activity) {
        ImmersionBar.with(activity)
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .init();
    }


}
