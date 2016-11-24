package com.example.anthony.farmaciacliente;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anthony on 24/11/16.
 */

public class myAdapter extends ArrayAdapter<Medicina> {

    private final Context context;
    private final ArrayList<Medicina> itemsArrayList;

    public myAdapter(Context context, ArrayList<Medicina> itemsArrayList) {

        super(context, R.layout.list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, View converterView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.list_item, parent, false);

        TextView nombre = (TextView)item.findViewById(R.id.nameText);
        TextView descripcion = (TextView)item.findViewById(R.id.descripcionItem);
        TextView cantidad = (TextView)item.findViewById(R.id.cantidadItem);
        TextView precio = (TextView)item.findViewById(R.id.precioItem);

        nombre.setText(context.getString(R.string.NombreMedicamen) + " " +
                itemsArrayList.get(position).getNombreMedicamento());
        descripcion.setText(context.getString(R.string.DescripcionMedicamento) + " " +
                itemsArrayList.get(position).getDescripcion());
        String cant = Integer.toString(itemsArrayList.get(position).getCantidad());
        cantidad.setText(context.getString(R.string.CantidadMedicamento) + " " +
                cant);
        precio.setText(context.getString(R.string.PrecioMedicamento) + " " +
                itemsArrayList.get(position).getPrecioUnidad());

        return item;
    }
}
