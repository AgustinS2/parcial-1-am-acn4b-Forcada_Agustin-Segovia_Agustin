package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.NumberFormat;

import Modelo.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button fixedTerm = findViewById(R.id.fixedButton);
        Button send_button = findViewById(R.id.sendButton);
        Button loans_button = findViewById(R.id.loansButton);
        Button credit_button = findViewById(R.id.creditCard);
        Button transaction_button = findViewById(R.id.transferButton);
        Button receive_button = findViewById(R.id.receiveButton);
        Button close = findViewById(R.id.close);


        Intent intent = getIntent();
        if (intent != null) {
            User usuario_actual = (User) getIntent().getSerializableExtra("usuario");

            // Verificar si el objeto Usuario no es nulo
            if (usuario_actual != null) {
                // Acceder a los datos del usuario
                TextView balance_text = findViewById(R.id.textBalance);
                String texto_actual = balance_text.getText().toString();
                String balance_format = NumberFormat.getNumberInstance().format(usuario_actual.getAccount().getBalance());
                String texto = texto_actual + " " + balance_format;
                balance_text.setText(texto);

                TextView welcomeText = findViewById(R.id.textWelcome);
                String texto_welcome = welcomeText.getText().toString() + " " + usuario_actual.getName() + " " + usuario_actual.getSurname();
                welcomeText.setText(texto_welcome);
            }


            send_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, SendActivity.class);
                    intent.putExtra("usuario",usuario_actual);
                    startActivity(intent);
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    mAuth.signOut();
                    startActivity(intent);
                }
            });
            fixedTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, FixedTermActivity.class);
                    intent.putExtra("usuario",usuario_actual);
                    startActivity(intent);
                }
            });
            loans_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, LoansActivity.class);
                    intent.putExtra("usuario",usuario_actual);
                    startActivity(intent);
                }
            });
            credit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CreditCardActivity.class);
                    intent.putExtra("usuario",usuario_actual);
                    startActivity(intent);
                }
            });
            transaction_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TransactionActivity.class);
                    intent.putExtra("usuario", usuario_actual);
                    startActivity(intent);
                }
            });

            receive_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ReceiveActivity.class);
                    intent.putExtra("usuario",usuario_actual);
                    startActivity(intent);
                }
            });


        }

    }
}
