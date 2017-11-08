package com.xiaojin.developerkit.Service;

import com.xiaojin.developerkit.Model.V2exModel;

import java.util.ArrayList;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public interface V2exService {

    @GET("/api/topics/hot.json")
    Observable<ArrayList<V2exModel>> getData();

}
