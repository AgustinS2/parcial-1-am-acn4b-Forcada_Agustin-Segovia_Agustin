package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Modelo.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email_register = findViewById(R.id.email);
        EditText password = findViewById(R.id.password_account);
        EditText nombre = findViewById(R.id.name);
        EditText apellido = findViewById(R.id.surname);
        EditText edad = findViewById(R.id.age);

        Button registrarse = findViewById(R.id.registerButton);
        Button button_back = findViewById(R.id.button_back);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_register.getText().toString();
                String pass = password.getText().toString();
                String name = nombre.getText().toString();
                String surname = apellido.getText().toString();
                String age = edad.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !age.isEmpty()) {
                    int edad_user = Integer.parseInt(age);
                    User usuario_nuevo = new User(name,surname,email,pass,edad_user);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("usuarionuevo", usuario_nuevo);
                    startActivity(intent);

                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
