package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yee.jobhunting.R;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegisterPhoneNumber,tvRegisterPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ViewUtils.initTitle(this,"注册");

        //设置状态栏
        StatusBarUtils.initStatusBar(this);


        tvRegisterPhoneNumber = findViewById(R.id.register_phone_number);
        tvRegisterPassword = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.btn_register);

        //注册按钮点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tvRegisterPhoneNumber.getText().toString().trim();
                String password = tvRegisterPassword.getText().toString().trim();
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(username);
                bmobUser.setPassword(password);
                bmobUser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this,"注册失败！" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
            }
        });

    }

}