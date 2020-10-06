package com.example.consumoapi.RetrofitPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter {
    Context context;
    List<PedidoClass> lista;

    private LayoutInflater inflater = null;

    public AdapterListAtividades(Context context, List<PedidoClass> lista) {
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
    public PedidoClass getItem(int position) {
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
            vi = inflater.inflate(R.layout.activities_cell, null);

        PedidoClass pedido = lista.get(position);

        TextView txtAssunto = (TextView) vi.findViewById(R.id.txtAssunto);
        txtAssunto.setText(new Function().cropText(pedido.getAssunto(), 30));

        TextView txtData = (TextView) vi.findViewById(R.id.txtData);
        txtData.setText(new Function().dataFormatterDB(pedido.getDataHora(), 1));

        TextView txtPrioridade = (TextView) vi.findViewById(R.id.txtPrioridade);
        String prioridadeStr = new FuncActivities().getPriorityStr(pedido.getPrioridade(), context);
        txtPrioridade.setText(prioridadeStr);
        txtPrioridade.setTextColor(new Function().getPriorityColor(pedido.getPrioridade()));

        TextView txtAutor = (TextView) vi.findViewById(R.id.txtAutor);
        txtAutor.setText(pedido.getUsuario_nome());

        TextView txtResponsavel = (TextView) vi.findViewById(R.id.txtResponsavel);
        if (!pedido.getTi_nome().equals("")) {
            txtResponsavel.setText(pedido.getTi_nome());
            txtResponsavel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            txtResponsavel.setText(" " + context.getString(R.string.txtNotAssigned));
            txtResponsavel.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.presence_away, 0, 0, 0);
        }

        LinearLayout lnlPriority = (LinearLayout) vi.findViewById(R.id.lnlPriority);
        lnlPriority.setBackgroundColor(new Function().getPriorityColor(pedido.getPrioridade()));

        TextView txtTipoAtividade = (TextView) vi.findViewById(R.id.txtTipoAtividade);
        txtTipoAtividade.setText(new FuncActivities().getTipoSuporteStr(pedido.getTipo(), context));

        TextView txtDeadline = (TextView) vi.findViewById(R.id.txtDeadline);
        txtDeadline.setText(new Function().dataFormatterDB(pedido.getDataFinal(), 2));

        RoundedCornerImageView imgFotoResponsavel = (RoundedCornerImageView) vi.findViewById(R.id.imgFotoResponsavel);
        imgFotoResponsavel.setImageResource(new Function().getActivityStatusImage(pedido.getStatus()));
        imgFotoResponsavel.setRoundedCornerColor(new Function().getPriorityColor(pedido.getPrioridade()));

        return vi;
    }
}
