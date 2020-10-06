package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.consumoapi.RetrofitPackage.ListViewAdapter;
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
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        refreshListView = findViewById(R.id.refresherDashboard);
        listView = findViewById(R.id.lista);

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

    private void callRetrofit() {
        Call<List<City>> call = new RetrofitConfig().getTesteService().buscarCity("list2");
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> teste = response.body();
                ListViewAdapter listViewAdapter = new ListViewAdapter(RetrofitTela.this, teste);
                listView.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(RetrofitTela.this, "Erro ao Conectar com o Servidor", Toast.LENGTH_LONG);
            }
        });

        refreshListView.setRefreshing(false);
    }
}