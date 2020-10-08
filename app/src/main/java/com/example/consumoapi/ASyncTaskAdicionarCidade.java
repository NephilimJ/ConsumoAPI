package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consumoapi.RetrofitPackage.City;
import com.example.consumoapi.RetrofitPackage.Country;
import com.example.consumoapi.RetrofitPackage.State;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ASyncTaskAdicionarCidade extends AppCompatActivity {

    TextInputEditText edtPais, edtEstado, edtEstadoABV, edtCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_sync_task_adicionar_cidade);

        edtPais = findViewById(R.id.edtPaisNomeASYNC);
        edtEstado = findViewById(R.id.edtEstadoNomeASYNC);
        edtEstadoABV = findViewById(R.id.edtEstadoAbrevASYNC);
        edtCidade = findViewById(R.id.edtCidadeNomeASYNC);
    }

    public void cadastrarCidade(View view) {
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

        ASyncTaskPOST chamarASYNC = new ASyncTaskPOST(city);
        chamarASYNC.execute();
    }

    public class ASyncTaskPOST extends AsyncTask<String, String, String> {
        private ProgressDialog loading;
        private HttpURLConnection conn;
        private City json;

        public ASyncTaskPOST(City json) {
            this.json = json;
        }

        @Override
        protected void onPreExecute() {
            loading = new ProgressDialog(ASyncTaskAdicionarCidade.this, ProgressDialog.THEME_HOLO_DARK);
            loading.setMessage("Enviando Dados");
            loading.setTitle("Carregando");
            loading.setCancelable(false);
            loading.show();
        }

        protected String doInBackground(String... parms) {
            StringBuffer chaine = new StringBuffer("");
            try {
                URL url = new URL("LINK DA API");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoInput(true);

                OutputStreamWriter outputStream = new OutputStreamWriter(conn.getOutputStream());
                outputStream.write(new Gson().toJson(json));

                outputStream.flush();
                outputStream.close();

                InputStream inputStream = conn.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    chaine.append(line);
                }

                return chaine.toString();

            } catch (Exception e) {
                e.printStackTrace();
                loading.dismiss();
                Toast.makeText(ASyncTaskAdicionarCidade.this, "Erro ao Conectar com o Servidor", Toast.LENGTH_LONG).show();
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response != null) {
                loading.dismiss();
                finish();
            }
        }
    }
}