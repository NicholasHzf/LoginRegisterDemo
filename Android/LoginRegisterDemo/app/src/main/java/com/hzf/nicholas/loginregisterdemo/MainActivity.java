package com.hzf.nicholas.loginregisterdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //组件
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    //TabLayout所需碎片和标题
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        //实例化组件
        initElements();
        //初始化碎片集合
        initTabLayoutDatas();
    }

    private void initElements(){
        mTabLayout = (TabLayout)findViewById(R.id.activity_main_tab_layout);
        mViewPager = (ViewPager)findViewById(R.id.activity_main_view_pager);
    }

    private void initTabLayoutDatas(){
        mTitleList.add(getString(R.string.s_activity_main_login));
        mTitleList.add(getString(R.string.s_activity_main_register));

        mFragmentList.add(LoginFragment.newInstance());
        mFragmentList.add(RegisterFragment.newInstance());

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(fragmentManager,mFragmentList,mTitleList);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
