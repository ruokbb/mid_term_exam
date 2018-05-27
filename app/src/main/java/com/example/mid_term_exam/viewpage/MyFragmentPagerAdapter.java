package com.example.mid_term_exam.viewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lenovo on 2018/5/24.
 * 由于我们要实现的是顶部导航栏，所有我们此处的布局应该为上面部分是TabLayout，下面部分是ViewPager
 *
 */

//在MainActivity中绑定布局
//private TabLayout mTabLayout;
//private ViewPager mViewPager;
//private MyFragmentPagerAdapter mFragmentPagerAdapter;
//
//private TabLayout.Tab mTab1;
//private TabLayout.Tab mTab2;
//
//        mViewPager = (ViewPager) findViewById(R.id.m_viewpager);
//        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
////给ViewPager设置适配器
//        mViewPager.setAdapter(mFragmentPagerAdapter);
////tablayout与Viewpager绑定
//        mTabLayout = (TabLayout) findViewById(R.id.m_tablayout);
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        mTab1 = mTabLayout.getTabAt(0);
//        mTab = mTabLayout.getTabAt(1);

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"课表", "社区","发现","我的"};//页面导航选项卡名字

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        //此处根据不同的position返回不同的Fragment
        if (position == 0){
            //fragment的类
            return new ScheduleFragment();
        }else if(position==1){
            return  new HomeFragment();
        }else if(position==2){
            return  new FoundFragment();
        }else{
            return new MyFragment();
        }
    }

    @Override
    public int getCount() {
        //此处返回Tab的数目
        return mTitles.length;
    }

}
