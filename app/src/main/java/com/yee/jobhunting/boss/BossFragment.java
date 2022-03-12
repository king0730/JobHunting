package com.yee.jobhunting.boss;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yee.jobhunting.R;
import com.yee.jobhunting.activity.AddJobActivity;
import com.yee.jobhunting.activity.SearchActivity;
import com.yee.jobhunting.adapter.JobAdapter;
import com.yee.jobhunting.bean.AddJob;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BossFragment extends Fragment {

    private EditText etSearch;
    private ImageView ivBossAddJob;
    private List<AddJob> addJobList = new ArrayList<>();
    private RecyclerView jobRecyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_boss, container, false);

        etSearch = v.findViewById(R.id.et_search);
        ivBossAddJob = v.findViewById(R.id.iv_boss_add_job);
        jobRecyclerView = v.findViewById(R.id.job_recyclerview);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //设置发布职位点击事件
        ivBossAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddJobActivity.class);
                startActivity(intent);
            }
        });



        //设置Home首页搜索框的点击事件
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        jobRecyclerView.setLayoutManager(layoutManager);
        //JobAdapter adapter = new JobAdapter(addJobList);
        //jobRecyclerView.setAdapter(adapter);

        //获取数据列表
        BmobQuery<AddJob> query = new BmobQuery<AddJob>();
        query.addWhereNotEqualTo("jobTitle","产品经理");
        query.order("-createdAt");
        query.setLimit(20);
        query.findObjects(new FindListener<AddJob>() {
            @Override
            public void done(List<AddJob> addJobList, BmobException e) {
                if (e == null) {

                    //更新UI显示
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"刷新"+addJobList.size()+"条职位",Toast.LENGTH_SHORT).show();

                            jobRecyclerView.setAdapter(new JobAdapter(getActivity(),addJobList));

                        }
                    });

                }
            }
        });






    }

}
