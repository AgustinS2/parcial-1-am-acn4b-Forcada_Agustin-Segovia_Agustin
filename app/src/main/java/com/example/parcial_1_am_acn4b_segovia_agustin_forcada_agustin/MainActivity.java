package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usuario = findViewById(R.id.emailUsername);
        TextView password = findViewById(R.id.password);

        Button ingreso = findViewById(R.id.buttonLogin);

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario.getText().toString().toUpperCase().equals("AGUSTIN") && password.getText().toString().toUpperCase().equals("ABC123"))
                {
                    Toast.makeText(MainActivity.this, "Entre", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "No entre", Toast.LENGTH_SHORT).show();
            }
        });

    }
}