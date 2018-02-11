package com.example.jxm.daiban.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.example.jxm.daiban.R;
import com.example.jxm.daiban.adpter.MViewPagerAdapter;
import com.example.jxm.daiban.util.FixedSpeedScroller;
import com.example.jxm.daiban.util.OnViewClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MViewPagerAdapter adapter;

    private List<View> views;
    private Button sendBt;
    private String TAG="ViewPageActivity";

    private boolean reverse=false;
    Timer timer;
    TimerTask task;
    FixedSpeedScroller scroller;
    //循环自动轮播
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setScrollDuration(1300,viewPager);
            if (!reverse) {//正向滑动
                int currPos = viewPager.getCurrentItem();
                Log.d("slide", "正向滑动handleMessage: "+currPos);
                int nextPos = ++currPos;//即将要跳到的位置
                if (nextPos <= views.size() - 1)
                    viewPager.setCurrentItem(nextPos);
                else {
                    reverse=true;
                    viewPager.setCurrentItem(views.size() - 2);
                }
            }else{

                int currPos = viewPager.getCurrentItem();
                Log.d("slide", "handleMessage: "+currPos);
                int nextPos = --currPos;
                if (nextPos >= 0)
                    viewPager.setCurrentItem(nextPos);
                else {
                    reverse=false;
                    viewPager.setCurrentItem(1);
                }
            }
            //setScrollDuration(5000,viewPager);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
        adapter = new MViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        try {  //实现自定义page切换时过度动画的时间长短。
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            scroller = new FixedSpeedScroller(viewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(viewPager, scroller);
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
        viewPager.setOffscreenPageLimit(3);
        adapter.setListener(new OnViewClickListener() {
            @Override
            public void OnViewClick(int position) {//点击pager切换到当前。
                scroller.setmDuration(2000);
                viewPager.setCurrentItem(position);

            }
        });
        viewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, getResources().getDisplayMetrics()));
        viewPager.setPageTransformer(false, new ScaleTransformer(this));
        viewPager.setCurrentItem(0);
        timer =new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);

            }
        };
        timer.schedule(task,0,1500);
        //触摸停止轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                       if (timer!=null) {
                           timer.cancel();
                           timer = null;
                       }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch:ACTION_UP ");
                        timer=new Timer();
                        task=new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(0);

                            }
                        };
                        timer.schedule(task,0,1500);

                }
                return false;
            }
        });
   }

    void initView() {
        viewPager = findViewById(R.id.viewpager);
        sendBt=findViewById(R.id.send_note);
        views = new ArrayList<View>();


        View v1= LayoutInflater.from(this).inflate(R.layout.view_1,null);
        View v2= LayoutInflater.from(this).inflate(R.layout.view_2,null);
        View v3= LayoutInflater.from(this).inflate(R.layout.view_3,null);
        View v4= LayoutInflater.from(this).inflate(R.layout.view_4,null);


        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);


        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bitmap largeIcon = ((BitmapDrawable) getResources().getDrawable(R.drawable.icon)).getBitmap();
                NotificationCompat.Builder builder=new NotificationCompat.Builder(ViewPagerActivity.this,"test");
                builder.setAutoCancel(true).setContentText("内容").setContentTitle("内容标题").setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon);

                builder.setDefaults(Notification.DEFAULT_ALL);	//铃声,振动,呼吸灯
                PendingIntent pendingIntent=PendingIntent.getActivity(ViewPagerActivity.this,1,new Intent(ViewPagerActivity.this,PlayActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification=builder.build();
                notification.contentIntent=pendingIntent;
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);


                manager.notify("fff",1,notification);

            }
        });

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


    void setScrollDuration(int duration ,ViewPager pager){

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(pager.getContext(),
                    new AccelerateInterpolator());
            field.set(pager, scroller);
            scroller.setmDuration(duration);
        } catch (Exception e) {
           Log.e(TAG, "", e);
        }

    }
}
