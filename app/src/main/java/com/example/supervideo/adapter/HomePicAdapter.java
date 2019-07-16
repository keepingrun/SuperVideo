package com.example.supervideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supervideo.R;

public class HomePicAdapter extends PagerAdapter {
    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;

    //每张轮播图片的描述文字
    private int[] mDes=new int[]{
            R.string.a_name,
            R.string.b_name,
            R.string.c_name,
            R.string.d_name,
            R.string.e_name,
    };

    private int[] mImg=new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
    };

    public HomePicAdapter(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_pic_item,null);
        mImageView=view.findViewById(R.id.iv_img);
        mTextView=view.findViewById(R.id.tv_dec);
        mImageView.setImageResource(mImg[position]);
        mTextView.setText(mDes[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
