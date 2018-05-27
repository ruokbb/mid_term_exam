package com.example.mid_term_exam;

import android.annotation.TargetApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mid_term_exam.myclass.BaseActivity;
import com.example.mid_term_exam.viewpage.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainLayoutActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private NoSrcollViewPage mViewPager;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;
    List<String> titles  =new ArrayList<>();
    List<Integer> res = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
        mViewPager =  findViewById(R.id.my_viewpage);
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentPagerAdapter);
        //tablayout与Viewpager绑定
        mTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
        //设置图标
        mTabLayout.getTabAt(0).setText("课表");
        mTabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(this,R.mipmap.schedule1));
        mTabLayout.getTabAt(1).setText("邮问");
        mTabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(this,R.mipmap.home));
        mTabLayout.getTabAt(2).setText("发现");
        mTabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(this,R.mipmap.found));
        mTabLayout.getTabAt(3).setText("我的");
        mTabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(this,R.mipmap.my));
        //选中是切换
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                tab.setIcon(res.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(res.get(tab.getPosition()+4));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void init(){
        titles.add("课表");
        titles.add("邮问");
        titles.add("发现");
        titles.add("我的");
        res.add(R.mipmap.schedule1);
        res.add(R.mipmap.home1);
        res.add(R.mipmap.found1);
        res.add(R.mipmap.my1);
        res.add(R.mipmap.schedule);
        res.add(R.mipmap.home);
        res.add(R.mipmap.found);
        res.add(R.mipmap.my);
    }

}
