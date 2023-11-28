package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import Modelo.User;

public class ReceiveActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");
        Button button_back = findViewById(R.id.button_back);
        TextView alias = findViewById(R.id.alias);
        TextView cbu = findViewById(R.id.cbu);
        TextView amount_total = findViewById(R.id.amount_total);


        String textAlias = usuario_actual.getAccount().getAlias();
        alias.setText(textAlias);

        String textCbu = String.valueOf(usuario_actual.getAccount().getCbu());
        cbu.setText(textCbu);

        String textAmount = String.valueOf(usuario_actual.getAccount().getBalance());
        amount_total.setText(textAmount);





        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceiveActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
