package com.xiaojin.developerkit;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public interface TestService {

    @GET("/api/topics/hot.json")
    Call<TestList> getTest();


}
