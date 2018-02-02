package com.example.jxm.daiban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jxm.daiban.R;
import com.example.jxm.daiban.adpter.RecycleViewAdapter1;
import com.example.jxm.daiban.bean.VideoItemInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamao on 2018/2/1.
 */

public class fragment1 extends Fragment {

    private List<VideoItemInfo> infos;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment1,container,false);

        initDatas();
        final RecyclerView recyclerView=view.findViewById(R.id.recycleView);
        final RecycleViewAdapter1 adapter=new RecycleViewAdapter1(getActivity(),infos);
         refreshLayout=view.findViewById(R.id.swipeRefresh);


         //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                        try {
                            Thread.sleep(1000);
                            addDatas();
                            adapter.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


        });
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecoration(20));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;

    }
   void initDatas(){

        infos=new ArrayList<>();

        for (int i=0;i<30;i++){

            VideoItemInfo info=new VideoItemInfo();
            info.setImgid(R.mipmap.ic_launcher);
            info.setIconid(R.drawable.icon);
            info.setTrTxt(i+"");
            info.setUrlIcon("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=147015573,3182587356&fm=27&gp=0.jpg");
            info.setUrlPreview("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=49366202,632101467&fm=27&gp=0.jpg");
            info.setTitle("第"+i+"集");
            infos.add(info);
        }
   }

   void addDatas(){
       for (int i=0;i<30;i++){

           VideoItemInfo info=new VideoItemInfo();
           info.setImgid(R.mipmap.ic_launcher);
           info.setIconid(R.drawable.icon);
           info.setTrTxt(i+"");
           info.setUrlIcon("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=147015573,3182587356&fm=27&gp=0.jpg");
           info.setUrlPreview("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=49366202,632101467&fm=27&gp=0.jpg");
           info.setTitle("第"+i+"集");
           infos.add(info);
       }
   }
   @Subscribe(threadMode = ThreadMode.MAIN)
   void onEvent(VideoItemInfo info){


   }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
