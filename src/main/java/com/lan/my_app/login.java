package com.lan.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.lan.my_app.database;
import com.baidu.speech.EventManagerFactory;

public class login extends AppCompatActivity {
    protected TextView txt_login;
    protected EditText name;
    protected EditText password;
    protected Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txt_login=findViewById(R.id.txt_login);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            database.getcon();
                            if(database.checkUser(name.getText().toString(),password.getText().toString())){
                                Intent intent = new Intent();
                                intent.setClass(login.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                txt_login.setText("用户不存在");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
