package com.laisontech.mydouvoice.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

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

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(this.getResLayoutId(), container, false);
        return view;
    }

    @LayoutRes
    protected abstract int getResLayoutId();

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        this.initData();
        this.initEvent();
    }

    protected void initViews(View view) {

    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    protected void openActivity(String serializableName, Serializable serializable, Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtra(serializableName, serializable);
        this.startActivity(intent);
    }

    protected void openActivity(Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        this.startActivity(intent);
    }

    protected void openActivity(String key, boolean values, Class<?> clz) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtra(key, values);
        this.startActivity(intent);
    }

    protected void openActivityForResult(Class<?> clz, int requestCode) {
        Intent intent = new Intent(this.getContext(), clz);
        this.startActivityForResult(intent, requestCode);
    }

    protected void openActivityForResult(Bundle bundle, Class<?> clz, int requestCode) {
        Intent intent = new Intent(this.getContext(), clz);
        intent.putExtras(bundle);
        this.startActivityForResult(intent, requestCode);
    }


    protected String getResStr(int resId) {
        return this.getResources().getString(resId);
    }

}
