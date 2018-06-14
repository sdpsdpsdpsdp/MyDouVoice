package com.laisontech.mydouvoice.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laisontech.mydouvoice.R;

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
 * 选中的TextView
 */

public class CustomerTextView extends LinearLayout {
    private View bottomIv;
    private TextView topTv;
    private int mShowView = 1;    //默认不选中
    private Context mContext;

    public CustomerTextView(Context context) {
        this(context, null);
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomerTextView);
        String topTxt = ta.getString(R.styleable.CustomerTextView_topText);
        mShowView = ta.getInteger(R.styleable.CustomerTextView_showBottomView, mShowView);
        ta.recycle();

        View view = View.inflate(context, R.layout.touch_textview, this);
        topTv = view.findViewById(R.id.top_tv);
        bottomIv = view.findViewById(R.id.bottom_view);
        topTv.setText(topTxt);
        if (mShowView == 1) {
            setUnSelected();
        } else {
            setSelected();
        }
    }

    private void setTextSize(int size) {
        topTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    private void setTextColor(int color) {
        topTv.setTextColor(mContext.getResources().getColor(color));
    }

    public void setSelected() {
        setTextSize(16);
        setTextColor(android.R.color.white);
        bottomIv.setVisibility(VISIBLE);
    }

    public void setUnSelected() {
        setTextSize(16);
        setTextColor(R.color.colorTextUnSelect);
        bottomIv.setVisibility(INVISIBLE);
    }
}
