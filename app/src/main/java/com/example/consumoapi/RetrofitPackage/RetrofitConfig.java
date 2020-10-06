package com.example.consumoapi.RetrofitPackage;

import com.example.consumoapi.RetrofitTela;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://icityapp.hopto.org:8101/ws/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public TesteService getTesteService() {
        return this.retrofit.create(TesteService.class);
    }

    public PostService getPOST() {
        return this.retrofit.create(PostService.class);
    }
}
