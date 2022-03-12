package com.yee.jobhunting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yee.jobhunting.MainActivity;
import com.yee.jobhunting.R;
import com.yee.jobhunting.utils.StatusBarUtils;

import cn.bmob.v3.BmobUser;

public class JobDetailsActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvJobDetailsTitle;
    private TextView tvJobDetailsSalary;
    private TextView tvJobDetailsEducation;
    private TextView tvJobDetailsExperience;
    private TextView tvJobDetailsCompanyTitle;
    private TextView tvJobDetailsCompanyLocation;
    private TextView tvJobDetailsDescription;
    private Button btnCommunicate;
    private Button btnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        //设置状态栏
        StatusBarUtils.initWhiteStatusBar(this);


        ivBack = findViewById(R.id.iv_back);
        tvJobDetailsTitle = findViewById(R.id.job_details_job_title);
        tvJobDetailsSalary = findViewById(R.id.job_details_job_salary);
        tvJobDetailsEducation = findViewById(R.id.job_details_job_education);
        tvJobDetailsExperience = findViewById(R.id.job_details_job_experience);
        tvJobDetailsCompanyTitle = findViewById(R.id.job_details_company_title);
        tvJobDetailsCompanyLocation = findViewById(R.id.job_details_company_location);
        tvJobDetailsDescription = findViewById(R.id.job_details_job_description);
        btnCommunicate = findViewById(R.id.btn_communicate);
        btnResume = findViewById(R.id.btn_resume);

        //接受intent传值
        Intent intent = getIntent();
        String jobDetailsTitle = intent.getStringExtra("jobTitle");
        String jobDetailsSalary = intent.getStringExtra("jobSalary");
        String jobDetailsEducation = intent.getStringExtra("jobEducation");
        String jobDetailsExperience = intent.getStringExtra("jobExperience");
        String jobDetailsCompanyTitle = intent.getStringExtra("companyTitle");
        String jobDetailsCompanyLocation = intent.getStringExtra("companyLocation");
        String jobDetailsDescription = intent.getStringExtra("jobDescription");


        tvJobDetailsTitle.setText(jobDetailsTitle);
        tvJobDetailsSalary.setText(jobDetailsSalary);
        tvJobDetailsEducation.setText(jobDetailsEducation);
        tvJobDetailsExperience.setText(jobDetailsExperience);
        tvJobDetailsCompanyTitle.setText(jobDetailsCompanyTitle);
        tvJobDetailsCompanyLocation.setText(jobDetailsCompanyLocation);
        tvJobDetailsDescription.setText(jobDetailsDescription);


        //监听投递简历按钮的点击事件
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //投递简历


                Toast.makeText(JobDetailsActivity.this, "投递成功!", Toast.LENGTH_SHORT).show();
            }
        });



        //监听沟通按钮的点击事件
        btnCommunicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断当前是否有用户登录
                if (BmobUser.isLogin()) {
                    Intent intent = new Intent(JobDetailsActivity.this, MainActivity.class);
                    intent.putExtra("id",2);
                    startActivity(intent);
                }else {
                    Toast.makeText(JobDetailsActivity.this, "尚未登录！请先登录！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JobDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });




        //监听返回按钮的点击事件
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}