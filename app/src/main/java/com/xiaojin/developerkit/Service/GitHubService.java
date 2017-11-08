package com.xiaojin.developerkit.Service;

import com.xiaojin.developerkit.Model.GitHubModel;

import io.reactivex.Observable;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit2.http.GET;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public interface GitHubService {

    @GET("/github")
    Observable<GitHubModel> getData();

    @retrofit.http.GET("/repos/{str1}/{str2}")
    rx.Observable<GitHubModel.Repository> getRepositoryData(@Path("str1") String st1,
                                                            @Path("str2") String str2, @Query("access_token") String token);

}
