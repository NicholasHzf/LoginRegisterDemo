package com.hzf.nicholas.loginregisterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hzf.nicholas.loginregisterdemo.mdoel.User;
import com.hzf.nicholas.loginregisterdemo.mdoel.UserLab;

public class LoginFragment extends Fragment {

    private EditText mAccountET;
    private EditText mPasswordET;
    private Button mButton;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化组件
        initElement(view);

        //登陆
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountET.getText().toString();
                String password = mPasswordET.getText().toString();
                User user = UserLab.get(getActivity(),account,password).getUser();
                if (user == null){
                    Toast.makeText(getActivity(),getString(R.string.s_activity_main_login_failure),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getString(R.string.s_activity_main_login_success),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),DetailActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }

    private void initElement(View view){
        mAccountET = (EditText) view.findViewById(R.id.frag_login_account_et);
        mPasswordET = (EditText) view.findViewById(R.id.frag_login_password_et);
        mButton = (Button) view.findViewById(R.id.frag_login_btn);
    }
}
