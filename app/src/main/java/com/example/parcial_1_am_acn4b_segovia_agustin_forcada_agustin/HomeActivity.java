package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

import Modelo.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button fixedTerm = findViewById(R.id.fixedButton);
        Button send_button = findViewById(R.id.sendButton);
        Button loans_button = findViewById(R.id.loansButton);
        Button credit_button = findViewById(R.id.creditCard);
        Button transaction_button = findViewById(R.id.transferButton);
        Button receive_button = findViewById(R.id.receiveButton);


        Intent intent = getIntent();
        if (intent != null) {
            User usuario_actual = (User) getIntent().getSerializableExtra("usuario");

            // Verificar si el objeto Usuario no es nulo
            if (usuario_actual != null) {
                // Acceder a los datos del usuario y mostrarlos en el TextView
                TextView textView = findViewById(R.id.textView4);
                String texto_actual = textView.getText().toString();
                String balance_format = NumberFormat.getNumberInstance().format(usuario_actual.getAccount().getBalance());
                String texto = texto_actual + " " + balance_format;
                textView.setText(texto);
            }


            send_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, SendActivity.class);
                    startActivity(intent);
                }
            });

            fixedTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, FixedTermActivity.class);
                    startActivity(intent);
                }
            });
            loans_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, LoansActivity.class);
                    startActivity(intent);
                }
            });
            credit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CreditCardActivity.class);
                    startActivity(intent);
                }
            });
            transaction_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TransactionActivity.class);
                    startActivity(intent);
                }
            });

            receive_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ReceiveActivity.class);
                    startActivity(intent);
                }
            });


        }

    }
}
