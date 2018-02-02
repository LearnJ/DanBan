package com.example.jxm.daiban.fragment;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jiamao on 2018/2/1.
 */
//设置item间距，像素单位
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;
    public ItemDecoration(int space) {
        mSpace=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mSpace;
        }
    }
}
