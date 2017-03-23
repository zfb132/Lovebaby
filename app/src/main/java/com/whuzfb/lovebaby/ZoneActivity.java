package com.whuzfb.lovebaby;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zfb15 on 2017/3/19.
 */

public class ZoneActivity extends Activity implements View.OnClickListener,AbsListView.OnScrollListener{
    private ListView listView=null;
    private View view=null;
    private LinearLayout linearLayout=null;
    private Integer[] mThumbIds={
            R.drawable.img_zone_1,R.drawable.img_zone_2,R.drawable.img_zone_3,R.drawable.img_zone_4,
    };

    public ArrayList<ZoneListItem> mList;
    public ZoneListViewAdapter adapter;
    //最后的可视项索引
    private int viewLastIndex = 0;
    private static int TOTAL_LENGTH=70;
    // 当前窗口可见项总数
    private int viewItemCount;
    public boolean flag_end=false;
    public int flag=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zone);

        listView=(ListView)findViewById(R.id.listview_zone) ;
        view = getLayoutInflater().inflate(R.layout.linear_zone_listheader, null);
        linearLayout=(LinearLayout)view.findViewById(R.id.linear_zone);


        listView.addHeaderView(linearLayout,null,true);


        mList = new ArrayList<ZoneListItem>();

        //初始化创建Item
        initListView();

        // 获取MainListAdapter对象
        adapter = new ZoneListViewAdapter();
        // 将MainListAdapter对象传递给ListView视图
        listView.setAdapter(adapter);
        //listView_packagename.setDividerHeight(1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ZoneActivity.this,"这是"+position,Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnScrollListener(this);



        //这是gridview
        MyGridView gridView=(MyGridView)view.findViewById(R.id.gridview);
        gridView.setAdapter(new myImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ZoneActivity.this,"这是第"+(position+1)+"幅图像", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(ZoneActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()){
            case R.id.img_setting:
                Toast.makeText(ZoneActivity.this,"这是tt",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_email:
                Toast.makeText(ZoneActivity.this,"pppt",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        */
        Toast.makeText(ZoneActivity.this,"pppt",Toast.LENGTH_SHORT).show();
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
        ZoneListItem item;
        if(adapter.getCount()==TOTAL_LENGTH)
            flag_end=true;
        for (int i = count; (i < count + 10)&&(i <TOTAL_LENGTH); i++) {
            item = new ZoneListItem();
            item.setImage(getResources().getDrawable(R.drawable.icon_setting));
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setConcernTitle("这是标题（）");
            item.setConcernContent("这是内容（）");
            item.setConcern("这是类型（）");
            item.setConcernName("这是关注着（）");
            Log.d("TAG",i+"走到这里了");
            mList.add(item);
        }

    }


    public void initListView(){
        // 初始化data，装载八组数据到数组链表mList中
        ZoneListItem item;
        int i = 0;
        for (; i < 4; i++) {
            item = new ZoneListItem();
            item.setImage(getResources().getDrawable(R.drawable.icon_setting));
            item.setTextcolor(Color.parseColor("#9A32CD"));
            item.setConcernTitle("这是标题");
            item.setConcernContent("这是内容");
            item.setConcern("这是类型");
            item.setConcernName("这是关注着");
            mList.add(item);
        }
    }



    //定义ListView适配器MainListViewAdapter
    class ZoneListViewAdapter extends BaseAdapter {

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
            ZoneListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(ZoneActivity.this).inflate(
                        R.layout.linear_zone_listitem, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ZoneListItemView();
                listItemView.imageView = (ImageView) convertView
                        .findViewById(R.id.img_zone);
                listItemView.tv_concern_title = (TextView)convertView
                        .findViewById(R.id.tv_concern_title);
                listItemView.tv_concern_content = (TextView)convertView
                        .findViewById(R.id.tv_concern_content);
                listItemView.tv_concern_name = (TextView)convertView
                        .findViewById(R.id.tv_concern_name);
                listItemView.tv_concern = (TextView)convertView
                        .findViewById(R.id.tv_concern);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            } else {
                // 从converView中获取ListItemView对象
                listItemView = (ZoneListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            Drawable img = mList.get(position).getImage();
            String concern_title = mList.get(position).getConcernTitle();
            String concern_content = mList.get(position).getConcernContent();
            String concern_name = mList.get(position).getConcernName();
            String concern = mList.get(position).getConcern();
            int color=mList.get(position).getTextcolor();

            // 将资源传递给ListItemView的两个域对象
            listItemView.imageView.setImageDrawable(img);
            listItemView.tv_concern_content.setTextColor(color);
            listItemView.tv_concern_content.setText(concern_content);
            listItemView.tv_concern_title.setTextColor(color);
            listItemView.tv_concern_title.setText(concern_title);
            listItemView.tv_concern_name.setTextColor(color);
            listItemView.tv_concern_name.setText(concern_name);
            listItemView.tv_concern.setTextColor(color);
            listItemView.tv_concern.setText(concern);

            // 返回convertView对象
            return convertView;
        }
    }

    //封装两个视图组件的类
    class ZoneListItemView {
        ImageView imageView;
        TextView tv_concern_title;
        TextView tv_concern_content;
        TextView tv_concern_name;
        TextView tv_concern;
    }

    //封装了两个资源的类
    //为了区别系统应用而加一个颜色
    class ZoneListItem {
        private Drawable image;
        private String concern_title;
        private String concern_content;
        private String concern_name;
        private String concern;
        private int textcolor= Color.parseColor("#2F4F4F");

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public String getConcernTitle() {
            return concern_title;
        }

        public void setConcernTitle(String title) {
            this.concern_title = title;
        }

        public void setConcernContent(String content) {
            this.concern_content = content;
        }

        public String getConcernName() {
            return concern_name;
        }

        public void setConcernName(String name) {
            this.concern_name = name;
        }

        public String getConcern() {
            return concern;
        }

        public void setConcern(String concern) {
            this.concern = concern;
        }

        public String getConcernContent() {
            return concern_content;
        }

        public int getTextcolor(){
            return textcolor;
        }

        public void setTextcolor(int color){
            this.textcolor=color;
        }
    }






    //下面是gridview
    public class myImageAdapter extends BaseAdapter {
        private Context mContext;
        public myImageAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount() {
            return mThumbIds.length;
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView==null){
                //实例化ImageView对象
                imageView=new ImageView(mContext);
                //设置ImageView对象布局，设置View的height和width
                imageView.setLayoutParams(new GridView.LayoutParams(300,300));
                //设置边界对齐
                imageView.setAdjustViewBounds(false);
                //按比例统一缩放图片
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //设置间距
                imageView.setPadding(8,8,8,8);
            } else{
                imageView=(ImageView)convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
    }
}
