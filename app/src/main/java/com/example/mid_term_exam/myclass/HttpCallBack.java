package com.example.mid_term_exam.myclass;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2018/5/26.
 */

public interface HttpCallBack {
    void onFinish(String response);
    void onFinish(Bitmap bitmap);
    void onError(Exception e);
}
