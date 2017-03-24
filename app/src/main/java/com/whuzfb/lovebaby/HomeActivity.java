package com.whuzfb.lovebaby;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends Activity implements View.OnClickListener,AbsListView.OnScrollListener{
    private View view=null;
    private ListView listView=null;
    private RelativeLayout relativeLayout=null;
    private ImageView img_setting=null;
    private ImageView img_email=null;
    private ImageView img_baby=null;
    private Button btn_more=null;

    public ArrayList<ListItem> mList;
    public MainListViewAdapter adapter;
    //最后的可视项索引
    private int viewLastIndex = 0;
    private static int TOTAL_LENGTH=70;
    // 当前窗口可见项总数
    private int viewItemCount;
    public boolean flag_end=false;
    public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_home);
        listView=(ListView)findViewById(R.id.listview_manual) ;
        view = getLayoutInflater().inflate(R.layout.relative_home_listheader, null);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.linear_home);

        //设置其他图标的点击响应
        img_setting=(ImageView)view.findViewById(R.id.img_setting);
        img_setting.setOnClickListener(this);
        img_email=(ImageView)view.findViewById(R.id.img_email);
        img_email.setOnClickListener(this);
        img_baby=(ImageView)view.findViewById(R.id.img_baby);
        img_baby.setOnClickListener(this);
        btn_more=(Button)view.findViewById(R.id.btn_more);
        btn_more.setOnClickListener(this);

        listView.addHeaderView(relativeLayout,null,true);

        mList = new ArrayList<HomeActivity.ListItem>();

        //初始化创建Item
        initListView();

        // 获取MainListAdapter对象
        adapter = new MainListViewAdapter();
        // 将MainListAdapter对象传递给ListView视图
        listView.setAdapter(adapter);
        //listView_packagename.setDividerHeight(1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this,"这是"+position,Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
        listView.setOnScrollListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_setting:
                Toast.makeText(HomeActivity.this,"你点击了设置图标",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_email:
                Toast.makeText(HomeActivity.this,"你点击了邮箱图标",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_baby:
                Toast.makeText(HomeActivity.this,"你点击了baby头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_more:
                Toast.makeText(HomeActivity.this,"你想要查看更多内容吗？",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
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
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && viewLastIndex == lastIndex) {
            //自动加载,可以在这里放置异步加载数据
            loadData();
            //数据集变化后,通知adapter
            adapter.notifyDataSetChanged();
            //设置选中项，如果不加这个flag则会导致最后一个时footview显示不全
            if(!flag_end &&(flag!=1)){
                listView.setSelection(viewLastIndex - viewItemCount + 1);
            }
        }
    }


    //加载数据
    private void loadData() {
        flag++;
        int count = adapter.getCount();
        ListItem item;
        if(adapter.getCount()==TOTAL_LENGTH)
            flag_end=true;
        for (int i = count; (i < count + 10)&&(i <TOTAL_LENGTH); i++) {
            item = new ListItem();
            item.setImage(getResources().getDrawable(R.drawable.icon_setting));
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setTitle("这是标题()");
            item.setManual("这是内容()");
            Log.d("TAG",i+"走到这里了");
            mList.add(item);
        }

    }

    public void initListView(){
        // 初始化data，装载八组数据到数组链表mList中
        ListItem item;
        int i = 0;
        for (; i < 4; i++) {
            item = new ListItem();
            item.setImage(getResources().getDrawable(R.drawable.img_home_1));
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setTitle("萌宝修炼手册");
            item.setManual("宝宝体重变轻了是怎么回事？");
            mList.add(item);
        }
    }





    //定义ListView适配器MainListViewAdapter
    class MainListViewAdapter extends BaseAdapter {

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
            ListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(HomeActivity.this).inflate(
                        R.layout.linear_home_listitem, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ListItemView();
                listItemView.imageView = (ImageView) convertView
                        .findViewById(R.id.img_manual);
                listItemView.tv_title = (TextView)convertView
                        .findViewById(R.id.tv_title);
                listItemView.tv_manual = (TextView)convertView
                        .findViewById(R.id.tv_manual);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            } else {
                // 从converView中获取ListItemView对象
                listItemView = (ListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            Drawable img = mList.get(position).getImage();
            String title = mList.get(position).getTitle();
            String manual = mList.get(position).getManual();
            int color=mList.get(position).getTextcolor();

            // 将资源传递给ListItemView的两个域对象
            listItemView.imageView.setImageDrawable(img);
            listItemView.tv_manual.setTextColor(color);
            listItemView.tv_manual.setText(manual);

            listItemView.tv_title.setTextColor(color);
            listItemView.tv_title.setText(title);
            // 返回convertView对象
            return convertView;
        }
    }

    //封装两个视图组件的类
    class ListItemView {
        ImageView imageView;
        TextView tv_title;
        TextView tv_manual;
    }

    //封装了两个资源的类
    //为了区别系统应用而加一个颜色
    class ListItem {
        private Drawable image;
        private String title;
        private String manual;
        private int textcolor= Color.parseColor("#2F4F4F");

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setManual(String manual) {
            this.manual = manual;
        }

        public String getManual() {
            return manual;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTextcolor(){
            return textcolor;
        }

        public void setTextcolor(int color){
            this.textcolor=color;
        }
    }
}
