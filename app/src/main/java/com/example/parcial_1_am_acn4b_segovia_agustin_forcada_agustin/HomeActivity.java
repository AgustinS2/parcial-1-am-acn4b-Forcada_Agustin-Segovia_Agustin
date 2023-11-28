package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.coroutines.channels.Send;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button fixedTerm = findViewById(R.id.fixedButton);
        Button send_button = findViewById(R.id.sendButton);
        Button loans_button = findViewById(R.id.loansButton);
        Button credit_button = findViewById(R.id.creditCard);
        Button transaction_button = findViewById(R.id.transferButton);
        Button receive_button = findViewById(R.id.receiveButton);

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
                Intent intent = new Intent (HomeActivity.this, FixedTermActivity.class);
                startActivity(intent);
            }
        });
        loans_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeActivity.this, LoansActivity.class);
                startActivity(intent);
            }
        });
        credit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeActivity.this, CreditCardActivity.class);
                startActivity(intent);
            }
        });
        transaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeActivity.this, TransactionActivity.class);
                startActivity(intent);
            }
        });

        receive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeActivity.this, ReceiveActivity.class);
                startActivity(intent);
            }
        });


    }

}
