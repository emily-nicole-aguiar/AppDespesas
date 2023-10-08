package com.jorzelio.despesa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jorzelio.despesa.R;
import com.jorzelio.despesa.adapter.AdapterDespesaCompartilhada;
import com.jorzelio.despesa.adapter.AdapterDespesaIndividual;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class DespesaList extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private TextView txtSaldo, txtSaldoIndividual, txtSaldoCompartilhado;
    private RecyclerView rDespesaIndividual, rDespesaCompartilhada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa_list);

        calendarView = findViewById(R.id.calendarView);
        txtSaldo = findViewById(R.id.txt_saldo);
        txtSaldoIndividual = findViewById(R.id.txt_saldoIndividual);
        txtSaldoCompartilhado = findViewById(R.id.txt_saldoCompartilhado);
        rDespesaIndividual = findViewById(R.id.recycler_dpIndividual);
        rDespesaCompartilhada = findViewById(R.id.recycler_dpCompart);

        configuraCalendar();

        // Configurar adpter
        AdapterDespesaIndividual adapterIndividual = new AdapterDespesaIndividual();
        AdapterDespesaCompartilhada adapterCompartilhado = new AdapterDespesaCompartilhada();

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);

        rDespesaIndividual.setLayoutManager(layoutManager);
        rDespesaIndividual.setHasFixedSize(true);
        rDespesaIndividual.setAdapter(adapterIndividual);

        rDespesaCompartilhada.setLayoutManager(layoutManager1);
        rDespesaCompartilhada.setHasFixedSize(true);
        rDespesaCompartilhada.setAdapter(adapterCompartilhado);
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
                .setMinimumDate(CalendarDay.from(2015, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .commit();
    }
}