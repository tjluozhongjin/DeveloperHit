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

import com.xiaojin.developerkit.Model.ZhuHuModel;
import com.xiaojin.developerkit.R;
import com.xiaojin.developerkit.Service.ZhiHuService;
import com.xiaojin.developerkit.adapter.ZhiHuAdapter;
import com.xiaojin.developerkit.ui.activity.StoryDetailActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by luozhongjin on 03/11/2017.
 */

public class ZhiHuFragment extends Fragment {

    private ZhuHuModel cache = null;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        listView = (ListView) view.findViewById(R.id.zhiHuListView);

        if (cache == null) {
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://115.159.1.222:3001")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            ZhiHuService zhiHuService = retrofit.create(ZhiHuService.class);

            Observable<ZhuHuModel> rootEntityObservable = zhiHuService.getData();

            rootEntityObservable
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(new Subject<ZhuHuModel>() {
                        @Override
                        public boolean hasObservers() {
                            return false;
                        }

                        @Override
                        public boolean hasThrowable() {
                            return false;
                        }

                        @Override
                        public boolean hasComplete() {
                            return false;
                        }

                        @Override
                        public Throwable getThrowable() {
                            return null;
                        }

                        @Override
                        protected void subscribeActual(Observer<? super ZhuHuModel> observer) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(final ZhuHuModel zhuHuModel) {
                            cache = zhuHuModel;
                            listView.setAdapter(new ZhiHuAdapter(zhuHuModel.getQuestions(), getContext()));
                            //点击item跳转到详细页面
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                                    intent.putExtra("url", zhuHuModel.getQuestions().get(position).getUrl());
                                    startActivity(intent);
                                }


                            });
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            listView.setAdapter(new ZhiHuAdapter(cache.getQuestions(), getContext()));
            //点击item跳转到详细页面
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                    intent.putExtra("url", cache.getQuestions().get(position).getUrl());
                    startActivity(intent);
                }


            });
        }
        return view;
    }
}
