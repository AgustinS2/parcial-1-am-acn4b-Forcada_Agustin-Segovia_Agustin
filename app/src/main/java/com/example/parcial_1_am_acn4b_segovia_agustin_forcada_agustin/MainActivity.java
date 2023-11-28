package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.Account;
import Modelo.CreditCard;
import Modelo.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usuario = findViewById(R.id.emailUsername);
        TextView passwordInput = findViewById(R.id.password);
        Account account1 = new Account (143244435, "banco.norte.acc1");
        Account account2 = new Account (34223424, "banco.norte.acc2");
        CreditCard creditCard1 = new CreditCard("Visa", "Nueva", 26450, 23123342, 234234556);
        CreditCard creditCard2 = new CreditCard("Mastercard", "Nueva", 26450, 2123123, 12342423);
        CreditCard creditCard3 = new CreditCard("Visa", "Nueva", 10000, 2123123, 12342423);
        CreditCard creditCard4 = new CreditCard("Visa", "Nueva", 14000, 2123123, 12342423);
        account1.addCreditCard(creditCard1);
        account1.addCreditCard(creditCard2);
        account2.addCreditCard(creditCard3);
        account2.addCreditCard(creditCard4);


        Button register = findViewById(R.id.buttonRegister);
        Button ingreso = findViewById(R.id.buttonLogin);
        User agustin = new User("Agustin", "Segovia", "agus@gmail.com", "abc123", 22, account1);
        User lucas = new User("Lucas", "Segovia", "lucas@gmail.com", "abc1234", 32, account2);
        List<User> listUser = new ArrayList<>();
        listUser.add(agustin);
        listUser.add(lucas);

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(usuario,passwordInput,listUser);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showErrorDialog()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Nombre de usuario o contraseña incorrectos");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public boolean login(TextView user, TextView pass, List<User> users) {
        for (User u : users) {
            if ((user.getText().toString().toLowerCase().equals(u.getName().toLowerCase()) || user.getText().toString().toLowerCase().equals(u.getEmail().toLowerCase()))
                    && pass.getText().toString().toLowerCase().equals(u.getPassword().toLowerCase())) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("usuario", u);
                startActivity(intent);
                return true;
            }
        }
        showErrorDialog();
        return false;
    }

}