package com.example.consumoapi.RetrofitPackage;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("LINK DA API")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public GetService getGetService() {
        return this.retrofit.create(GetService.class);
    }

    public PostService getPostService() {
        return this.retrofit.create(PostService.class);
    }
}
