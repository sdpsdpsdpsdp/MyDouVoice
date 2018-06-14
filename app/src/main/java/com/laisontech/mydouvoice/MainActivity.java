package com.laisontech.mydouvoice;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laisontech.mydouvoice.adapter.DouYinAdapter;
import com.laisontech.mydouvoice.bean.VideoBean;
import com.laisontech.mydouvoice.utils.DataUtil;
import com.laisontech.videoplayer.player.IVideoView;
import com.laisontech.videoplayer.player.PlayerConfig;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DouYinActivity";
    private IVideoView mIjkVideoView;
    private DouYinController mDouYinController;
    private DouYinAdapter mDouYinAdapter;
    private List<VideoBean> mVideoList;
    private List<View> mViews = new ArrayList<>();
    private int mCurrentPosition;
    private int mPlayingPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin);

        setStatusBarTransparent();

        mIjkVideoView = new IVideoView(this);
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(this);
        mIjkVideoView.setVideoController(mDouYinController);
        mVideoList = DataUtil.getDouYinVideoList();
        for (VideoBean item : mVideoList) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_douyin, null);
            ImageView imageView = view.findViewById(R.id.thumb);
            Glide.with(this).load(item.getThumb()).into(imageView);
            mViews.add(view);
        }

        mDouYinAdapter = new DouYinAdapter(mViews);
    }

    private void startPlay() {
        View view = mViews.get(mCurrentPosition);
        ImageView imageView = view.findViewById(R.id.thumb);
        mDouYinController.getThumb().setImageDrawable(imageView.getDrawable());
        mIjkVideoView.setUrl(mVideoList.get(mCurrentPosition).getUrl());
        mIjkVideoView.setScreenScale(IVideoView.SCREEN_SCALE_CENTER_CROP);
        mIjkVideoView.start();
        mPlayingPosition = mCurrentPosition;
    }

    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView =this.getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIjkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }
}
