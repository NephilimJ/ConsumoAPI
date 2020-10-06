package com.example.consumoapi.RetrofitPackage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TesteService {

    @GET("city/{id}")
    Call<List<City>> buscarCity(@Path("id") String id);

}
