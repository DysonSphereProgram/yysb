package com.lan.my_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EventListener {
    protected Button btn_search;
    protected TextView txt_result;
    protected Button btn_start;
    protected Button btn_stop;
    private EventManager asr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
        btn_search=findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://m.baidu.com/from=844b/s?word="+txt_result.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        asr= EventManagerFactory.create(this,"asr");
        asr.registerListener(this);
    }
    private void initView() {
        txt_result=findViewById(R.id.txt_result);
        btn_start=findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asr.send(SpeechConstant.ASR_START,"{}",null,0,0);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asr.send(SpeechConstant.ASR_STOP,"{}",null,0,0);
            }
        });
    }
    private void initPermission(){
        String permissions[]={
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ArrayList<String> toApplyList=new ArrayList<String>();
        for(String perm:permissions){
            if(PackageManager.PERMISSION_GRANTED!= ContextCompat.checkSelfPermission(this,perm)){
                toApplyList.add(perm);
            }
        }
        String tmpList[]=new String[toApplyList.size()];
        if(!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this,toApplyList.toArray(tmpList),123);

        }
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    @Override
    public void onEvent(String s, String s1, byte[] bytes, int i, int i1) {
        if(s.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)){
            if (s1 == null || s1.isEmpty()) {
                return;
            }
            if (s1.contains("\"final_result\"")) {
                // 一句话的最终识别结果
                Gson gson=new Gson();
                ASRresponse asRresponse=gson.fromJson(s1,ASRresponse.class);
                if(asRresponse==null) return;
                if(asRresponse.getBest_result().contains(",")){
                    txt_result.setText(asRresponse.getBest_result().replace(',',' ').trim());
                }else {
                    txt_result.setText(asRresponse.getBest_result().trim());
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //发送取消事件
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        //退出事件管理器
        // 必须与registerListener成对出现，否则可能造成内存泄露
        asr.unregisterListener(this);
    }
}
