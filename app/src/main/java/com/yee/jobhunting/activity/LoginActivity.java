package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yee.jobhunting.MainActivity;
import com.yee.jobhunting.R;
import com.yee.jobhunting.boss.BossMainActivity;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup rgIdentity;
    Button btnLogin;
    RadioButton radioBtnStaff,radioBtnBoss;
    TextView tvLoginByCode,tvLoginToRegister,tvTitle;
    EditText etLoginPhoneNumber,etLoginPassword;
    ImageView ivBack;
    int userType;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ViewUtils.initTitle(this,"登录");

        //设置状态栏
        StatusBarUtils.initStatusBar(this);


        initView();


        //取消默认选项
        rgIdentity.clearCheck();

        rgIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (LoginActivity.this.radioBtnStaff.getId() == checkedId) {
                    userType = 1;
                }
                if (LoginActivity.this.radioBtnBoss.getId() == checkedId) {
                    userType = 2;
                }

            }
        });

    }



    private void initView() {
        ivBack = findViewById(R.id.title_back);
        tvTitle = findViewById(R.id.title_name);
        btnLogin = findViewById(R.id.btn_login);
        rgIdentity = findViewById(R.id.rg_identity);
        radioBtnStaff = findViewById(R.id.radio_staff);
        radioBtnBoss = findViewById(R.id.radio_boss);
        tvLoginByCode = findViewById(R.id.login_by_code);
        tvLoginToRegister = findViewById(R.id.login_to_register);
        etLoginPhoneNumber = findViewById(R.id.login_phone_number);
        etLoginPassword = findViewById(R.id.login_password);




        btnLogin.setOnClickListener(this);
        tvLoginByCode.setOnClickListener(this);
        tvLoginToRegister.setOnClickListener(this);
        ivBack.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.title_back:
                finish();
                break;
            case R.id.btn_login:
                String username = etLoginPhoneNumber.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(username);
                bmobUser.setPassword(password);
                bmobUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            if (userType == 1) {
                                Intent intentStaff = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentStaff);
                            }else if(userType == 2) {
                                Intent intentBoss = new Intent(LoginActivity.this, BossMainActivity.class);
                                startActivity(intentBoss);
                            }
                        }else {
                            Toast.makeText(LoginActivity.this,"手机号或密码错误！",Toast.LENGTH_LONG).show();

                        }

                    }
                });

                break;
            case R.id.login_by_code:
                Intent intent1 = new Intent(this, ConfirmLoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_to_register:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }



}