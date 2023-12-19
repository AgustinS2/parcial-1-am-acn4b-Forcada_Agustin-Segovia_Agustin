package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import Modelo.User;

public class CreditCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        Button button_back = findViewById(R.id.button_back);
        TextView textMarca1 = findViewById(R.id.textMarca1);
        TextView textEstado1 = findViewById(R.id.textEstado1);
        TextView textBalance1 = findViewById(R.id.textBalance1);
        TextView textAccountNumber1 = findViewById(R.id.textAccountNumber1);
        TextView textCardNumber1 = findViewById(R.id.textCardNumber1);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");

        if (usuario_actual != null){

        textMarca1.setText(textMarca1.getText() + " " + usuario_actual.getMarca());
        textEstado1.setText(textEstado1.getText() + " " + usuario_actual.getEstado());
        textBalance1.setText(textBalance1.getText() + " " + usuario_actual.getBalance());
        textAccountNumber1.setText(textAccountNumber1.getText() + " " + usuario_actual.getNumeroCuenta());
        textCardNumber1.setText(textCardNumber1.getText() + " " + usuario_actual.getNumeroTarjeta());
        }


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditCardActivity.this, HomeActivity.class);
                intent.putExtra("usuario", usuario_actual);
                startActivity(intent);
            }
        });
    }
}
