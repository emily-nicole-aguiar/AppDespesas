package com.jorzelio.despesa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jorzelio.despesa.R;

public class AdapterDespesaIndividual extends RecyclerView.Adapter<AdapterDespesaIndividual.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_despesas, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.data.setText("03\nOut");
        holder.titulo.setText("Netflix");
        holder.categoria.setText("Entretenimento");
        holder.valor.setText("R$ 39,90");

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView data;
        ImageView cifrao;
        TextView titulo;
        TextView categoria;
        TextView valor;

        public MyViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textAdapterData);
            cifrao = itemView.findViewById(R.id.imgCifrao);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
            valor = itemView.findViewById(R.id.textAdapterValor);
        }
    }
}
