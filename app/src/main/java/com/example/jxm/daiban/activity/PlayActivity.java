package com.example.jxm.daiban.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.jxm.daiban.R;

public class PlayActivity extends AppCompatActivity {


    private ViewFlipper viewFlipper;
    private TextView userName;
    private WebView webView;
    private final String TAG="PlayActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        webView=findViewById(R.id.webView);
        viewFlipper=findViewById(R.id.flipper);
        userName=findViewById(R.id.play_user_name);
        ImageView img1=new ImageView(this);
        img1.setBackgroundResource(R.drawable.ic_launcher_background);
        viewFlipper.addView(img1);
        ImageView img2=new ImageView(this);
        img1.setBackgroundResource(R.drawable.icon);
        viewFlipper.addView(img2);

        viewFlipper.startFlipping();
        Intent intent=getIntent();
        String url=intent.getStringExtra("play_addr");
        loadWeb(url);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayActivity.this,"本视频由李四上传",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadWeb(String url){

        if (url==null){
            return ;
        }
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // webSettings.setAppCacheEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d(TAG, "onReceivedTitle: "+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });
        webView.loadUrl(url);
    }
}
