package com.example.mid_term_exam.viewpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mid_term_exam.login_activity;
import com.example.mid_term_exam.R;

/**
 * Created by lenovo on 2018/5/26.
 */

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.my_fragment,container,false);

        //判断是否登陆
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        Boolean isornot = sharedPreferences.getBoolean("isornot",false);
        if(!isornot){
        //跳转登陆界面
            Intent intent = new Intent(getActivity(),login_activity.class);
            startActivity(intent);
        }

        Button exit = view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isornot",false);
                editor.apply();
            }
        });

        return view;
    }
}
