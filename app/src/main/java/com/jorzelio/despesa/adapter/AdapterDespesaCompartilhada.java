package com.jorzelio.despesa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jorzelio.despesa.R;

public class AdapterDespesaCompartilhada extends RecyclerView.Adapter<AdapterDespesaCompartilhada.MinhaViewHolder> {

    @Override
    public MinhaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_despesas, parent, false);

        return new AdapterDespesaCompartilhada.MinhaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MinhaViewHolder holder, int position) {
        holder.data.setText("03\nOut");
        holder.titulo.setText("Geladeira");
        holder.categoria.setText("Casa");
        holder.valor.setText("R$ 119,90");

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder{
        TextView data;
        ImageView cifrao;
        TextView titulo;
        TextView categoria;
        TextView valor;

        public MinhaViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textAdapterData);
            cifrao = itemView.findViewById(R.id.imgCifrao);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
            valor = itemView.findViewById(R.id.textAdapterValor);
        }
    }
}
