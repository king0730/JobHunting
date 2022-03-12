package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yee.jobhunting.R;
import com.yee.jobhunting.bean.AddJob;
import com.yee.jobhunting.utils.StatusBarUtils;
import com.yee.jobhunting.utils.ViewUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddJobActivity extends AppCompatActivity {
    private EditText etJobTitle;
    private EditText etJobSalary;
    private EditText etJobEducation;
    private EditText etJobExperience;
    private EditText etJobCompanyTitle;
    private EditText etCompanyLocation;
    private EditText etJobDescription;
    private Button btnPostJob;
    private String mObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        ViewUtils.initTitle(this,"发布职位");

        //设置状态栏
        StatusBarUtils.initStatusBar(this);

        initView();

    }

    private void initView() {
        etJobTitle = findViewById(R.id.et_add_job_title);
        etJobSalary = findViewById(R.id.et_add_job_salary);
        etJobEducation = findViewById(R.id.et_add_job_education);
        etJobExperience = findViewById(R.id.et_add_job_experience);
        etJobCompanyTitle = findViewById(R.id.et_add_job_company_title);
        etCompanyLocation = findViewById(R.id.et_add_job_location);
        etJobDescription = findViewById(R.id.et_add_job_description);
        btnPostJob = findViewById(R.id.btn_post_job);

        //发布职位按钮的点击事件
        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的数据
                String jobTitle = etJobTitle.getText().toString().trim();
                String jobSalary = etJobSalary.getText().toString().trim();
                String jobEducation = etJobEducation.getText().toString().trim();
                String jobExperience = etJobExperience.getText().toString().trim();
                String companyTitle = etJobCompanyTitle.getText().toString().trim();
                String companyLocation = etCompanyLocation.getText().toString().trim();
                String jobDescription = etJobDescription.getText().toString().trim();

                //添加数据
                AddJob addJob = new AddJob();
                addJob.setJobTitle(jobTitle);
                addJob.setJobSalary(jobSalary);
                addJob.setJobEducation(jobEducation);
                addJob.setJobExperience(jobExperience);
                addJob.setCompanyTitle(companyTitle);
                addJob.setCompanyLocation(companyLocation);
                addJob.setJobDescription(jobDescription);

                addJob.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            mObjectId = s;
                            Toast.makeText(AddJobActivity.this,"发布职位成功！" + mObjectId,Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddJobActivity.this,"发布职位失败！",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                    finish();
            }
        });

    }

}