package com.whuzfb.lovebaby;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zfb15 on 2017/4/20.
 */

public class MyAccessibilityService extends AccessibilityService {
    List<AccessibilityNodeInfo> mList;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
        if (rootNodeInfo != null) {
            int eventType = event.getEventType();
            //响应窗口内容变化，窗口状态变化，控件滚动三种事件。
            if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
                    || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                    || eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED) {
                //通过一个Map集合来过滤重复点击事件
                Toast.makeText(getApplicationContext(),"监视屏幕",Toast.LENGTH_SHORT).show();
                }
            }
            try{
                mList=rootNodeInfo.findAccessibilityNodeInfosByText("单选按钮1");
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString()+"未找到指定文字",Toast.LENGTH_SHORT).show();
            }

        if(mList!=null){
            try{
                mList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Toast.makeText(getApplicationContext(),"点击按钮",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            }

        }

        }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {

        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 100;
        setServiceInfo(info);
        info.packageNames = new String[]{"com.whuzfb.myapplication","com.tencent.tim"};
        setServiceInfo(info);
        //super.onServiceConnected();

        super.onServiceConnected();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(getApplicationContext(),"关闭辅助功能",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
