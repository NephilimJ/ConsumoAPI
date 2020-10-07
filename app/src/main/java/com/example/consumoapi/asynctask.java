package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.consumoapi.RetrofitPackage.City;
import com.example.consumoapi.RetrofitPackage.ListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class asynctask extends AppCompatActivity {

    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        listView = findViewById(R.id.listaASYNC);
        swipeRefreshLayout = findViewById(R.id.refresherListASYNC);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        chamarAsync();
                    }
                }
        );

        chamarAsync();
    }

    private void chamarAsync(){
        ASyncTaskGET chamarAsync = new ASyncTaskGET();
        chamarAsync.execute();
    }

    public class ASyncTaskGET extends AsyncTask<String, String, String> {
        private ProgressDialog loading;
        private HttpURLConnection conn;

        public ASyncTaskGET() {
        }

        @Override
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
        }

        protected String doInBackground(String... parms) {
            StringBuffer chaine = new StringBuffer("");
            try {
                URL url = new URL("http://icityapp.hopto.org:8101/ws/city/list2");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                InputStream inputStream = conn.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    chaine.append(line);
                }

                return chaine.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response != null) {
                List<City> list = (List<City>) fromJson(response, new TypeToken<List<City>>() {
                }.getType());
                ListViewAdapter listViewAdapter = new ListViewAdapter(asynctask.this, list);
                listView.setAdapter(listViewAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

}