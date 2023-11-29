package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

import Modelo.CreditCard;
import Modelo.User;

public class FixedTermActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_term);

        Button simular = findViewById(R.id.simulate);
        EditText amount = findViewById(R.id.amount);
        Button button_back = findViewById(R.id.button_back);
        Button send = findViewById(R.id.send);

        TextView intereses_generates = findViewById(R.id.interes_generated);
        TextView amount_total = findViewById(R.id.amount_total);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");


        Spinner spinner_account_selected = findViewById(R.id.selected_account);
        String[] opciones_selected_account;
        if (!usuario_actual.getAccount().getCreditCards().isEmpty()) {
            opciones_selected_account = new String[]{"Seleccione una cuenta", String.valueOf(usuario_actual.getAccount().getCreditCards().get(0).getNumero_cuenta()), String.valueOf(usuario_actual.getAccount().getCreditCards().get(1).getNumero_cuenta())};
        } else {
            opciones_selected_account = new String[]{"No dispone de cuentas ya que es un nuevo usuario"};
        }
        ArrayAdapter<String> adapter_selected_account = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_selected_account);
        spinner_account_selected.setAdapter(adapter_selected_account);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedTermActivity.this, HomeActivity.class);
                intent.putExtra("usuario", usuario_actual);
                startActivity(intent);
            }
        });
        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = amount.getText().toString();

                if (!res.isEmpty()) {

                    double monto = Double.parseDouble(res);
                    double intereses = 7.3;

                    double intereses_totales = monto * intereses / 100;
                    double monto_total = monto + intereses_totales;

                    intereses_generates.setText(String.valueOf(intereses_totales));
                    amount_total.setText(String.valueOf(monto_total));
                }

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuario_actual.getAccount().getCreditCards().isEmpty() && !amount.getText().toString().isEmpty()) {
                    if (operationFixedTerm(amount, usuario_actual, spinner_account_selected)) {
                        Intent intent = new Intent(FixedTermActivity.this, HomeActivity.class);
                        intent.putExtra("usuario", usuario_actual);
                        successFixedTerm(intent);
                    } else {
                        showErrorDialog();
                    }
                }
                else {
                    showErrorDialog();
                }

            }

        });


    }

    private boolean operationFixedTerm(EditText amount, User usuario_actual, Spinner spinner_account_selected) {
        double monto = Double.parseDouble(amount.getText().toString());
        try {
            int selected_account = Integer.parseInt(spinner_account_selected.getSelectedItem().toString());
            if (!usuario_actual.getAccount().getCreditCards().isEmpty()) {
                for (CreditCard c : usuario_actual.getAccount().getCreditCards()) {
                    if (selected_account == c.getNumero_cuenta() && monto <= c.getSaldo_actual()) {
                        c.setSaldo_actual(c.getSaldo_actual() - monto);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    private void showErrorDialog()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("No posee el monto indicado en su cuenta");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void successFixedTerm(Intent intent)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Succes");
        builder.setMessage("El plazo fijo se realizo con exito");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
