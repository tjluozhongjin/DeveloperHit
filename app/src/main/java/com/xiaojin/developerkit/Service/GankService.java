package com.xiaojin.developerkit.Service;

import com.xiaojin.developerkit.Model.GankModel;



import retrofit.http.GET;
import rx.Observable;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public interface GankService {

    @GET("/api/data/all/20/1")
    Observable<GankModel> getData();

}
