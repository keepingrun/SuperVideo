package com.example.supervideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> mList=new ArrayList<>();
    private ImageView[] mDotsList;
    //拿到第三个view页面，为里面的“立即体验按钮”设置点击事件
    private View view;
    private ImageView iv_start;
    //通过父布局获取里面的三个圆点ImageView
    private LinearLayout dots_LinearLayout;
    //圆点的位置
    private int mLastPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDots();

    }

    private void initView() {
        //创建3个页面
        LayoutInflater inflater=LayoutInflater.from(this);
        mList.add(inflater.inflate(R.layout.guide_one_layout,null));
        mList.add(inflater.inflate(R.layout.guide_two_layout,null));
        mList.add(inflater.inflate(R.layout.guide_three_layout,null));
        viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(new GuideAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setCurrentDotPosition(i);
                if(i==mList.size()-1){
                    ImageView imageView = mList.get(i).findViewById(R.id.iv_start);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(GuideActivity.this,HomeActivity.class));
                            getSharedPreferences("config",0).edit().putBoolean("isFirst",false).apply();
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initDots() {
        //找到父布局
        dots_LinearLayout=findViewById(R.id.ll_dots_layout);
        mDotsList=new ImageView[mList.size()];
        for(int i=0;i<mList.size();i++){
            mDotsList[i]= (ImageView) dots_LinearLayout.getChildAt(i);
            mDotsList[i].setEnabled(false);
        }
        mLastPosition=0;
        mDotsList[0].setEnabled(true);
    }
    private void setCurrentDotPosition(int position){
        mDotsList[position].setEnabled(true);
        mDotsList[mLastPosition].setEnabled(false);
        mLastPosition=position;
    }
    class GuideAdapter extends PagerAdapter{
        /**
         * 通过该函数来显示view
         * @param container
         * @param position
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    };
}
