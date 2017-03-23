package com.whuzfb.lovebaby;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zfb15 on 2017/3/20.
 */

public class NoteActivity extends AppCompatActivity implements View.OnClickListener,AbsListView.OnScrollListener{
    private View view=null;
    private ListView listView=null;
    private RelativeLayout relativeLayout=null;
    private ImageView img_setting=null;

    public ArrayList<NoteListItem> mList;
    public NoteListViewAdapter adapter;
    //最后的可视项索引
    private int viewLastIndex = 0;
    private static int TOTAL_LENGTH=70;
    // 当前窗口可见项总数
    private int viewItemCount;
    public boolean flag_end=false;
    public ModeCallback mCallback;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_note);

        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listview_note) ;
        //view = getLayoutInflater().inflate(R.layout.relative_home_listheader, null);
        //relativeLayout=(RelativeLayout)view.findViewById(R.id.linear_home);
        //img_setting=(ImageView)view.findViewById(R.id.img_setting);
        //img_setting.setOnClickListener(this);

        //listView.addHeaderView(relativeLayout,null,true);

        mList = new ArrayList<NoteActivity.NoteListItem>();

        //初始化创建Item
        initListView();

        // 获取MainListAdapter对象
        adapter = new NoteListViewAdapter();
        // 将MainListAdapter对象传递给ListView视图
        listView.setAdapter(adapter);
        //listView_packagename.setDividerHeight(1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NoteActivity.this,"这是"+position,Toast.LENGTH_SHORT).show();
            }
        });

        /*
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //响应戴安
                mList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        */
        listView.setOnScrollListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mCallback = new ModeCallback();
        listView.setMultiChoiceModeListener(mCallback);
    }


    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()){
            case R.id.img_setting:
                Toast.makeText(NoteActivity.this,"这是tt",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_email:
                Toast.makeText(NoteActivity.this,"pppt",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        */
    }


    //滑动时被调用
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.viewItemCount = visibleItemCount;
        viewLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    //滑动状态改变时被调用
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;    //数据集最后一项的索引
        //去掉底部的loadMoreView项，因此不加1
        int lastIndex = itemsLastIndex ;
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && viewLastIndex == lastIndex) {
            //自动加载,可以在这里放置异步加载数据
            loadData();
            //数据集变化后,通知adapter
            adapter.notifyDataSetChanged();
            //设置选中项，如果不加这个flag则会导致最后一个时footview显示不全
            if(!flag_end ){
                //去掉底部的loadMoreView项，因此不加1
                listView.setSelection(viewLastIndex - viewItemCount );
            }
        }
    }


    //加载数据


    private void loadData() {
        int count = adapter.getCount();
        NoteListItem item;
        if(adapter.getCount()==TOTAL_LENGTH)
            flag_end=true;
        for (int i = count; (i < count + 10)&&(i <TOTAL_LENGTH); i++) {
            item = new NoteListItem();
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setContent("这是标题()"+i);
            mList.add(item);
        }
    }

    public void initListView(){
        // 初始化data，装载八组数据到数组链表mList中
        NoteListItem item;
        int i = 0;
        for (; i < 20; i++) {
            item = new NoteListItem();
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setContent("这是内容"+i);
            mList.add(item);
        }
    }





    //定义ListView适配器MainListViewAdapter
    class NoteListViewAdapter extends BaseAdapter {

        //返回item的个数
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        //返回item的内容
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mList.get(position);
        }

        //返回item的id
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        //返回item的视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NoteListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(NoteActivity.this).inflate(
                        R.layout.linear_note_listitem, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new NoteListItemView();
                listItemView.tv_content = (TextView)convertView
                        .findViewById(R.id.tv_note_item);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            } else {
                // 从converView中获取ListItemView对象
                listItemView = (NoteListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            String content = mList.get(position).getContent();
            int color=mList.get(position).getTextcolor();

            // 将资源传递给ListItemView的两个域对象
            listItemView.tv_content.setTextColor(color);
            listItemView.tv_content.setText(content);
            updateBackground(position,listItemView.tv_content);
            // 返回convertView对象
            return convertView;
        }
    }

    @SuppressLint("NewApi")
    public void updateBackground(int position, View view) {
        if (listView.isItemChecked(position)) {
            view.setBackgroundColor(Color.parseColor("#bb40ffc6"));
        } else {
            view.setBackgroundColor(Color.parseColor("#00ffffff"));
        }


    }


    //封装两个视图组件的类
    class NoteListItemView {
        TextView tv_content;
    }

    //封装了两个资源的类
    //为了区别系统应用而加一个颜色
    class NoteListItem {
        private String content;

        private int textcolor= Color.parseColor("#2F4F4F");

        public void setContent(String manual) {
            this.content = manual;
        }

        public String getContent() {
            return content;
        }

        public int getTextcolor(){
            return textcolor;
        }

        public void setTextcolor(int color){
            this.textcolor=color;
        }
    }




    /*
    //多选
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_choice){
            //这里使用了一点技巧来实现处于选中状态 但是0个item 被选择
            Log.d("TAG","wwwwwwwwwwwww");
            listView.setItemChecked(0,true);
            listView.clearChoices();
            mCallback.updateSeletedCount();
        }
        return super.onOptionsItemSelected(item);
    }
    */


    private class ModeCallback implements ListView.MultiChoiceModeListener {
        private View mMultiSelectActionBarView;
        private TextView mSelectedCount;
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // actionmode的菜单处理
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_multile, menu);
            if (mMultiSelectActionBarView == null) {
                mMultiSelectActionBarView = LayoutInflater.from(NoteActivity.this)
                        .inflate(R.layout.actionbar_list_select, null);
                mSelectedCount =
                        (TextView)mMultiSelectActionBarView.findViewById(R.id.tv_number);
            }
            mode.setCustomView(mMultiSelectActionBarView);
            ((TextView)mMultiSelectActionBarView.findViewById(R.id.title)).setText("已被选中");
            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            if (mMultiSelectActionBarView == null) {
                ViewGroup v = (ViewGroup)LayoutInflater.from(NoteActivity.this)
                        .inflate(R.layout.actionbar_list_select, null);
                mode.setCustomView(v);
                mSelectedCount = (TextView)v.findViewById(R.id.tv_number);
            }
            //更新菜单的状态
            MenuItem mItem = menu.findItem(R.id.action_select);
            if(listView.getCheckedItemCount() == adapter.getCount()){
                mItem.setTitle("全不选");
            }else{
                mItem.setTitle("全选");
            }
            return true;
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_select:
                    if(listView.getCheckedItemCount() == adapter.getCount()){
                        unSelectedAll();
                    }else{
                        selectedAll();
                    }
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.action_delete:
                    delete();
                    listView.clearChoices();
                    listView.setItemChecked(0,false);
                    mCallback.updateSeletedCount();
                    break;
                default:
                    break;
            }
            return true;
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            listView.clearChoices();
        }
        @Override
        public void onItemCheckedStateChanged(ActionMode mode,
                                              int position, long id, boolean checked) {
            updateSeletedCount();
            mode.invalidate();
            Log.d("TAG",position+"被选中");
            adapter.notifyDataSetChanged();
        }

        public void updateSeletedCount(){
            mSelectedCount.setText(Integer.toString(listView.getCheckedItemCount()));
        }
    }
    public void selectedAll(){
        for(int i= 0; i< adapter.getCount(); i++){
            listView.setItemChecked(i, true);
        }
        mCallback.updateSeletedCount();
    }

    public void unSelectedAll(){
        listView.clearChoices();
        listView.setItemChecked(0,false);
        mCallback.updateSeletedCount();
    }

    public void delete() {
        SparseBooleanArray m=listView.getCheckedItemPositions();
        if(m!=null){
            //Iterator<NoteListItem> sListIterator = mList.iterator();
            //
            for(int i=m.size()-1;i>=0;i--){
                if(m.valueAt(i)){
                    mList.remove(m.keyAt(i));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

}
