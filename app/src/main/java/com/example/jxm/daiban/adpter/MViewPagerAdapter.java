package com.example.jxm.daiban.adpter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jxm.daiban.util.OnViewClickListener;

import java.util.List;

/**
 * Created by jiamao on 2018/2/9.
 */

public class MViewPagerAdapter extends PagerAdapter {

    private List<View>views;

    private OnViewClickListener listener;

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public OnViewClickListener getListener() {
        return listener;
    }

    public void setListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    public MViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View view =views.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                    Toast.makeText(container.getContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
                     listener.OnViewClick(position);
            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
