package com.jorzelio.despesa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorzelio.despesa.R;
import com.jorzelio.despesa.adapter.AdapterAmigos;
import com.jorzelio.despesa.adapter.AdapterDespesaCompartilhada;
import com.jorzelio.despesa.adapter.AdapterDespesaIndividual;

public class AmigosFragment extends Fragment {

    private RecyclerView amigos_adicionados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);

        amigos_adicionados = view.findViewById(R.id.lista_amigos);

        // Configurar adpter
        AdapterAmigos adapter = new AdapterAmigos();

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        amigos_adicionados.setLayoutManager(layoutManager);
        amigos_adicionados.setHasFixedSize(true);
        amigos_adicionados.setAdapter(adapter);

        return view;
    }
}