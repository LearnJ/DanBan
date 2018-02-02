package com.example.jxm.daiban.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jxm.daiban.R;
import com.example.jxm.daiban.bean.VideoItemInfo;
import com.example.jxm.daiban.util.ItemClickListener;

import java.util.List;

/**
 * Created by jiamao on 2018/2/1.
 */

public class RecycleViewAdapter1 extends RecyclerView.Adapter {

    private Context mContext;
    private List<VideoItemInfo> infos;

    public RecycleViewAdapter1(Context mContext, List<VideoItemInfo> infos) {
        this.mContext = mContext;
        this.infos = infos;
    }

    static class MViewHolder extends RecyclerView.ViewHolder{
        public TextView trTV;
        public TextView titleTV;
        public ImageView preview_img;
        public ImageView icon_img;

        public MViewHolder(View itemView) {
            super(itemView);

            trTV=itemView.findViewById(R.id.topright_tv);
            titleTV=itemView.findViewById(R.id.item_title);
            preview_img=itemView.findViewById(R.id.preview_img);
            icon_img=itemView.findViewById(R.id.item_icon);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(mContext).inflate(R.layout.recycle_item1,parent,false);

        MViewHolder holder=new MViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VideoItemInfo info=infos.get(position);

        MViewHolder mViewHolder=(MViewHolder)holder;
        int imgid=info.getImgid();
        int iconid=info.getIconid();
        String title=info.getTitle();
        String trTxt=info.getTrTxt();

        mViewHolder.icon_img.setBackgroundResource(imgid);
        mViewHolder.preview_img.setBackgroundResource(iconid);

        Glide.with(mContext).load(info.getUrlIcon()).error(R.drawable.icon).into(mViewHolder.icon_img);
        Glide.with(mContext).load(info.getUrlPreview()).into(mViewHolder.preview_img);
        mViewHolder.titleTV.setText(title);
        mViewHolder.trTV.setText(trTxt);
        mViewHolder.preview_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mContext instanceof ItemClickListener){
                    ((ItemClickListener) mContext).onImageClick(info);
                }

            }
        });

        mViewHolder.titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mContext instanceof ItemClickListener){
                    ((ItemClickListener) mContext).onTitleClick(info);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return infos.size();
    }
}
