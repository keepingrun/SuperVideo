package com.example.supervideo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.supervideo.R;


public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载指定布局
        setContentView(getLayoutId());

        initView();

        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    //返回要加载的指定布局id
    protected abstract int getLayoutId();

    //findViewById()  <T extends View>是类型
    protected <T extends View> T bindViewId(int resId){
        return (T)findViewById(resId);
    }
    //选择性使用ToolBar
    protected void setActionBar(){
        this.mToolBar =bindViewId(R.id.toolbar);
        if(this.mToolBar !=null){
            setSupportActionBar(mToolBar);
        }
    }
    //利用Toolbar的函数设置导航图标
    protected void setActionBarIcon(int resId){
        if(this.mToolBar !=null){
            mToolBar.setNavigationIcon(resId);
        }
    }
}
