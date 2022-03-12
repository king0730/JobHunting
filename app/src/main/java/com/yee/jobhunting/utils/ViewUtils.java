package com.yee.jobhunting.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yee.jobhunting.R;

public class ViewUtils {
    /*加载绿色标题栏（有返回按钮）*/
    public static void initTitle(Activity activity, String titleName) {
        ImageView back = activity.findViewById(R.id.title_back);
        TextView title = activity.findViewById(R.id.title_name);

        title.setText(titleName);
//        back.setOnClickListener(v -> activity.finish());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /*加载绿色标题栏（无返回按钮）*/
    public static void initTitleBar(Activity activity, String titleBarName) {
        TextView title = activity.findViewById(R.id.title_bar_name);
        title.setText(titleBarName);
    }

    /*加载白色标题栏（有返回按钮）*/
    public static void initWhiteTitleBar(Activity activity, String whiteTitleBarName) {
        ImageView back = activity.findViewById(R.id.white_title_back);
        TextView title = activity.findViewById(R.id.whit_title_name);
        TextView ensure = activity.findViewById(R.id.tv_modify_ensure);

        title.setText(whiteTitleBarName);

        back.setOnClickListener(v -> activity.finish());

    }

    /*加载白色标题栏（有返回按钮）*/
    public static void initWhiteEnsureTitleBar(Activity activity, String whiteTitleBarName) {
        ImageView back = activity.findViewById(R.id.white_title_back);
        TextView title = activity.findViewById(R.id.whit_title_name);
        TextView ensure = activity.findViewById(R.id.tv_modify_ensure);

        title.setText(whiteTitleBarName);

        back.setOnClickListener(v -> activity.finish());

    }


}
