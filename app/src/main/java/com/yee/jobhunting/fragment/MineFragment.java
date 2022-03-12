package com.yee.jobhunting.fragment;


import android.content.Intent;

import android.os.Bundle;
;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.yee.jobhunting.R;
import com.yee.jobhunting.activity.MineInfoActivity;
import com.yee.jobhunting.activity.SettingsActivity;
import com.yee.jobhunting.bean.User;

import cn.bmob.v3.BmobUser;



public class MineFragment extends Fragment {

    private ImageView ivMineSettings;
    private ImageView ivMineIcon;
    private TextView tvMineName;

    private static final String TAG = "MineFragment";


    @Override
    public void onResume() {
        super.onResume();
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);

        //初始化View
        initView(v);

        return v;
    }

    private void initView(View v) {

        ivMineSettings = v.findViewById(R.id.iv_mine_settings);
        ivMineIcon = v.findViewById(R.id.mine_icon);
        tvMineName = v.findViewById(R.id.mine_name);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化监听事件
        initListener();

    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }


    /*
     * 监听事件
     * */
    private void initListener() {

        //监听设置控件的点击事件
        ivMineSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);

            }
        });


        //监听头像控件的点击事件
        ivMineIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MineInfoActivity.class);
                startActivity(intent);

            }
        });


        //获取当前用户昵称
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            String nickName = user.getNickName();
            tvMineName.setText(nickName);
        }else {
            Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
        }

    }

}
