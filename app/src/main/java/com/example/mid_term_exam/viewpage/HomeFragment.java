package com.example.mid_term_exam.viewpage;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import  com.example.mid_term_exam.login_activity;
import com.example.mid_term_exam.help_activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mid_term_exam.MainLayoutActivity;
import com.example.mid_term_exam.QAViewPage.MyQAFragmentPagerAdapter;
import com.example.mid_term_exam.R;

/**
 * Created by lenovo on 2018/5/26.
 */

public class HomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyQAFragmentPagerAdapter mFragmentPagerAdapter;

    private TabLayout.Tab mTab1;
    private TabLayout.Tab mTab2;
    private TabLayout.Tab mTab3;
    private TabLayout.Tab mTab4;
    private TabLayout.Tab mTab5;
    int select =0;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.home_fragment,container,false);


        mViewPager = (ViewPager) view.findViewById(R.id.qaviewpage);
        mFragmentPagerAdapter = new MyQAFragmentPagerAdapter(getActivity().getSupportFragmentManager());
       //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentPagerAdapter);
       //tablayout与Viewpager绑定
        mTabLayout = (TabLayout) view.findViewById(R.id.qatablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        mTab1 = mTabLayout.getTabAt(0);
        mTab2 = mTabLayout.getTabAt(1);
        mTab3 = mTabLayout.getTabAt(2);
        mTab4 = mTabLayout.getTabAt(3);
        mTab5 = mTabLayout.getTabAt(4);



        //帮助页面
        FloatingActionButton floatingActionButton = view.findViewById(R.id.help);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //设置底部dialog
                final LayoutInflater factory = LayoutInflater.from(getActivity());
                View myView = factory.inflate(R.layout.kindeselect,null);
                final Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setView(myView).create();
                dialog.show();
                //设置点击Dialog外部任意区域关闭Dialog
                dialog.setCanceledOnTouchOutside(true);


                dialog.show();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);



                TextView yes = myView.findViewById(R.id.yes);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //下一步的逻辑
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                        if(!sharedPreferences.getBoolean("isornot",false)){
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(),login_activity.class);
                            startActivity(intent);
                        }else if(select==0){
                            Toast.makeText(getActivity(),"请选择一个帮助的类型",Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(getActivity(),help_activity.class);
                            intent.putExtra("kind",select);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }
                });

                //类型选择
                final ImageView stu = myView.findViewById(R.id.stu);
                final ImageView life = myView.findViewById(R.id.life);
                final ImageView emotion = myView.findViewById(R.id.emotion);
                final ImageView other = myView.findViewById(R.id.other);
                stu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(select!=1){
                            select=1;
                            stu.setImageResource(R.mipmap.stupress);
                            life.setImageResource(R.mipmap.life);
                            emotion.setImageResource(R.mipmap.emotion);
                            other.setImageResource(R.mipmap.other);
                        }else{
                            select=0;
                            stu.setImageResource(R.mipmap.stu);
                        }
                    }
                });
                life.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(select!=2){
                            select=2;
                            stu.setImageResource(R.mipmap.stu);
                            life.setImageResource(R.mipmap.lifepress);
                            emotion.setImageResource(R.mipmap.emotion);
                            other.setImageResource(R.mipmap.other);
                        }else{
                            select=0;
                            life.setImageResource(R.mipmap.life);
                        }
                    }
                });
                emotion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(select!=3){
                            select=3;
                            stu.setImageResource(R.mipmap.stu);
                            life.setImageResource(R.mipmap.life);
                            emotion.setImageResource(R.mipmap.emotionpress);
                            other.setImageResource(R.mipmap.other);
                        }else{
                            select=0;
                            emotion.setImageResource(R.mipmap.emotion);
                        }
                    }
                });
                other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(select!=4){
                            select=4;
                            stu.setImageResource(R.mipmap.stu);
                            life.setImageResource(R.mipmap.life);
                            emotion.setImageResource(R.mipmap.emotion);
                            other.setImageResource(R.mipmap.otherpress);
                        }else{
                            select=0;
                            other.setImageResource(R.mipmap.other);
                        }
                    }
                });

                //取消
                TextView exit1 = myView.findViewById(R.id.exit1);
                ImageView exit2 = myView.findViewById(R.id.exit2);
                TextView exit3 = myView.findViewById(R.id.exit3);
                exit1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                exit2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                exit3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }




}
