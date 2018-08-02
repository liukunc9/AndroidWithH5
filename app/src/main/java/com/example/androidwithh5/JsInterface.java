package com.example.androidwithh5;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JsInterface {
    Context context;
    private Handler mHandler=new Handler();

    public  JsInterface(Context context){
        this.context=context;
    }

    @JavascriptInterface //一定要写，不然H5调用不到这个方法
    public void ShowMessage(final String msg){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
