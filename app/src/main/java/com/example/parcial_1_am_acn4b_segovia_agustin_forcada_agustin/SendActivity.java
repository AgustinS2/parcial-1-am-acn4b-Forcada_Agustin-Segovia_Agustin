package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import Modelo.CreditCard;
import Modelo.User;

public class SendActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String uid = currentUser.getUid();

    DocumentReference userDocRef = db.collection("users").document(uid);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Spinner spinner_account_send = findViewById(R.id.account_send);
        Spinner spinner_account_destination = findViewById(R.id.account_destination);
        Spinner spinner_reason = findViewById(R.id.reason);
        Button button_back = findViewById(R.id.button_back);
        Button send_money = findViewById(R.id.send_money);
        EditText amount_send = findViewById(R.id.amoun_send);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");

        String[] opciones_send = {"Selecciones una cuenta de envio agendada", "Cuenta de Melina", "Cuenta de Joaquin", "Cuenta de Marcos"};
        String[] opciones_destination;
        if (usuario_actual != null){
            opciones_destination = new String[] {"Selecciones una cuenta de destino", String.valueOf(usuario_actual.getNumeroCuenta())};
        }
        else {
            opciones_destination = new String[] {"No hay cuentas de destino"};
        }

        String[] opciones_reason = {"Seleccione un motivo", "Alquileres", "Honorarios", "Donaciones", "Varios"};
        ArrayAdapter<String> adapter_send = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_send);
        spinner_account_send.setAdapter(adapter_send);
        ArrayAdapter<String> adapter_destination = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_destination);
        spinner_account_destination.setAdapter(adapter_destination);
        ArrayAdapter<String> adapter_reason = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_reason);
        spinner_reason.setAdapter(adapter_reason);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendActivity.this, HomeActivity.class);
                intent.putExtra("usuario", usuario_actual);
                startActivity(intent);
            }
        });

        send_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destination = spinner_account_destination.getSelectedItem().toString();
                String send = spinner_account_send.getSelectedItem().toString();
                String motivo = spinner_reason.getSelectedItem().toString();
                boolean operationSuccess = false;
                if (destination != opciones_destination[0] && send != opciones_send[0] && motivo != opciones_reason[0] && !amount_send.getText().toString().isEmpty()){
                    int destination_account = Integer.parseInt(destination);
                    double amount_destination_acc = Double.parseDouble(amount_send.getText().toString());
                    if (destination_account == usuario_actual.getNumeroCuenta() && amount_destination_acc <= usuario_actual.getBalance()) {
                        usuario_actual.setBalance(usuario_actual.getBalance() - amount_destination_acc);
                        Map<String, Object> actualizarDatos = new HashMap<>();
                        actualizarDatos.put("balance", usuario_actual.getBalance());
                        userDocRef.update(actualizarDatos).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.i("TAG", "Actualizado");
                            }
                        });
                        operationSuccess = true;
                    }
                }
                if (operationSuccess) {
                    Intent intent = new Intent(SendActivity.this, HomeActivity.class);
                    intent.putExtra("usuario", usuario_actual);
                    successOperation(intent);
                }
                else {
                    showErrorDialog();
                }
            }
        });


    }

    private void successOperation(Intent intent)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Succes");
        builder.setMessage("El envio de dinero se realizo con exito");
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
    private void aca()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("aca");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void aca2()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("aca22");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
