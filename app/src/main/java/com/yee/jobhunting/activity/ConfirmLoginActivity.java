package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yee.jobhunting.R;
import com.yee.jobhunting.activity.LoginActivity;
import com.yee.jobhunting.activity.RegisterActivity;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

public class ConfirmLoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivBack;
    TextView etLoginByPassword,etLoginToRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_login);


        ViewUtils.initTitle(this,"验证码登录");

        //设置状态栏
        StatusBarUtils.initStatusBar(this);

        initView();



    }

    private void initView() {
        ivBack = findViewById(R.id.title_back);
        etLoginByPassword = findViewById(R.id.login_by_password);
        etLoginToRegister = findViewById(R.id.login_to_register);

        ivBack.setOnClickListener(this);
        etLoginByPassword.setOnClickListener(this);
        etLoginToRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.login_by_password:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_to_register:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;

        }

    }


}