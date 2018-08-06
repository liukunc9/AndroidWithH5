package com.example.androidwithh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

        //拦截网页中的超链接，让webView自己处理各种通知、请求等事件
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url == null) return false;
                //webview只能识别http, https这样的协议
                try{
                    if(url.startsWith("http://")||url.startsWith("https://")){
                        //处理http和https开头的url
                        view.loadUrl(url);
                        return false;
                    }else{
                        //处理不以http或https为开头的url请求
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }
            }
        });

        //打开js接口，参数1为本地类名，参数2为别名；h5通过 window.别名.类名里的方法名 来调用android里的接口
        webView.addJavascriptInterface(new JsInterface(MainActivity.this),"Android");

        //允许调试设置
        webView.setWebContentsDebuggingEnabled(true);

        //android中调用js方法
        //webView.loadUrl("javascript:javacalljs('"+参数名+'")");
    }
}