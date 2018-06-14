package com.laisontech.mydouvoice.fragment.home;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laisontech.mydouvoice.DouYinController;
import com.laisontech.mydouvoice.R;
import com.laisontech.mydouvoice.adapter.DouYinAdapter;
import com.laisontech.mydouvoice.base.BaseFragment;
import com.laisontech.mydouvoice.bean.VideoBean;
import com.laisontech.mydouvoice.utils.DataUtil;
import com.laisontech.videoplayer.player.IVideoView;
import com.laisontech.videoplayer.player.PlayerConfig;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * ..................................................................
 * .         The Buddha said: I guarantee you have no bug!          .
 * .                                                                .
 * .                            _ooOoo_                             .
 * .                           o8888888o                            .
 * .                           88" . "88                            .
 * .                           (| -_- |)                            .
 * .                            O\ = /O                             .
 * .                        ____/`---'\____                         .
 * .                      .   ' \\| |// `.                          .
 * .                       / \\||| : |||// \                        .
 * .                     / _||||| -:- |||||- \                      .
 * .                       | | \\\ - /// | |                        .
 * .                     | \_| ''\---/'' | |                        .
 * .                      \ .-\__ `-` ___/-. /                      .
 * .                   ___`. .' /--.--\ `. . __                     .
 * .                ."" '< `.___\_<|>_/___.' >'"".                  .
 * .               | | : `- \`.;`\ _ /`;.`/ - ` : | |               .
 * .                 \ \ `-. \_ __\ /__ _/ .-` / /                  .
 * .         ======`-.____`-.___\_____/___.-`____.-'======          .
 * .                            `=---='                             .
 * ..................................................................
 * Created by SDP on 2018/6/13.
 */

public class RecommendFragment extends BaseFragment {
    private static final String TAG = "DouYinActivity";
    private IVideoView mIjkVideoView;
    private DouYinController mDouYinController;
    private VerticalViewPager mVerticalViewPager;
    private DouYinAdapter mDouYinAdapter;
    private List<VideoBean> mVideoList;
    private List<View> mViews = new ArrayList<>();
    private int mCurrentPosition;
    private int mPlayingPosition;


    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initViews(View view) {
        mIjkVideoView = new IVideoView(getContext());
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(getContext());
        mIjkVideoView.setVideoController(mDouYinController);
        mVerticalViewPager = view.findViewById(R.id.vvp);
        mVideoList = DataUtil.getDouYinVideoList();
    }

    @Override
    protected void initEvent() {
        for (VideoBean item : mVideoList) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_douyin, null);
            ImageView imageView = view.findViewById(R.id.thumb);
            Glide.with(this).load(item.getThumb()).into(imageView);
            mViews.add(view);
        }

        mDouYinAdapter = new DouYinAdapter(mViews);
        mVerticalViewPager.setAdapter(mDouYinAdapter);

        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e(TAG, "mCurrentId == " + position + ", positionOffset == " + positionOffset +
//                        ", positionOffsetPixels == " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {//出现bug,
                Log.d(TAG, "position: " + position);
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: " + state);
                if (mPlayingPosition == mCurrentPosition) return;//防止重复播放
                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mIjkVideoView.release();
                    ViewParent parent = mIjkVideoView.getParent();
                    if (parent != null && parent instanceof FrameLayout) {
                        ((FrameLayout) parent).removeView(mIjkVideoView);
                    }
                    startPlay();
                }
            }
        });
        //自动播放第一条
        mVerticalViewPager.post(this::startPlay);
    }

    private void startPlay() {
        View view = mViews.get(mCurrentPosition);
        FrameLayout frameLayout = view.findViewById(R.id.container);
        ImageView imageView = view.findViewById(R.id.thumb);
        mDouYinController.getThumb().setImageDrawable(imageView.getDrawable());
        frameLayout.addView(mIjkVideoView);
        mIjkVideoView.setUrl(mVideoList.get(mCurrentPosition).getUrl());
        mIjkVideoView.setScreenScale(IVideoView.SCREEN_SCALE_CENTER_CROP);
        mIjkVideoView.start();
        mPlayingPosition = mCurrentPosition;
    }

    @Override
    public void onPause() {
        super.onPause();
        mIjkVideoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIjkVideoView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }
}
