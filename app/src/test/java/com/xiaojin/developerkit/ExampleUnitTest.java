package com.xiaojin.developerkit;

import org.junit.Test;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
        System.out.println("hello");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.v2ex.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestService testService = retrofit.create(TestService.class);

        Call<List<com.xiaojin.developerkit.Test>> call = testService.getTest();

        call.enqueue(new Callback<List<com.xiaojin.developerkit.Test>>() {
            @Override
            public void onResponse(Response<List<com.xiaojin.developerkit.Test>> response, Retrofit retrofit) {
                System.out.println(response.body().get(0).getId());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("bad");
            }
        });
    }

    @Test
    public void my(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.v2ex.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestService testService = retrofit.create(TestService.class);

        Call<List<com.xiaojin.developerkit.Test>> call = testService.getTest();

        call.enqueue(new Callback<List<com.xiaojin.developerkit.Test>>() {
            @Override
            public void onResponse(Response<List<com.xiaojin.developerkit.Test>> response, Retrofit retrofit) {
                System.out.println("hello");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("bad");
            }
        });
    }
}