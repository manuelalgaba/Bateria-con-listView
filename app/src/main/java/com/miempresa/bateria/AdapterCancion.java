package com.miempresa.bateria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterCancion extends BaseAdapter {
    Context context;
    List<Cancion> canciones;

    public AdapterCancion(Context context, List<Cancion> canciones) {
        this.context = context;
        this.canciones = canciones;
    }

    @Override
    public int getCount() {
        return canciones.size();
    }

    @Override
    public Object getItem(int position) {
        return canciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_item, viewGroup, false); // Corrección: se agrega viewGroup y false
        }

        TextView textViewTitulo = view.findViewById(R.id.txtNombreCancion);
        Cancion cancion = canciones.get(position);
        textViewTitulo.setText(cancion.getNombre());

        return view;
    }
}