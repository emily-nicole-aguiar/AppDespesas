package com.jorzelio.despesa.fragments;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorzelio.despesa.R;
import com.jorzelio.despesa.model.ExpenseModel;
import com.jorzelio.despesa.notification.NotificationReceiver;

import java.util.Calendar;

public class AddDespesaFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 123;

    private TextView selecionaData;
    private EditText nomeDespesa;
    private RadioGroup radioGroup;
    private Calendar calendar;
    private Button salvar;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_despesa, container, false);

        selecionaData = view.findViewById(R.id.txtSelecionaData);
        nomeDespesa = view.findViewById(R.id.edtInformaDespesa);
        radioGroup = view.findViewById(R.id.radioGroup);
        salvar = view.findViewById(R.id.btSalvarDespesa);

        if(ContextCompat.checkSelfPermission(AddDespesaFragment.this.requireContext(),
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }else{
            Log.i("debug", "Permissao garantida já!");
        }
        //Log.i("debug", "on create!");

        /*================Seleciona Data==========*/
        selecionaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(view.getContext(),
                        "Criando uma notificacao em 10 segundos..",
                        Toast.LENGTH_SHORT);
                toast.show();

                showDatePicker();

                //faz a notificacao
                //agendarNotificacao(10000);
            }
        });

        // Configuração da lógica de seleção
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Encontra o RadioButton selecionado
                RadioButton checkedRadioButton = view.findViewById(checkedId);

                // Verifica qual RadioButton foi selecionado
                if (checkedRadioButton.getId() == R.id.rbIndividual) {
                    // Lógica para o RadioButton 1
                    showToast("Despesa Individual");
                } else if (checkedRadioButton.getId() == R.id.rbCompartilhado) {
                    // Lógica para o RadioButton 2
                    showToast("Despesa Compartilhada");
                }
            }
        });

        /*===========Salvando despesa no banco de dados*/
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference despesaRef = reference.child("nome_despesa");
                ExpenseModel despesa = new ExpenseModel();
                despesa.setExpenseName("Parcela geladeira");

                despesaRef.child("002").setValue(despesa);
                Toast toast = Toast.makeText(view.getContext(),
                        "Dados salvos no banco de dados",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }

    // Função auxiliar para mostrar um Toast
    private void showToast(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void showDatePicker() {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        agendarNotificacao(calendar.getTimeInMillis());
                        Toast.makeText(requireContext(), "Data selecionada: " + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                    }
                },
                year,
                month,
                day
        );
        pickerDialog.show();
    }

    private void agendarNotificacao(long delay) {
        Intent intentNotification = new Intent(requireContext(), NotificationReceiver.class);
        //intentNotification.putExtra("notificationId", 1);
        intentNotification.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.i("debug", "agendando notificacao");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                intentNotification,
                PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + 1000,
                pendingIntent);
    }
}