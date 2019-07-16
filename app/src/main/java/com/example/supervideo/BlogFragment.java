package com.example.supervideo;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.supervideo.base.BaseFragment;

public class BlogFragment extends BaseFragment {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private static final  int MAX_VALUE=100;
    //需要网络访问权限
    private static final String BLOG_URL="https://blog.csdn.net/qq_42403295";
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mWebView=bindViewId(R.id.webview);
        mProgressBar=bindViewId(R.id.pb_progress);
        WebSettings settings=mWebView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(BLOG_URL);
        mWebView.setWebChromeClient(mWebChromeClient);

    }
    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if(newProgress==MAX_VALUE){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }
}
