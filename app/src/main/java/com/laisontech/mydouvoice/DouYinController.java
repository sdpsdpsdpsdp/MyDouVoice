package com.laisontech.mydouvoice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.laisontech.videoplayer.controller.BaseVideoController;
import com.laisontech.videoplayer.player.IVideoView;
import com.laisontech.videoplayer.util.Log;

/**
 * 抖音
 */

public class DouYinController extends BaseVideoController {

    private ImageView thumb;
    public DouYinController(@NonNull Context context) {
        super(context);
    }

    public DouYinController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DouYinController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_douyin_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        thumb = controllerView.findViewById(R.id.iv_thumb);
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);

        switch (playState) {
            case IVideoView.STATE_IDLE:
                Log.e("STATE_IDLE");
                thumb.setVisibility(VISIBLE);
                break;
            case IVideoView.STATE_PLAYING:
                Log.e("STATE_PLAYING");
                thumb.setVisibility(GONE);
                break;
            case IVideoView.STATE_PREPARED:
                Log.e("STATE_PREPARED");
                break;
        }
    }

    public ImageView getThumb() {
        return thumb;
    }
}
