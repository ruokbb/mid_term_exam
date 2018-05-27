package com.example.mid_term_exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mid_term_exam.myclass.BaseActivity;

public class load_activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oad_layout);
        begin();
    }

    public void begin(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//休眠1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //计时结束后跳转到其他界面
                //默认登陆也在这

                startActivity(new Intent(load_activity.this,MainLayoutActivity.class));

                //添加finish方法在任务栈中销毁倒计时界面，使新开界面在回退时直接退出而不是再次返回该界面
                finish();
            }
        }).start();
    }
}
