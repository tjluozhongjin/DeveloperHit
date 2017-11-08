package com.xiaojin.developerkit.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaojin.developerkit.R;

public class StoryDetailActivity extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        //左上角出现小箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wv= (WebView) findViewById(R.id.webView);

        wv.getSettings().setJavaScriptEnabled(true);

        Intent intent=getIntent();


        String url = intent.getStringExtra("url");

        wv.loadUrl(url);


        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {  //表示按返回键

                        wv.goBack();   //后退

                        //webview.goForward();//前进

                        return true;    //已处理
                    }
                }
                return false;
            }
        });



//        wv.setWebChromeClient(new WebChromeClient());


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        ZhiHuService service=retrofit.create(ZhiHuService.class);

//        service.getNewsDetails(url)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .map(new Func1<StoryDetailsEntity, String>() {
//                    @Override
//                    public String call(StoryDetailsEntity storyDetailsEntity) {
//                        return  HtmlUtils.structHtml(storyDetailsEntity);
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("error",e.toString());
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        //加载asset里的css
//
//                        wv.loadDataWithBaseURL("file:///android_asset/", s, "text/html", "UTF-8", null);
//                    }
//                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //点击小箭头返回
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
