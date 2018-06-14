package com.laisontech.mydouvoice.fragment.home;

import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laisontech.mydouvoice.R;
import com.laisontech.mydouvoice.base.BaseFragment;


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
 * Created by SDP on 2018/6/14.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private ImageView ivCut;
    private TextView tvRecommend;
    private TextView tvNear;
    private ImageView ivSearch;
    private RecommendFragment rf;
    private NearFragment nf;

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        ivCut = view.findViewById(R.id.iv_cut_record_video);
        tvRecommend = view.findViewById(R.id.tv_recommend);
        tvNear = view.findViewById(R.id.tv_near);
        ivSearch = view.findViewById(R.id.iv_search);
    }


    @Override
    protected void initEvent() {
        ivCut.setOnClickListener(this);
        tvRecommend.setOnClickListener(this);
        tvNear.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        initRecommendFragment();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cut_record_video:

                break;
            case R.id.tv_recommend:
                setSelectRecommendOrNear(tvRecommend, tvNear);
                initRecommendFragment();
                break;
            case R.id.tv_near:
                setSelectRecommendOrNear(tvNear, tvRecommend);
                initNearFragment();
                break;
            case R.id.iv_search:

                break;
        }
    }

    private void setSelectRecommendOrNear(TextView tv1, TextView tv2) {
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        tv1.setTextColor(getResources().getColor(android.R.color.white));
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        tv2.setTextColor(getResources().getColor(R.color.colorTextUnSelect));
    }

    //显示第一个fragment
    private void initRecommendFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (rf == null) {
            rf = new RecommendFragment();
            ft.add(R.id.home_container, rf);
        }
        hideFragment(ft);
        ft.show(rf);
        ft.commit();
    }

    //显示第2个fragment
    private void initNearFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (nf == null) {
            nf = new NearFragment();
            ft.add(R.id.home_container, nf);
        }
        hideFragment(ft);
        ft.show(nf);
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (rf != null) {
            ft.hide(rf);
        }
        if (nf != null) {
            ft.hide(nf);
        }
    }
}
