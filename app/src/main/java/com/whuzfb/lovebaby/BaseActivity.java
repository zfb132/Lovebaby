package com.whuzfb.lovebaby;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * Created by zfb15 on 2017/3/27.
 */

/**
 * 具体功能待添加
 */
public class BaseActivity extends Activity {
    private BaseApplication mBaseApp = null;
    private WindowManager mWindowManager = null;
    private View mNightView = null;
    private WindowManager.LayoutParams mNightViewParam;
    private boolean mIsAddedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBaseApp = (BaseApplication) getApplication();
        if (mBaseApp.isNightMode())
            setTheme(R.style.AppTheme_Night);
        else
            setTheme(R.style.AppTheme_Day);
        super.onCreate(savedInstanceState);
        mIsAddedView = false;
        if (mBaseApp.isNightMode()) {
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }
    }
    public void changeViewMode() {
        boolean isNight = mBaseApp.isNightMode();
        if (isNight)
            ChangeToDay();
        else
            ChangeToNight();
        recreate();
    }
    @Override
    protected void onDestroy() {
        if (mIsAddedView) {
            mBaseApp = null;
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
        super.onDestroy();
    }
    public void ChangeToDay() {
        mBaseApp.setIsNightMode(false);
        mNightView.setBackgroundResource(android.R.color.transparent);
    }
    public void ChangeToNight() {
        mBaseApp.setIsNightMode(true);
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }
    /**
     * wait a time until the onresume finish
     */
    public void recreateOnResume() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                recreate();
            }
        }, 100);
    }
    private void initNightView() {
        if (mIsAddedView == true)
            return;
        mNightViewParam = new LayoutParams(
                LayoutParams.TYPE_APPLICATION,
                LayoutParams.FLAG_NOT_TOUCHABLE | LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, mNightViewParam);
        mIsAddedView = true;
    }
}
