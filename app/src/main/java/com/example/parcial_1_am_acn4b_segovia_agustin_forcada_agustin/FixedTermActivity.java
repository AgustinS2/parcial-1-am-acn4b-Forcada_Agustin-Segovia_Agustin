package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class FixedTermActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_term);

        Button simular = findViewById(R.id.simulate);
        EditText amount = findViewById(R.id.amount);

        TextView intereses_generates = findViewById(R.id.interes_generated);
        TextView amount_total = findViewById(R.id.amount_total);

        Spinner spinner_account_selected = findViewById(R.id.selected_account);
        String[] opciones_selected_account = {"Seleccione una cuenta", "Cuenta de Agustin 24543A", "Cuenta de Agustin 38294B", "Caja de Ahorro"};
        ArrayAdapter<String> adapter_selected_account = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opciones_selected_account);
        spinner_account_selected.setAdapter(adapter_selected_account);

        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = amount.getText().toString();

                if (!res.isEmpty()){

                    double monto = Double.parseDouble(res);
                    double intereses = 7.3;

                    double intereses_totales = monto * intereses / 100;
                    double monto_total = monto + intereses_totales;

                    intereses_generates.setText(String.valueOf(intereses_totales));
                    amount_total.setText(String.valueOf(monto_total));
                }




            }
        });


    }

}
