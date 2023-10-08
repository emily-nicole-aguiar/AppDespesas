package com.jorzelio.despesa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jorzelio.despesa.R;

public class AdapterAmigos extends RecyclerView.Adapter<AdapterAmigos.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_amigos, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.amigo.setText("Emily");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView amigo;

        public MyViewHolder(View itemView) {
            super(itemView);
            amigo = itemView.findViewById(R.id.adapterAmigo);
        }
    }
}
