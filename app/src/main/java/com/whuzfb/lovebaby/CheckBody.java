package com.whuzfb.lovebaby;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zfb15 on 2017/3/27.
 */

public class CheckBody extends Activity implements View.OnClickListener{
    private CheckBox checkBox_1=null;
    private CheckBox checkBox_2=null;
    private CheckBox checkBox_3=null;
    private CheckBox checkBox_4=null;
    private CheckBox checkBox_5=null;
    private CheckBox checkBox_6=null;
    private CheckBox checkBox_7=null;
    private CheckBox checkBox_8=null;
    private CheckBox checkBox_9=null;
    private CheckBox checkBox_10=null;
    private Button btn_checkbody_all=null;
    private Button btn_checkbody_ok=null;
    private Button btn_checkbody_none=null;
    private TextView tv_result=null;
    /**
     * 症状类型：
     */
    private boolean ill_flag[]=new boolean[10];
    /**
     * 疾病类型：
     * 感冒，风寒，消化不良
     */
    private int score_illness[]=new int[10];
    private Map temp_position =new HashMap();

    private myCheckChange myboxchange=new myCheckChange();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbody);

        initView();
        initListener();
    }

    class myCheckChange implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.checkbox_1:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[0]=true;
                        //analyse(0,1);
                        score_illness[0]=score_illness[0]+10;
                        score_illness[1]=score_illness[1]+10;
                    }else{
                        ill_flag[0]=false;
                    }

                    break;
                case R.id.checkbox_2:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[1]=true;
                        //analyse(0,1);
                        score_illness[0]=score_illness[0]+10;
                        score_illness[1]=score_illness[1]+10;
                    }else{
                        ill_flag[1]=false;
                    }

                    break;
                case R.id.checkbox_3:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[2]=true;
                        //analyse(2);
                        score_illness[2]=score_illness[2]+10;
                        //score_illness[1]=score_illness[1]+10;
                    }else{
                        ill_flag[2]=false;
                    }

                    break;
                case R.id.checkbox_4:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[3]=true;
                        //analyse(0,1);
                        score_illness[0]=score_illness[0]+10;
                        score_illness[1]=score_illness[1]+10;
                    }else{
                        ill_flag[3]=false;
                    }

                    break;
                case R.id.checkbox_5:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[4]=true;
                    }else{
                        ill_flag[4]=false;
                    }
                    break;
                case R.id.checkbox_6:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[5]=true;
                    }else{
                        ill_flag[5]=false;
                    }
                    break;
                case R.id.checkbox_7:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[6]=true;
                        //analyse(1);
                        //score_illness[0]=score_illness[0]+10;
                        score_illness[1]=score_illness[1]+10;
                    }else{
                        ill_flag[6]=false;
                    }

                    break;
                case R.id.checkbox_8:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[7]=true;
                    }else{
                        ill_flag[7]=false;
                    }
                    break;
                case R.id.checkbox_9:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[8]=true;
                        //analyse(2);
                        score_illness[2]=score_illness[2]+10;
                    }else{
                        ill_flag[8]=false;
                    }

                    break;
                case R.id.checkbox_10:
                    if(isChecked){
                        //Toast.makeText(CheckBody.this,"你选择了一键体检1按钮",Toast.LENGTH_SHORT).show();
                        ill_flag[9]=true;
                    }else{
                        ill_flag[9]=false;
                    }
                    break;
                default:
                    //Toast.makeText(CheckBody.this,"选择按钮",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void initView(){
        checkBox_1=(CheckBox)findViewById(R.id.checkbox_1);
        checkBox_2=(CheckBox)findViewById(R.id.checkbox_2);
        checkBox_3=(CheckBox)findViewById(R.id.checkbox_3);
        checkBox_4=(CheckBox)findViewById(R.id.checkbox_4);
        checkBox_5=(CheckBox)findViewById(R.id.checkbox_5);
        checkBox_6=(CheckBox)findViewById(R.id.checkbox_6);
        checkBox_7=(CheckBox)findViewById(R.id.checkbox_7);
        checkBox_8=(CheckBox)findViewById(R.id.checkbox_8);
        checkBox_9=(CheckBox)findViewById(R.id.checkbox_9);
        checkBox_10=(CheckBox)findViewById(R.id.checkbox_10);
        btn_checkbody_all=(Button)findViewById(R.id.btn_checkbody_all);
        btn_checkbody_ok=(Button)findViewById(R.id.btn_checkbody_ok);
        btn_checkbody_none=(Button)findViewById(R.id.btn_checkbody_none);
        tv_result=(TextView)findViewById(R.id.tv_ill_result);
        for (int i=0;i<10;i++){
            score_illness[i]=0;
        }
    }

    public void initListener(){
        checkBox_1.setOnCheckedChangeListener(myboxchange);
        checkBox_2.setOnCheckedChangeListener(myboxchange);
        checkBox_3.setOnCheckedChangeListener(myboxchange);
        checkBox_4.setOnCheckedChangeListener(myboxchange);
        checkBox_5.setOnCheckedChangeListener(myboxchange);
        checkBox_6.setOnCheckedChangeListener(myboxchange);
        checkBox_7.setOnCheckedChangeListener(myboxchange);
        checkBox_8.setOnCheckedChangeListener(myboxchange);
        checkBox_9.setOnCheckedChangeListener(myboxchange);
        checkBox_10.setOnCheckedChangeListener(myboxchange);
        btn_checkbody_all.setOnClickListener(this);
        btn_checkbody_ok.setOnClickListener(this);
        btn_checkbody_none.setOnClickListener(this);
    }

    public void setAllChecked(boolean flag){
        checkBox_1.setChecked(flag);
        checkBox_2.setChecked(flag);
        checkBox_3.setChecked(flag);
        checkBox_4.setChecked(flag);
        checkBox_5.setChecked(flag);
        checkBox_6.setChecked(flag);
        checkBox_7.setChecked(flag);
        checkBox_8.setChecked(flag);
        checkBox_9.setChecked(flag);
        checkBox_10.setChecked(flag);
    }

    public void analyse(int ...array){
        for(int i=0;i<10;i++){
            if(i<array.length){
                score_illness[array[i]]=score_illness[array[i]]+10;
            }
        }
    }

    public void result(){
        String m="没有";
        try{
            InputStream in=getAssets().open("book.xml");
            m=DomReader.readXml(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_result.setText("诊断结果：\n最可能的是："+findMax(1,true)+"\n其次可能的是："+findMax(2,false)+"\n"+m);
        for (int i=0;i<10;i++){
            score_illness[i]=0;
        }
    }

    public String findMax(int turn,boolean flag){
        if(flag){
            for(int i=0;i<score_illness.length;i++){
                temp_position.put(score_illness[i],i+1);
                Log.d("TAG",score_illness[i]+"-------排序前----->"+i);
            }
            Arrays.sort(score_illness);
        }
        Log.d("TAG",score_illness[score_illness.length-turn]+"----排序后--->"+temp_position.get(score_illness[score_illness.length-turn]));
        return temp_position.get(score_illness[score_illness.length-turn])+"";
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkbody_all:
                //Toast.makeText(CheckBody.this,"全选",Toast.LENGTH_SHORT).show();
                setAllChecked(true);
                break;
            case R.id.btn_checkbody_ok:
                //Toast.makeText(CheckBody.this,"确定",Toast.LENGTH_SHORT).show();
                result();
                setAllChecked(false);
                break;
            case R.id.btn_checkbody_none:
                //Toast.makeText(CheckBody.this,"全不选",Toast.LENGTH_SHORT).show();
                setAllChecked(false);
                break;
        }
    }
}
