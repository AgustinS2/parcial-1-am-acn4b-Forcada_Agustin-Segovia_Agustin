package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import Modelo.User;

public class LoansActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String uid = currentUser.getUid();

    DocumentReference userDocRef = db.collection("users").document(uid);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);
        Button button_back = findViewById(R.id.button_back);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button confirmed_transaction = findViewById(R.id.confirmed_transaction);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoansActivity.this, HomeActivity.class);
                intent.putExtra("usuario", usuario_actual);
                startActivity(intent);
            }
        });

        confirmed_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId_check = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId_check != -1) {
                    RadioButton selectedRadioButtonId = findViewById(radioGroup.getCheckedRadioButtonId());
                    String opcionSeleccion = selectedRadioButtonId.getText().toString();
                    switch (opcionSeleccion) {
                        case "$100.000 - 12 c/$12,000 - 44% int":
                            agregarSaldo(100000,usuario_actual);
                            break;
                        case "$200.000 - 12 c/$27.000 - 62% int":
                            agregarSaldo(200000,usuario_actual);
                            break;
                        case "$300.000 - 12 c/$43.000 - 72% int":
                            agregarSaldo(300000,usuario_actual);

                            break;
                        case "$500.000 - 12 c/$75.000 - 80% int":
                            agregarSaldo(400000,usuario_actual);
                            break;
                    }
                }
                else {
                    Log.e("RadioGroup", "Ningún RadioButton seleccionado");
                    showErrorDialog();
                }

            }
        });

    }

    private void agregarSaldo(int cantidad, User usuario_actual) {
        if (usuario_actual != null) {
            double saldo_actual = usuario_actual.getBalance();
            usuario_actual.setBalance(saldo_actual + cantidad);
            Map<String, Object> actualizarDatos = new HashMap<>();
            actualizarDatos.put("balance", usuario_actual.getBalance());
            userDocRef.update(actualizarDatos).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.i("TAG", "Actualizado");
                }
            });
            Intent intent = new Intent(LoansActivity.this, HomeActivity.class);
            intent.putExtra("usuario", usuario_actual);
            successOperation(intent);
        }
        else {
            showErrorDialog();
        }
    }

    private void successOperation(Intent intent)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Succes");
        builder.setMessage("El prestamo se recibio con exito");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void showErrorDialog()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("No se pudo realizar la operacion");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
