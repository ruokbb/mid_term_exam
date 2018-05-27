package com.example.mid_term_exam.QAViewPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lenovo on 2018/5/26.
 */

public class MyQAFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"全部", "学习","生活","情感","其他"};//页面导航选项卡名字

    public MyQAFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        //此处根据不同的position返回不同的Fragment
        if (position == 0){
            return new AllFragment();
        }else if(position==1){
            return  new StuFragment();
        }else if(position==2){
            return  new LifeFragment();
        }else if(position==3){
            return  new EmotionFragment();
        }else{
            return new OtherFragment();
        }

    }

    @Override
    public int getCount() {
        //此处返回Tab的数目
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //此处返回每个Tab的title
        return mTitles[position];
    }

}
