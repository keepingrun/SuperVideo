package com.example.supervideo;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.supervideo.base.BaseFragment;

public class AboutFragment extends BaseFragment {
    private TextView mTextView;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTextView=bindViewId(R.id.tv_app_des);
        //识别textview里的链接可点击
        mTextView.setAutoLinkMask(Linkify.ALL);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());//文字可以滚动
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
