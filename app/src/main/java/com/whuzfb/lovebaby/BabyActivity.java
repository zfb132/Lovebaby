package com.whuzfb.lovebaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by zfb15 on 2017/3/19.
 */

public class BabyActivity extends Activity implements View.OnClickListener{
    private Button btn_examination=null;
    private Button btn_nurse=null;
    private Button btn_record=null;
    private Button btn_vaccine=null;
    private Button btn_statistics=null;
    private Button btn_doctor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_baby);

        //初始化各种控件
        btn_examination=(Button)findViewById(R.id.btn_examination);
        btn_nurse=(Button)findViewById(R.id.btn_nurse);
        btn_record=(Button)findViewById(R.id.btn_record);
        btn_vaccine=(Button)findViewById(R.id.btn_vaccine);
        btn_statistics=(Button)findViewById(R.id.btn_statistics);
        btn_doctor=(Button)findViewById(R.id.btn_doctor);

        //设置监听函数
        btn_examination.setOnClickListener(this);
        btn_nurse.setOnClickListener(this);
        btn_record.setOnClickListener(this);
        btn_vaccine.setOnClickListener(this);
        btn_statistics.setOnClickListener(this);
        btn_doctor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_examination:
                Toast.makeText(BabyActivity.this,"你点击了一键体检按钮",Toast.LENGTH_SHORT).show();
                Intent intenta=new Intent();
                intenta.setClass(BabyActivity.this,CheckBody.class);
                startActivity(intenta);
                break;
            case R.id.btn_nurse:
                Toast.makeText(BabyActivity.this,"你点击了育儿助手按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_record:
                Toast.makeText(BabyActivity.this,"你点击了宝贝日记按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(BabyActivity.this,NoteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_vaccine:
                Toast.makeText(BabyActivity.this,"你点击了疫苗提醒按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_statistics:
                Toast.makeText(BabyActivity.this,"你点击了数据分析按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_doctor:
                Toast.makeText(BabyActivity.this,"你点击了咨询医生按钮",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
