package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Modelo.User;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "createUserWithEmail:success");
                                // Usuario registrado con éxito en Firebase Authentication
                                String userId = mAuth.getCurrentUser().getUid(); // Obtén el ID del usuario
                                saveUserDataToFirestore(userId, email, name, surname, age);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                successOperation(intent);
                            }
                            else {
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Creacion fallida.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


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

    private void successOperation(Intent intent)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Succes");
        builder.setMessage("La cuenta se realizo con exito");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void saveUserDataToFirestore(String userId, String email, String name, String surname, String age) {
        // Obtén una referencia a la colección de usuarios en Firestore
        CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("users");


        int numerotarjeta = Integer.parseInt(generarNumeroTarjeta());
        int numerocuenta = Integer.parseInt(generarNumeroTarjeta());
        int cbu = Integer.parseInt(generarNumeroTarjeta());
        String alias = generarAlias();


        // Crea un objeto Map con los datos del usuario
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("name", name);
        userData.put("surname", surname);
        userData.put("age", age);
        userData.put("balance", 10.0);
        userData.put("marca", "Visa");
        userData.put("estado", "Deshabilitada");
        userData.put("numerotarjeta", numerotarjeta);
        userData.put("numerocuenta", numerocuenta);
        userData.put("cbu", cbu);
        userData.put("alias", alias);






        // Agrega el usuario a Firestore usando el ID del usuario en Firebase Authentication como identificador
        usersCollection.document(userId).set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Usuario agregado a Firestore con éxito");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar el fallo al agregar el usuario a Firestore
                        Log.e("TAG", "Error al agregar usuario a Firestore", e);
                        Toast.makeText(RegisterActivity.this, "Error al guardar información adicional.", Toast.LENGTH_SHORT).show();
                    }
                });
    }



        // Método para generar un número de tarjeta aleatorio
        public String generarNumeroTarjeta() {
            Random random = new Random();
            StringBuilder numeroTarjeta = new StringBuilder();

            // Agrega 16 dígitos al número de tarjeta (puedes ajustar según tus necesidades)
            for (int i = 0; i < 8; i++) {
                int digito = random.nextInt(10);
                numeroTarjeta.append(digito);
            }

            return numeroTarjeta.toString();
        }

        // Método para generar un alias aleatorio
        public String generarAlias() {
            String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder alias = new StringBuilder();

            // Agrega 8 caracteres al alias (puedes ajustar según tus necesidades)
            for (int i = 0; i < 8; i++) {
                if (i == 4) {
                    alias.append(".");
                } else {
                int indice = random.nextInt(caracteres.length());
                alias.append(caracteres.charAt(indice));
                }
            }

            return alias.toString();
        }

}
