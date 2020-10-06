package com.example.consumoapi.RetrofitPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.consumoapi.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<City> lista;

    private LayoutInflater inflater = null;

    public ListViewAdapter(Context context, List<City> lista) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.lista = lista;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lista.size();
    }

    @Override
    public City getItem(int position) {
        // TODO Auto-generated method stub
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.activity_retrofit, null);

        City pedido = lista.get(position);



        return vi;
    }
}
