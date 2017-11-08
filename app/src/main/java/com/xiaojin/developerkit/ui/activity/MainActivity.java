package com.xiaojin.developerkit.ui.activity;



import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.xiaojin.developerkit.adapter.PagerAdapter;
import com.xiaojin.developerkit.R;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerSlidingTabStrip tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        String url = "https://api.github.com/repos/seclab-ucr/INTANG?access_token=725223c65cc5b0f75ff02b005b791cfdf5ed27b6";
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        okhttp3.Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response.code());
//            }
//        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        TestService testService = retrofit.create(TestService.class);
//
//        retrofit.Call<TestModel> repositoryObservable = testService.getRepositoryData();
//
//        repositoryObservable.enqueue(new retrofit.Callback<TestModel>() {
//            @Override
//            public void onResponse(retrofit.Response<TestModel> response, Retrofit retrofit) {
//                System.out.println("yes");
//                System.out.println(response.code());
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });




//
////
//        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                .baseUrl("http://115.159.1.222:3001")
//                .addConverterFactory(SimpleXmlConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        ZhiHuService zhiHuService = retrofit.create(ZhiHuService.class);
////
//////
//        Observable<ZhuHuModel> call = zhiHuService.getData();
//
//
//
////
//
//        call.enqueue(new Callback<ZhuHuModel>() {
//            @Override
//            public void onResponse(Call<ZhuHuModel> call, Response<ZhuHuModel> response) {
//                System.out.println(response.body().getQuestions().get(0).getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<ZhuHuModel> call, Throwable t) {
//                System.out.println("bad");
//                System.out.println(t.getMessage());
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager= (ViewPager) findViewById(R.id.pager);
        tab= (PagerSlidingTabStrip) findViewById(R.id.tab);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tab.setViewPager(pager);
    }
}
