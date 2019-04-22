package com.hzf.nicholas.loginregisterdemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hzf.nicholas.loginregisterdemo.mdoel.User;
import com.hzf.nicholas.loginregisterdemo.mdoel.UserLab;

public class DetailActivity extends AppCompatActivity {

    private TextView mTextView;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        //实例化组件
        initElements();
        //显示数据
        showData();
    }

    private void initElements(){
        mTextView = (TextView) findViewById(R.id.activity_detail_tv);
    }

    private void showData(){
        mUser = UserLab.get(DetailActivity.this,"","").getUser();
        mTextView.setText("用户："+mUser.getNickname()+" 登陆成功！\n"+"手机号："+mUser.getPhoneNum()+" \n"+"密码："+mUser.getPassword());
    }
}
