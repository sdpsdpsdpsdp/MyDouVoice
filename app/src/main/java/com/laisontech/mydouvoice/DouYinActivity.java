package com.laisontech.mydouvoice;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.laisontech.mydouvoice.customview.CustomerTextView;
import com.laisontech.mydouvoice.fragment.AttentionFragment;
import com.laisontech.mydouvoice.fragment.MessageFragment;
import com.laisontech.mydouvoice.fragment.MineFragment;
import com.laisontech.mydouvoice.fragment.home.HomeFragment;
import com.laisontech.mydouvoice.utils.WindowUtils;
import com.laisontech.videoplayer.util.WindowUtil;

import java.util.ArrayList;
import java.util.List;

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

public class DouYinActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomerTextView ctvHome;
    private CustomerTextView ctvAttention;
    private CustomerTextView ctvMessage;
    private CustomerTextView ctvMine;
    private FrameLayout rlAddVideo;
    private List<CustomerTextView> mTvViews;
    private HomeFragment mHomeFragment;
    private AttentionFragment mAttentionFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin);
        WindowUtils.setStatusBarTransparent(this);
        initViews();
        initDatas();
    }




    private void initViews() {
        ctvHome = findViewById(R.id.ctv_Home);
        ctvAttention = findViewById(R.id.ctv_Attention);
        ctvMessage = findViewById(R.id.ctv_Message);
        ctvMine = findViewById(R.id.ctv_Mine);
        rlAddVideo = findViewById(R.id.rl_add_video);

        ctvHome.setOnClickListener(this);
        ctvAttention.setOnClickListener(this);
        ctvMessage.setOnClickListener(this);
        ctvMine.setOnClickListener(this);
        rlAddVideo.setOnClickListener(this);
    }

    private void initDatas() {
        mTvViews = new ArrayList<>();
        mTvViews.add(ctvHome);
        mTvViews.add(ctvAttention);
        mTvViews.add(ctvMessage);
        mTvViews.add(ctvMine);
        initHomeFragment();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_Home:
                setViewSelect(ctvHome);
                initHomeFragment();
                break;
            case R.id.ctv_Attention:
                setViewSelect(ctvAttention);
                initAttentionFragment();
                break;
            case R.id.ctv_Message:
                setViewSelect(ctvMessage);
                initMessageFragment();
                break;
            case R.id.ctv_Mine:
                setViewSelect(ctvMine);
                initMineFragment();
                break;
            case R.id.rl_add_video:
                //打开dialog
                break;
        }
    }

    private void setViewSelect(CustomerTextView tvSelect) {
        for (CustomerTextView tv : mTvViews) {
            tv.setUnSelected();
        }
        tvSelect.setSelected();
    }

    private void initHomeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.main_container, mHomeFragment);
        }
        hideAllFragment(transaction);
        transaction.show(mHomeFragment).commit();
    }


    private void initAttentionFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mAttentionFragment == null) {
            mAttentionFragment = new AttentionFragment();
            transaction.add(R.id.main_container, mAttentionFragment);
        }
        hideAllFragment(transaction);
        transaction.show(mAttentionFragment).commit();
    }


    private void initMessageFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
            transaction.add(R.id.main_container, mMessageFragment);
        }
        hideAllFragment(transaction);
        transaction.show(mMessageFragment).commit();
    }


    private void initMineFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            transaction.add(R.id.main_container, mMineFragment);
        }
        hideAllFragment(transaction);
        transaction.show(mMineFragment).commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mAttentionFragment != null) {
            transaction.hide(mAttentionFragment);
        }
        if (mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }
}
