package com.xiaojin.developerkit.Service;

import com.xiaojin.developerkit.Model.ZhuHuModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public interface ZhiHuService {

    @GET("/topic")
    Observable<ZhuHuModel> getData();


}
