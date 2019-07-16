package com.example.supervideo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private View mContentView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView=getActivity().getLayoutInflater().inflate(getLayoutId(),null);
        initView();
        initData();
        return mContentView;
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int getLayoutId();
    protected <T extends View> T bindViewId(int resId){
        return (T)mContentView.findViewById(resId);
    }

}
