package com.hzf.nicholas.loginregisterdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hzf.nicholas.loginregisterdemo.mdoel.User;
import com.hzf.nicholas.loginregisterdemo.mdoel.UserLab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class RegisterFragment extends Fragment {

    private EditText nicknameET;
    private EditText phoneNumET;
    private EditText passwordET;
    private EditText confirmET;
    private CheckBox mCheckBox;
    private TextView mTextView;
    private Button mButton;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化组件
        initElement(view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submit
                finishSubmit();
            }
        });
    }

    private void initElement(View view){
        nicknameET = (EditText)view.findViewById(R.id.frag_register_nickname_et);
        phoneNumET = (EditText)view.findViewById(R.id.frag_register_phone_num_et);
        passwordET = (EditText)view.findViewById(R.id.frag_register_password_et);
        confirmET = (EditText)view.findViewById(R.id.frag_register_password_check_et);
        mCheckBox = (CheckBox)view.findViewById(R.id.frag_register_protocol_cb);
        mTextView = (TextView)view.findViewById(R.id.frag_register_tip_tv);
        mButton = (Button)view.findViewById(R.id.frag_register_submit);
    }

    private void finishSubmit(){
        String nickname = nicknameET.getText().toString();
        String phoneNum = phoneNumET.getText().toString();
        String password = passwordET.getText().toString();
        String confirm = confirmET.getText().toString();
        boolean isAgree = mCheckBox.isChecked();
        if (nickname.equals("")){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_nickname));
            return;
        }else if (phoneNum.equals("")){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_phone_num));
            return;
        }else if (password.equals("")){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_password));
            return;
        }else if (confirm.equals("")){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_confirm_password));
            return;
        }if (!isAgree){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_protocol));
            return;
        }else if (!confirm.equals(password)){
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(getString(R.string.s_activity_main_register_input_password_wrong));
            return;
        }else {
            String url = getString(R.string.s_url_register);
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("NICKNAME", nickname)
                    .add("PASSWORD",password)
                    .add("PHONENUM",phoneNum)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String s = response.body().string();
                    JSONArray jsonArray = null;
                    try {
                        String state = "";
                        jsonArray = new JSONArray(s);
                        for (int i = 0 ; i < jsonArray.length() ; i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            state = jsonObject.getString("STATE");
                        }
                        if (state.equals("success")){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),getString(R.string.s_activity_main_register_success),Toast.LENGTH_SHORT).show();
                                }
                            });
                            User user = new User();
                            user.setNickname(nicknameET.getText().toString());
                            user.setPassword(passwordET.getText().toString());
                            user.setPhoneNum(phoneNumET.getText().toString());
                            UserLab.get(getActivity(),phoneNumET.getText().toString(),passwordET.getText().toString()).setUser(user);
                            Intent intent = new Intent(getActivity(),DetailActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),getString(R.string.s_activity_main_register_failure),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onResponse: " + s);
                }
            });
        }
    }

}
