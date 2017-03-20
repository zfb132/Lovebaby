package com.whuzfb.lovebaby;

/**
 * Created by zfb15 on 2017/3/18.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
public class AdapterScroolList extends ListView {
    public AdapterScroolList(Context context) {
        super(context);
    }
    public AdapterScroolList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AdapterScroolList(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

