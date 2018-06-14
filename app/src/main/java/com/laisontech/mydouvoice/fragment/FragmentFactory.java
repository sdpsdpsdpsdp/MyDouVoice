package com.laisontech.mydouvoice.fragment;

import android.support.v4.app.Fragment;

import com.laisontech.mydouvoice.fragment.home.HomeFragment;
import com.laisontech.mydouvoice.fragment.home.NearFragment;
import com.laisontech.mydouvoice.fragment.home.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDP on 2017/8/15.
 * 碎片工厂
 */

public class FragmentFactory {


    private HomeFragment mHomeFragment;
    private AttentionFragment mAttentionFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private HomeFragment getHomeFragment() {
        if (mHomeFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
            }
        }
        return mHomeFragment;
    }

    private AttentionFragment getAttentionFragment() {
        if (mAttentionFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mAttentionFragment == null) {
                    mAttentionFragment = new AttentionFragment();
                }
            }
        }
        return mAttentionFragment;
    }

    private MessageFragment getMessageFragment() {
        if (mMessageFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                }
            }
        }
        return mMessageFragment;
    }

    private MineFragment getMineFragment() {
        if (mMineFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                }
            }
        }
        return mMineFragment;
    }


    //获取新碎片集合
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(getHomeFragment());
        fragments.add(getAttentionFragment());
        fragments.add(getMessageFragment());
        fragments.add(getMineFragment());
        return fragments;
    }


    //获取首页推荐和附近的碎片
    private RecommendFragment mRecommendFragment;
    private NearFragment mNearFragment;

    private RecommendFragment getRecommendFragment() {
        if (mRecommendFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mRecommendFragment == null) {
                    mRecommendFragment = new RecommendFragment();
                }
            }
        }
        return mRecommendFragment;
    }

    private NearFragment getNearFragment() {
        if (mNearFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mNearFragment == null) {
                    mNearFragment = new NearFragment();
                }
            }
        }
        return mNearFragment;
    }
    //获取新碎片集合
    public List<Fragment> getHomeChildFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(getRecommendFragment());
        fragments.add(getNearFragment());
        return fragments;
    }
}
