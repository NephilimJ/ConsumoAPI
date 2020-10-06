package com.example.consumoapi.RetrofitPackage;

import com.example.consumoapi.RetrofitPackage.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostService {

        @POST("city/name")
        Call<City> createCity(@Body City city);
}
