package com.example.androidwithh5;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        webView=(WebView)findViewById(R.id.wvMain);
        //获取webSettings
        WebSettings settings = webView.getSettings();
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        //WebSettings常用设置
        //setSupportZoom(boolean support) 是否支持使用屏幕控件或手势进行缩放
        //setAllowFileAccess(boolean allow) 设置在WebView内部是否允许访问文件，默认允许访问。
        //setAllowContentAccess(boolean allow) 设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        //setTextZoom(int textZoom) 设置WebView中加载页面字体变焦百分比，默认100，整型数。
        //setAllowUniversalAccessFromFileURLs(boolean flag) 运行中的脚本可以是否访问任何原始起点内容，默认true
        //setAllowFileAccessFromFileURLs(boolean flag) 设置WebView运行中的一个文件方案被允许访问其他文件方案中的内容，默认值true
        //setDomStorageEnabled(boolean flag) 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API

        //加载html页面
        webView.loadUrl("file:///android_asset/index.html");

        //打开js接口，参数1为本地类名，参数2为别名；h5通过 window.别名.类名里的方法名 来调用android里的接口
        webView.addJavascriptInterface(new JsInterface(MainActivity.this),"Android");
    }
}