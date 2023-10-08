package com.jorzelio.despesa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.jorzelio.despesa.R;
import com.jorzelio.despesa.databinding.ActivityMainBinding;

public class SplashActivity extends AppCompatActivity {

    static int TEMPO_SPLASH_SCREEN = 4000; //milissegundos
    Handler handler = new Handler();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                irParaProximaTela();
            }

            private void irParaProximaTela() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TEMPO_SPLASH_SCREEN);

        progressBar = findViewById(R.id.pb_splash);
        progressBar.getProgress();
    }
}