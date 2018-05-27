package com.example.mid_term_exam.myclass;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/5/26.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            activity.finish();
        }
        activities.clear();
    }

}
