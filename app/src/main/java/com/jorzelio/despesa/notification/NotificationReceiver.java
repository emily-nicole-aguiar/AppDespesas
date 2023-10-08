package com.jorzelio.despesa.notification;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.jorzelio.despesa.R;
import com.jorzelio.despesa.fragments.AddDespesaFragment;

import java.util.Objects;

public class NotificationReceiver extends BroadcastReceiver {

    public static String ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("debug", "Trying rcv notif");

        NotificationChannel channel = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    "br.edu.ufam.testenotification",
                    "testeNotification",
                    NotificationManager.IMPORTANCE_DEFAULT);
        }
        NotificationManager nfm = (NotificationManager)
                context.getSystemService(
                        Context.NOTIFICATION_SERVICE);

        if(nfm != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                nfm.createNotificationChannel(channel);
                Log.i("debug","criando canal de notif");
            }
        }

        Log.i("debug","notificando...");
        Notification nf = intent.getParcelableExtra(NOTIFICATION);
//        int id = intent.getIntExtra(ID, 0 );
//        nfm.notify(id,nf);
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, channelID);
        builder.setSmallIcon(R.drawable.ic_logo)
                .setContentTitle("Titulo")
                .setContentText("Notificacao de fatura")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        assert nfm != null;
        nfm.notify(1, builder.build());

        /*
        int notificationId = intent.getIntExtra("notificationId", 0);

        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId")
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle("Fatura de débito")
                .setContentText("Sua despesa está prestes a vencer!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    101);
        }else{
            Log.i("debug", "Permissao garantida já!");
        }

        notificationManager.notify(notificationId, builder.build());

        Log.i("debug","notificando...");
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    "channelId",
                    "Channel Name",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }*/
    }
}
