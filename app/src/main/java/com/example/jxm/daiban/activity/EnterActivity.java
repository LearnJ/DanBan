package com.example.jxm.daiban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jxm.daiban.R;

import java.lang.ref.WeakReference;

public class EnterActivity extends AppCompatActivity {

    private Handler mhandler;
    private TextView countTV;
    public int countNum=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        countTV=findViewById(R.id.start_logo_count);
        mhandler=new MyHandler(this);
        mhandler.sendEmptyMessageDelayed(1,1000);

    }

    static class MyHandler extends Handler
    {
        WeakReference<EnterActivity> mWeakReference;
        public MyHandler(EnterActivity activity)
        {
            mWeakReference=new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg)
        {
            final EnterActivity activity=mWeakReference.get();
            if(activity!=null)
            {
                Log.d("jm", "handleMessage: ");
                if (msg.what == 1)
                {
                    if (activity.countNum<=0){

                        Intent intent=new Intent(activity,MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else{
                        activity.countTV.setText("倒计时："+ activity.countNum+"s");
                        activity.mhandler.sendEmptyMessageDelayed(1,1000);
                        activity.countNum--;
                    }
                }
            }
        }
    }


}
