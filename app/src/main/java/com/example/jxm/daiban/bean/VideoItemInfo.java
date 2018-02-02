package com.example.jxm.daiban.bean;

/**
 * Created by jiamao on 2018/2/1.
 */

public class VideoItemInfo {
    private int imgid;
    private int iconid;
    private String urlPreview;
    private String urlIcon;
    private String title;
    private String trTxt;

    public String getUrlPreview() {
        return urlPreview;
    }

    public void setUrlPreview(String urlPreview) {
        this.urlPreview = urlPreview;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public int getIconid() {
        return iconid;
    }

    public void setIconid(int iconid) {
        this.iconid = iconid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrTxt() {
        return trTxt;
    }

    public void setTrTxt(String trTxt) {
        this.trTxt = trTxt;
    }
}
