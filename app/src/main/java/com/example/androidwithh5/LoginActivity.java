package com.example.androidwithh5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View v){
        //在此添加密码验证

        Intent intent=new Intent();
        intent.setAction("myapp.intent.action.menu");
        startActivity(intent);
    }
}