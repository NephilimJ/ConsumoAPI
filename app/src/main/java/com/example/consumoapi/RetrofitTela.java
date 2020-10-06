package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.consumoapi.RetrofitPackage.RetrofitConfig;
import com.example.consumoapi.RetrofitPackage.City;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTela extends AppCompatActivity {

    SwipeRefreshLayout refreshListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        refreshListView = findViewById(R.id.refresherDashboard);
        refreshListView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        refreshListView.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        callRetrofit();
                    }
                }
        );

        callRetrofit();
    }

    private void callRetrofit(){
        Call<List<City>> call = new RetrofitConfig().getTesteService().buscarCity("list2");
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> teste = response.body();
                Log.e("Teste","Teste: "+teste.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.e("Teste", "Erro ao buscar o cep:" + t.getMessage());
            }
        });
    }
}