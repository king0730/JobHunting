package com.yee.jobhunting.fragment;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.yee.jobhunting.R;
import com.yee.jobhunting.activity.SearchActivity;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class HomeFragment extends Fragment {

    TabLayout jobTabLayout;
    ViewPager jobViewPager;

    private ArrayList<String> jobTabList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private EditText etSearch;
    private ImageView jobTabAdd;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        jobTabLayout = v.findViewById(R.id.job_tab_layout);
        jobViewPager = v.findViewById(R.id.job_view_pager);
        etSearch = v.findViewById(R.id.et_search);
        jobTabAdd = v.findViewById(R.id.job_tab_add);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentList = new ArrayList<>();
        fragmentList.add(new JobFragment());
        fragmentList.add(new JobFragment());
        fragmentList.add(new JobFragment());
        fragmentList.add(new JobFragment());


        jobTabList = new ArrayList<>();
        jobTabList.add(0,"Android");
        jobTabList.add(1,"Java");
        jobTabList.add(2,"新媒体运营");
        jobTabList.add(3,"广告设计");




        //设置添加Tab点击事件
        jobTabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobTabList.add(0,"Android");
                jobTabList.add(1,"Java");
                jobTabList.add(2,"新媒体运营");
                jobTabList.add(3,"广告设计");
                jobTabList.add(4,"产品经理");
                jobTabList.add(5,"销售");
                Toast.makeText(v.getContext(),"增加Tab",Toast.LENGTH_LONG).show();
            }
        });



        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        jobViewPager.setAdapter(adapter);
//        prepareViewPager(jobViewPager, jobTabList);
        //设置缓存数量
        int limit = jobTabList.size();
        jobViewPager.setOffscreenPageLimit(limit);
        jobTabLayout.setupWithViewPager(jobViewPager);

        //设置Home首页搜索框的点击事件
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

//    private void prepareViewPager(ViewPager jobViewPager, ArrayList<String> jobTypeList) {
//        MainAdapter adapter = new MainAdapter(getChildFragmentManager());
//        JobFragment fragment = new JobFragment();
//        for (int i=0;i<jobTypeList.size();i++) {
//            Bundle bundle =  new Bundle();
//            bundle.putString("title",jobTypeList.get(i));
//            fragment.setArguments(bundle);
//            adapter.addFragment(fragment,jobTypeList.get(i));
//            fragment = new JobFragment();
//
//        }
//        jobViewPager.setAdapter(adapter);
//
//    }

//    private class MainAdapter extends FragmentPagerAdapter {
//
//        ArrayList<String> jobTypeList = new ArrayList<>();
//        List<Fragment> fragmentList = new ArrayList<>();
//
//        public void addFragment(Fragment fragment,String title) {
//            jobTypeList.add(title);
//            fragmentList.add(fragment);
//        }
//
//        public MainAdapter(@NonNull FragmentManager fm) {
//            super(fm);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return jobTypeList.get(position);
//        }
//    }
//


    @Override
    public void onResume() {
        super.onResume();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .init();
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return jobTabList.get(position);    //返回标题的第几项
        }

    }


}
