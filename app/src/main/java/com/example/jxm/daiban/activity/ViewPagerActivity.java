package com.example.jxm.daiban.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.example.jxm.daiban.R;
import com.example.jxm.daiban.adpter.MViewPagerAdapter;
import com.example.jxm.daiban.util.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MViewPagerAdapter adapter;

    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
        adapter = new MViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        //viewPager.setPageMargin(80);
        viewPager.setOffscreenPageLimit(3);
        adapter.setListener(new OnViewClickListener() {
            @Override
            public void OnViewClick(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        viewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, getResources().getDisplayMetrics()));
        viewPager.setPageTransformer(false, new ScaleTransformer(this));


    }

    void initView() {
        viewPager = findViewById(R.id.viewpager);
        views = new ArrayList<View>();

//        ImageView IMG1 = new ImageView(this);
//        IMG1.setBackgroundResource(R.drawable.img1);
//        ImageView IMG2 = new ImageView(this);
//        IMG2.setBackgroundResource(R.drawable.img2);
//        ImageView IMG3 = new ImageView(this);
//        IMG3.setBackgroundResource(R.drawable.img1);
//        ImageView IMG4 = new ImageView(this);
//        IMG4.setBackgroundResource(R.drawable.img2);
        View v1= LayoutInflater.from(this).inflate(R.layout.view_1,null);
        View v2= LayoutInflater.from(this).inflate(R.layout.view_2,null);
        View v3= LayoutInflater.from(this).inflate(R.layout.view_3,null);
        View v4= LayoutInflater.from(this).inflate(R.layout.view_4,null);


        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);



    }

    public class ScaleTransformer implements ViewPager.PageTransformer {
        private Context context;
        private float elevation;
        private static final float MIN_SCALE = 0.70f;
        private static final float MIN_ALPHA = 0.5f;

        public ScaleTransformer(Context context) {
            this.context = context;
            elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    20, context.getResources().getDisplayMetrics());
        }
   /* position取值特点：
                * 假设页面从0～1，则：
                * 第一个页面position变化为[0,-1]
                * 第二个页面position变化为[1,0]
                * */
        @Override
        public void transformPage(View page, float position) {
            Log.d("viewpage ", "transformPage:page= "+page+"  position= "+position);
            if (position < -1 || position > 1) {
                page.setAlpha(MIN_ALPHA);
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                if (position < 0) {
                    float scaleX = 1 + 0.3f * position;
                    Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                } else {
                    float scaleX = 1 - 0.3f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                }
               // page.setAlpha(MIN_ALPHA+);
                page.setAlpha(scaleFactor);

//                int width = page.getWidth();
//                int pivotX = 0;
//                if (position <= 1 && position > 0) {// right scrolling
//                    pivotX = 0;
//                } else if (position == 0) {
//
//                } else if (position < 0 && position >= -1) {// left scrolling
//                    pivotX = width;
//                }
//                //设置x轴的锚点
//                page.setPivotX(pivotX);
//                //设置绕Y轴旋转的角度
//                page.setRotationY(90f * position);

            }
        }
    }




}
