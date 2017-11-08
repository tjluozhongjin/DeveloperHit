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

import com.xiaojin.developerkit.Model.GitHubModel;
import com.xiaojin.developerkit.R;
import com.xiaojin.developerkit.Service.GitHubService;
import com.xiaojin.developerkit.adapter.GitHubAdapter;
import com.xiaojin.developerkit.ui.activity.StoryDetailActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;



/**
 * Created by luozhongjin on 04/11/2017.
 */

public class GitHubFragment extends Fragment {

    private static GitHubModel cache = null;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        listView = (ListView) view.findViewById(R.id.zhiHuListView);


        if(cache == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://115.159.1.222:8000")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            GitHubService gitHubService = retrofit.create(GitHubService.class);

            Observable<GitHubModel> rootEntityObservable = gitHubService.getData();

            rootEntityObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subject<GitHubModel>() {
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
                        protected void subscribeActual(Observer<? super GitHubModel> observer) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(final GitHubModel gitHubModel) {
                            cache = gitHubModel;
                            listView.setAdapter(new GitHubAdapter(gitHubModel.getProjects(),getContext()));
                            //点击item跳转到详细页面
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent=new Intent(getActivity(),StoryDetailActivity.class);
                                    intent.putExtra("url",gitHubModel.getProjects().get(position).getUrl());
                                    startActivity(intent);
                                }


                            });

                            System.out.println(gitHubModel.getProjects().get(1).getDescriptions());
                            //System.out.println(gitHubModel.getProjects().get(1).getContributors().get(0).getAvatar());
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {
            listView.setAdapter(new GitHubAdapter(cache.getProjects(),getContext()));
            //点击item跳转到详细页面
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getActivity(),StoryDetailActivity.class);
                    intent.putExtra("url",cache.getProjects().get(position).getUrl());
                    startActivity(intent);
                }


            });
        }


        return view;
    }
}
