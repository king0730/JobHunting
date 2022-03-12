package com.yee.jobhunting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yee.jobhunting.bean.AddJob;
import com.yee.jobhunting.R;
import com.yee.jobhunting.adapter.JobAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class JobFragment extends Fragment {
//    private List<Job> jobList = new ArrayList<>();
    private List<AddJob> addJobList = new ArrayList<>();
    private RecyclerView jobRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job, container, false);
        jobRecyclerView = v.findViewById(R.id.job_recyclerview);


        return v;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                    Toast.makeText(getActivity(),"刷新"+addJobList.size()+"条职位",Toast.LENGTH_SHORT).show();
                    jobRecyclerView.setAdapter(new JobAdapter(getActivity(),addJobList));
                }
            }
        });

    }

}
