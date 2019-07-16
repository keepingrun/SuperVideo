package com.example.supervideo;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.supervideo.base.BaseActivity;
import com.example.supervideo.base.BaseFragment;


public class HomeActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActiontBarDrawerToggle;

    private FragmentManager mFragmentManager;
    private BaseFragment mCurrentFragment;

    private MenuItem mPreItem;
    @Override
    protected void initView() {
        setActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");

        mDrawerLayout=bindViewId(R.id.drawer_layout);
        mNavigationView=bindViewId(R.id.navigation_view);
        //Actionbar和DrawerLayout绑定
        mActiontBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.drawer_open,R.string.drawer_close);
        //同步状态
        mActiontBarDrawerToggle.syncState();
        //设置监听
        mDrawerLayout.addDrawerListener(mActiontBarDrawerToggle);
        //侧拉菜单NavigationView监听
        mPreItem=mNavigationView.getMenu().getItem(0);
        //可选状态
        mPreItem.setCheckable(true);
        //打开HomeActivity时就加载HomeFragment
        initFragment();
        handleNavigationViewItem();
    }

    private void initFragment() {
        mCurrentFragment=FragmentManagerWrapper.getmInstance().createFragment(HomeFragment.class,true);
        mFragmentManager=getSupportFragmentManager();

        //事务的回滚
        mFragmentManager.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commit();//后者替换前者id指定的布局

    }

    private void handleNavigationViewItem() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(mPreItem!=null){
                    mPreItem.setCheckable(false);
                }
                switch(menuItem.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        mToolBar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        mToolBar.setTitle(R.string.blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(AboutFragment.class);
                        mToolBar.setTitle(R.string.about_title);
                        break;
                }
                //关闭DrawerLayout
                mDrawerLayout.closeDrawer(Gravity.START);
                //跳转完
                mPreItem=menuItem;
                menuItem.setCheckable(true);
                return false;
            }
        });

    }

    private void switchFragment(Class<?> clazz) {
        BaseFragment fragment=FragmentManagerWrapper.getmInstance().createFragment(clazz);
        if (fragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
        }else{
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content,fragment).commitAllowingStateLoss();
        }
        mCurrentFragment=fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }
}
