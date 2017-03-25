package com.whuzfb.lovebaby;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zfb15 on 2017/3/23.
 */
@SuppressLint("SdCardPath")
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

    private Bitmap head;//头像Bitmap
    private static String path="/sdcard/myHead/";//sd路径
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
        registerForContextMenu(img_user);
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
                openContextMenu(v);
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




    //以下内容用来实现更换头像

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0," 从相册选择");
        menu.add(0,2,0,"使用相机拍照");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                //Toast.makeText(HomeActivity.this,"你",Toast.LENGTH_SHORT).show();
                //调用手机相册的方法,该方法在下面有具体实现
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                //此处必须通过调用父Activity的startActivityForResult()然后再在父Activity中把函数传递到此处
                //如果不是用的复合结构，直接调用就可以。
                getParent().startActivityForResult(intent1, 4);
                break;
            case 2:
                //Toast.makeText(HomeActivity.this,"吗？",Toast.LENGTH_SHORT).show();
                //调用手机照相机的方法,通过Intent调用系统相机完成拍照，并调用系统裁剪器裁剪照片
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                getParent().startActivityForResult(intent2, 5);//采用ForResult打开
                break;
        }
        return super.onContextItemSelected(item);
    }


    //该方法实现通过何种方式跟换图片
    //@Override
    public void handleresult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG","走到这里了");
        switch (requestCode) {
            case 4:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 5:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 6:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if(head!=null){
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        img_user.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        getParent().startActivityForResult(intent, 6);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName =path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
