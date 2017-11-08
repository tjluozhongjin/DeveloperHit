package com.xiaojin.developerkit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaojin.developerkit.Model.GankModel;
import com.xiaojin.developerkit.R;
import com.xiaojin.developerkit.Service.GankService;
import com.xiaojin.developerkit.adapter.GankAdapter;
import com.xiaojin.developerkit.ui.activity.StoryDetailActivity;

import java.util.ArrayList;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class GankFragment extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_zhihu,container,false);
        listView = (ListView) view.findViewById(R.id.zhiHuListView);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GankService v2exService = retrofit.create(GankService.class);

        rx.Observable<GankModel> rootEntityObservable = v2exService.getData();

        rootEntityObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankModel, ArrayList<GankModel.Result>>() {
                    @Override
                    public ArrayList<GankModel.Result> call(GankModel gankModel) {
                        return gankModel.getResultArrayList();
                    }
                })
                .subscribe(new Subscriber<ArrayList<GankModel.Result>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final ArrayList<GankModel.Result> storiesEntities) {
                        listView.setAdapter(new GankAdapter(storiesEntities,getContext()));
                        //点击item跳转到详细页面
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(getActivity(),StoryDetailActivity.class);
                                intent.putExtra("url",storiesEntities.get(position).getUrl());
                                startActivity(intent);
                            }


                        });

                    }
                });

        return view;
    }

}