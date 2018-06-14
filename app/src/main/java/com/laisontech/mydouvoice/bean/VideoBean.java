package com.laisontech.mydouvoice.bean;

/**
 * Created by Devlin_n on 2017/6/1.
 */

public class VideoBean {
    private  int id;
    private String title;
    private String url;
    private String thumb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public VideoBean(String title, String thumb, String url,int id) {
        this.title = title;
        this.thumb = thumb;
        this.url = url;
        this.id = id;
    }

    public VideoBean(String title, String thumb, String url) {
        this.title = title;
        this.thumb = thumb;
        this.url = url;
    }
}
