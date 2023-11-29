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
        TextView textMarca2 = findViewById(R.id.textMarca2);
        TextView textEstado2 = findViewById(R.id.textEstado2);
        TextView textBalance2 = findViewById(R.id.textBalance2);
        TextView textAccountNumber2 = findViewById(R.id.textAccountNumber2);
        TextView textCardNumber2 = findViewById(R.id.textCardNumber2);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");

        if (!usuario_actual.getAccount().getCreditCards().isEmpty()){

        textMarca1.setText(textMarca1.getText() + " " + usuario_actual.getAccount().getCreditCards().get(0).getMarca());
        textEstado1.setText(textEstado1.getText() + " " + usuario_actual.getAccount().getCreditCards().get(0).getEstado());
        textBalance1.setText(textBalance1.getText() + " " + usuario_actual.getAccount().getCreditCards().get(0).getSaldo_actual());
        textAccountNumber1.setText(textAccountNumber1.getText() + " " + usuario_actual.getAccount().getCreditCards().get(0).getNumero_cuenta());
        textCardNumber1.setText(textCardNumber1.getText() + " " + usuario_actual.getAccount().getCreditCards().get(0).getNumero_tarjeta());
        textMarca2.setText(textMarca2.getText() + " " + usuario_actual.getAccount().getCreditCards().get(1).getMarca());
        textEstado2.setText(textEstado2.getText() + " " + usuario_actual.getAccount().getCreditCards().get(1).getEstado());
        textBalance2.setText(textBalance2.getText() + " " + usuario_actual.getAccount().getCreditCards().get(1).getSaldo_actual());
        textAccountNumber2.setText(textAccountNumber2.getText() + " " + usuario_actual.getAccount().getCreditCards().get(1).getNumero_cuenta());
        textCardNumber2.setText(textCardNumber2.getText() + " " + usuario_actual.getAccount().getCreditCards().get(1).getNumero_tarjeta());
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
