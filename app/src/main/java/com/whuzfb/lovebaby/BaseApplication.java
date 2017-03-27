package com.whuzfb.lovebaby;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by zfb15 on 2017/3/27.
 */

/**
 * 具体功能待添加
 */
public class BaseApplication extends Application {
    private ArrayList<Activity> activityList = new ArrayList<>();
    private boolean isNight=false;

    public boolean isNightMode() {
        return isNight;
    }

    public void setIsNightMode(boolean flag){
        isNight=flag;
    }

    /**
     * 添加到ArrayList<Activity>
     *
     * @param activity：Activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 退出所有的Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }


}
