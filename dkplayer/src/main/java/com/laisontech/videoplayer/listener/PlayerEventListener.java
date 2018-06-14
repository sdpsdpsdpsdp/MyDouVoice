package com.laisontech.videoplayer.listener;

/**
 * 播放器事件接口
 */

public interface PlayerEventListener {

    void onError();

    void onCompletion();

    void onInfo(int what, int extra);

    void onBufferingUpdate(int percent);

    void onPrepared();

    void onVideoSizeChanged(int width, int height);

}
