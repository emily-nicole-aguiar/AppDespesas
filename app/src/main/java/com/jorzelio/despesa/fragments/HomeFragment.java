package com.jorzelio.despesa.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorzelio.despesa.R;
import com.jorzelio.despesa.adapter.AdapterDespesaCompartilhada;
import com.jorzelio.despesa.adapter.AdapterDespesaIndividual;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class HomeFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private TextView txtSaldo, txtSaldoIndividual, txtSaldoCompartilhado;
    private RecyclerView rDespesaIndividual, rDespesaCompartilhada;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        txtSaldo = view.findViewById(R.id.txt_saldo);
        txtSaldoIndividual = view.findViewById(R.id.txt_saldoIndividual);
        txtSaldoCompartilhado = view.findViewById(R.id.txt_saldoCompartilhado);
        rDespesaIndividual = view.findViewById(R.id.recycler_dpIndividual);
        rDespesaCompartilhada = view.findViewById(R.id.recycler_dpCompart);

        DatabaseReference despesaRef = reference.child("nome_despesa");
        despesaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        configuraCalendar();

        // Configurar adpter
        AdapterDespesaIndividual adapterIndividual = new AdapterDespesaIndividual();
        AdapterDespesaCompartilhada adapterCompartilhado = new AdapterDespesaCompartilhada();

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());

        rDespesaIndividual.setLayoutManager(layoutManager);
        rDespesaIndividual.setHasFixedSize(true);
        rDespesaIndividual.setAdapter(adapterIndividual);

        rDespesaCompartilhada.setLayoutManager(layoutManager1);
        rDespesaCompartilhada.setHasFixedSize(true);
        rDespesaCompartilhada.setAdapter(adapterCompartilhado);
        
        return view;
    }

    private void configuraCalendar() {
        CharSequence meses [] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2027, 0, 1))
                .commit();
    }
}