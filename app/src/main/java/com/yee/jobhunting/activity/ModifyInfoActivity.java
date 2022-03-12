package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yee.jobhunting.R;
import com.yee.jobhunting.bean.User;
import com.yee.jobhunting.bean.UserInfo;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ModifyInfoActivity extends AppCompatActivity {

    EditText etModifyInfoName;
    EditText etModifyInfoGender;
    EditText etModifyInfoBirth;
    EditText etModifyInfoEmail;
    TextView tvModifyEnsure;

    private String mObjectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        //设置状态栏
        StatusBarUtils.initWhiteStatusBar(this);

        initView();

        initListener();

        ViewUtils.initWhiteEnsureTitleBar(this,"填写个人信息");


    }

    private void initView() {

        tvModifyEnsure = findViewById(R.id.tv_modify_ensure);
        etModifyInfoName = findViewById(R.id.et_mine_modify_info_name);
        etModifyInfoGender = findViewById(R.id.et_mine_modify_info_gender);
        etModifyInfoBirth = findViewById(R.id.et_mine_modify_info_birth);
        etModifyInfoEmail = findViewById(R.id.et_mine_modify_info_email);
    }



    private void initListener() {
        tvModifyEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //更新User数据
                User user = BmobUser.getCurrentUser(User.class);

                //获取用户数据
                String nickName = etModifyInfoName.getText().toString().trim();
                String userGender = etModifyInfoGender.getText().toString().trim();
                String userBirth = etModifyInfoBirth.getText().toString().trim();
                String userEmail = etModifyInfoEmail.getText().toString().trim();

                user.setNickName(nickName);
                user.setUserGender(userGender);
                user.setUserBirth(userBirth);
                user.setEmail(userEmail);

                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(ModifyInfoActivity.this,"更新成功！",Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(ModifyInfoActivity.this,"更新失败！" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

                finish();
            }
        });

    }


}