package com.example.mid_term_exam.viewpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mid_term_exam.login_activity;
import com.example.mid_term_exam.R;

/**
 * Created by lenovo on 2018/5/26.
 */

public class ScheduleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //判断是否登陆
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        Boolean isornot = sharedPreferences.getBoolean("isornot",false);

        if(!isornot){
            //未登录
            View view  = inflater.inflate(R.layout.no_schedule_fragment,container,false);
            TextView textView = view.findViewById(R.id.no_text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),login_activity.class);
                    startActivity(intent);
                }
            });
            return view;
        }else{
            //已登录
            View view  = inflater.inflate(R.layout.schedule_fragment,container,false);
            return view;
        }
    }
}
