package com.yee.jobhunting;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yee.jobhunting.custom.NoScrollViewPager;
import com.yee.jobhunting.fragment.HomeFragment;
import com.yee.jobhunting.fragment.MessageFragment;
import com.yee.jobhunting.fragment.MineFragment;
import com.yee.jobhunting.fragment.DiscoverFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private NoScrollViewPager contentViewPager;
    private BottomNavigationView bottomNav;
    private MenuItem menuItem;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contentViewPager = findViewById(R.id.content_view_pager);
        bottomNav = findViewById(R.id.bottom_nav);

        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());

        contentViewPager.setNoScroll(true);

        //设置fragment的缓冲数量
        int limit = fragmentList.size();
        contentViewPager.setOffscreenPageLimit(limit);
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        contentViewPager.setAdapter(adapter);



        //导航栏点击事件
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.myhome:
                        contentViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.discover:
                        contentViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.mymessage:
                        contentViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.mine:
                        contentViewPager.setCurrentItem(3,false);
                        break;
                }
                        return false;
            }
        });

        contentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNav.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private class MainAdapter extends FragmentPagerAdapter {

            public MainAdapter(@NonNull FragmentManager fm) {
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
        }


    /*
    * Activity跳转到Fragment
    * */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        int id = getIntent().getIntExtra("id",0);
//        bottomNav.setSelectedItemId(bottomNav.getMenu().getItem(id).getItemId());
//
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}
