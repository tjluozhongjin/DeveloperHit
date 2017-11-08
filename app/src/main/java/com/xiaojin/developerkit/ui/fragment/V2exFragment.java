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
import com.xiaojin.developerkit.Model.V2exModel;
import com.xiaojin.developerkit.R;
import com.xiaojin.developerkit.Service.V2exService;
import com.xiaojin.developerkit.adapter.V2exAdapter;
import com.xiaojin.developerkit.ui.activity.StoryDetailActivity;
import java.util.ArrayList;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class V2exFragment extends Fragment {


    private static ArrayList<V2exModel> cache = null;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_zhihu,container,false);
        listView = (ListView) view.findViewById(R.id.zhiHuListView);

        if(cache == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.v2ex.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            V2exService v2exService = retrofit.create(V2exService.class);

            rx.Observable<ArrayList<V2exModel>> rootEntityObservable = v2exService.getData();

            rootEntityObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<ArrayList<V2exModel>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(final ArrayList<V2exModel> storiesEntities) {
                            cache = storiesEntities;
                            listView.setAdapter(new V2exAdapter(storiesEntities,getContext()));
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
        }else {
            listView.setAdapter(new V2exAdapter(cache,getContext()));
            //点击item跳转到详细页面
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getActivity(),StoryDetailActivity.class);
                    intent.putExtra("url",cache.get(position).getUrl());
                    startActivity(intent);
                }


            });
        }

        return view;
    }

}
