package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

        private EditText usernameEditText;
        private Button registerButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            usernameEditText = findViewById(R.id.editTextUsername);
            registerButton = findViewById(R.id.buttonRegister);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameEditText.getText().toString();
                    if (!username.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "¡Bienvenido, " + username + "!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Por favor, ingresa un nombre de usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

