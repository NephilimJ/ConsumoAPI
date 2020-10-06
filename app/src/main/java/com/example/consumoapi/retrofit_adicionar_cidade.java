package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.consumoapi.RetrofitPackage.Country;
import com.example.consumoapi.RetrofitPackage.City;
import com.example.consumoapi.RetrofitPackage.PostService;
import com.example.consumoapi.RetrofitPackage.RetrofitConfig;
import com.example.consumoapi.RetrofitPackage.State;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class retrofit_adicionar_cidade extends AppCompatActivity {

    EditText edtPais, edtEstado, edtEstadoABV, edtCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_adicionar_cidade);

        edtPais = findViewById(R.id.edtPaisNome);
        edtEstado = findViewById(R.id.edtEstadoNome);
        edtEstadoABV= findViewById(R.id.edtEstadoAbrev);
        edtCidade = findViewById(R.id.edtCidadeNome);

    }

    public void cadastrarCidade(View view){
        String pais = edtPais.getText().toString();
        String estado = edtEstado.getText().toString();
        String estadoABV = edtEstadoABV.getText().toString();
        String cidade = edtCidade.getText().toString();

        Country country = new Country();
        country.setName(pais);
        country.setId(0);

        State state = new State();
        state.setId(0);
        state.setCountry(country);
        state.setLongName(estado);
        state.setShortName(estadoABV);

        City city = new City();
        city.setId(0);
        city.setName(cidade);
        city.setState(state);

        callRetrofit(city);
    }

    private void callRetrofit(City city){

        Call<City> call = new RetrofitConfig().getPOST().createCity(city);
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                Log.e("Teste", "sucesso");
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.e("Teste", "falha, "+t.getMessage());
            }
        });
    }
}
