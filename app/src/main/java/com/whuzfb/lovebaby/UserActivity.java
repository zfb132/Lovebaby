package com.whuzfb.lovebaby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zfb15 on 2017/3/23.
 */

public class UserActivity extends Activity implements View.OnClickListener{
    private ImageView img_user_email;
    private ImageView img_user;
    private TextView tv_user_vote=null;
    private TextView tv_user_concern=null;
    private TextView tv_user_fans=null;
    private LinearLayout ll_collect=null;
    private LinearLayout ll_note=null;
    private LinearLayout ll_comment=null;
    private LinearLayout ll_feedback=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_user);

        //初始化
        img_user_email=(ImageView)findViewById(R.id.img_user_email);
        img_user=(ImageView)findViewById(R.id.img_user);
        tv_user_vote=(TextView)findViewById(R.id.tv_user_vote);
        tv_user_concern=(TextView)findViewById(R.id.tv_user_concern);
        tv_user_fans=(TextView)findViewById(R.id.tv_user_fans);
        ll_collect=(LinearLayout)findViewById(R.id.ll_collect);
        ll_note=(LinearLayout)findViewById(R.id.ll_note);
        ll_comment=(LinearLayout)findViewById(R.id.ll_comment);
        ll_feedback=(LinearLayout)findViewById(R.id.ll_feedback);

        img_user_email.setOnClickListener(this);
        img_user.setOnClickListener(this);
        tv_user_vote.setOnClickListener(this);
        tv_user_concern.setOnClickListener(this);
        tv_user_fans.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_note.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_user_email:
                Toast.makeText(UserActivity.this,"你点击了邮箱图标",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_user:
                Toast.makeText(UserActivity.this,"你点击了头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_vote:
                Toast.makeText(UserActivity.this,"我的赞的个数",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_concern:
                Toast.makeText(UserActivity.this,"我关注的人数",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_fans:
                Toast.makeText(UserActivity.this,"我的粉丝数",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_collect:
                Toast.makeText(UserActivity.this,"我收藏的文章",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_comment:
                Toast.makeText(UserActivity.this,"我发的帖子",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_note:
                Toast.makeText(UserActivity.this,"我的评论",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_feedback:
                Toast.makeText(UserActivity.this,"意见反馈",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
