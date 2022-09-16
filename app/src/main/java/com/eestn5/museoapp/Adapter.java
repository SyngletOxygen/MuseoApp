package com.eestn5.museoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Puntos> {
    Context context;
    List<Puntos> comentarios;
    public Adapter(@NonNull Context context, List<Puntos> comentarios) {
        super(context, R.layout.comentarios,comentarios);
        this.context=context;
        this.comentarios=comentarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comentarios,null,true);
        TextView txtid,txtcomentario,txtfecha,txtopinion;

        txtid=view.findViewById(R.id.txtid);
        txtcomentario=view.findViewById(R.id.txtDescripcion);
        txtfecha=view.findViewById(R.id.txtTitulo);
        txtopinion=view.findViewById(R.id.txtOrden);

        txtid.setText(comentarios.get(position).getId());
        txtcomentario.setText(comentarios.get(position).getComentario());
        txtfecha.setText(comentarios.get(position).getTitulo());
        txtopinion.setText(comentarios.get(position).getOrden());

        return super.getView(position,convertView,parent);
    }
}
