package com.example.jxm.daiban.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.jxm.daiban.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button getImgBtn;
    final SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
            Log.d("glide", "onResourceReady: "+bitmap);
            System.out.println("jm---onclick----setbitmap");
            Toast.makeText(GlideActivity.this,"get the bitmap",Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap( bitmap );
        }

        @Override
        public void onLoadCleared(Drawable placeholder) {
            super.onLoadCleared(placeholder);
            Toast.makeText(GlideActivity.this,"onLoadCleared",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
            Toast.makeText(GlideActivity.this,"onLoadStarted",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            super.onLoadFailed(e, errorDrawable);
            e.printStackTrace();
            Toast.makeText(GlideActivity.this,"onLoadFailed"+e.toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
            super.onStart();
            Toast.makeText(GlideActivity.this,"onStart",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        if ( ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }
//        if ( ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
//
//
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},2);
//
//        }




        imageView=findViewById(R.id.img_display);
        getImgBtn=findViewById(R.id.getimg_bt);

        getImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(GlideActivity.this,"click the button",Toast.LENGTH_SHORT).show();
                System.out.println("jm---onclick");
                Log.d("glide", "onClick: ");
                Glide.with(GlideActivity.this)
                      //  .load("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
                        .load("")
                        .placeholder(R.drawable.img1)
                        .fallback(R.drawable.img2)
                        .into(imageView);

            }
        });


    }

    class MyTarget extends  SimpleTarget<Bitmap>{


        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {




        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(GlideActivity.this,"读写权限授权成功！",Toast.LENGTH_SHORT).show();


                } else {


                }
                break;
            case 2:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Toast.makeText(GlideActivity.this,"网络权限授权成功！",Toast.LENGTH_SHORT).show();

                } else {


                }
                break;

            }
        }

    }

