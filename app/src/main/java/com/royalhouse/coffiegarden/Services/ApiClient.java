package com.royalhouse.coffiegarden.Services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {
    @GET("4k4qyzyAv")
    Call<String> dataClient ();

    @GET("4ksTWQyCP")
    Call<String> dataProduk ();

    @GET("VJVzHQx0w")
    Call<String> dataBanner ();

    @GET("VJ2c8Vl0w")
    Call<String> dataProdukList ();

    @POST("login")
    Call<String> mlogin (@Body String body);

}
