package com.example.supervideo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.supervideo.base.BaseFragment;
import com.example.supervideo.adapter.HomePicAdapter;
import com.example.supervideo.model.Channel;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;

/**
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment {
    //轮播viewpager
    private LoopViewPager mLoopViewPager;
    //轮播小圆点
    private CircleIndicator mCircleIndicator;
    // 九宫布局
    private GridView mGridView;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mLoopViewPager=bindViewId(R.id.viewpager);
        mCircleIndicator=bindViewId(R.id.indicator);

        mLoopViewPager.setAdapter(new HomePicAdapter(getActivity()));
        mLoopViewPager.setLooperPic(true);//5s自动轮播
        mCircleIndicator.setViewPager(mLoopViewPager);//圆点和轮播绑定

        //九宫布局
        mGridView=bindViewId(R.id.gv_channel);
        mGridView.setAdapter(new ChannelAdapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("tag","num:"+i);//0---8
                switch(i){
                    case Channel.LIVE://6=7-1
                        break;
                    case Channel.FAVORITE://7=8-1
                        break;
                    case Channel.HISTORY://8=9-1
                        break;
                        default:
                            //跳转到对应的频道
                            break;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    private class ChannelAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Channel.MAX_COUNT;//9
        }

        @Override
        public Object getItem(int i) {
            return new Channel(i+1,getActivity());
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //获取加载子项
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            Channel channel= (Channel) getItem(i);
            ViewHolder viewHolder=null;
            if(convertView==null){
                convertView= LayoutInflater.from(getActivity()).inflate(R.layout.home_grid_item,null);
                viewHolder=new ViewHolder();
                viewHolder.imageView=convertView.findViewById(R.id.iv_home_item_img);
                viewHolder.textView=convertView.findViewById(R.id.tv_home_item_text);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(channel.getChannelName());
            int id = channel.getChannelId();
            int imgResId = -1;
            switch (id) {
                case Channel.SHOW:
                    imgResId = R.drawable.ic_show;
                    break;
                case Channel.MOVIE:
                    imgResId = R.drawable.ic_movie;
                    break;
                case Channel.COMIC:
                    imgResId = R.drawable.ic_comic;
                    break;
                case Channel.DOCUMENTRY:
                    imgResId = R.drawable.ic_movie;
                    break;
                case Channel.MUSIC:
                    imgResId = R.drawable.ic_music;
                    break;
                case Channel.VARIETY:
                    imgResId = R.drawable.ic_variety;
                    break;
                case Channel.LIVE:
                    imgResId = R.drawable.ic_live;
                    break;
                case Channel.FAVORITE:
                    imgResId = R.drawable.ic_bookmark;
                    break;
                case Channel.HISTORY:
                    imgResId = R.drawable.ic_history;
                    break;
            }

            viewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(imgResId));
            return convertView;
        }
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
