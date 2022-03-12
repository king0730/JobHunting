package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yee.jobhunting.R;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import cn.bmob.v3.BmobUser;

public class SettingsActivity extends AppCompatActivity {

    Button btnExit;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //重置标题栏名称
        ViewUtils.initWhiteTitleBar(this,"设置");

        //初始化View
        initView();

        //初始化点击事件
        initListener();

        //设置状态栏
        StatusBarUtils.initWhiteStatusBar(this);



    }


    private void initView() {

        btnExit = findViewById(R.id.btn_exit);
        ivBack = findViewById(R.id.white_title_back);
    }

    private void initListener() {

        //监听退出按钮
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //退出登录
                BmobUser bmobUser = new BmobUser();
                bmobUser.logOut();

                //刷新UI显示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //退出成功，回到登录页面
                        Toast.makeText(SettingsActivity.this, "退出成功！", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });



    }

}