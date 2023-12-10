package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.Account;
import Modelo.CreditCard;
import Modelo.User;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


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


        Intent intent = getIntent();
        if (intent != null) {
            User usuario_nuevo = (User) getIntent().getSerializableExtra("usuarionuevo");

            // Verificar si el objeto Usuario no es nulo
            if (usuario_nuevo != null) {
                // Acceder a los datos del usuario
                listUser.add(usuario_nuevo);
            }
        }


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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String uid = currentUser.getUid();

            db.collection("users").whereEqualTo("uid", uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for(QueryDocumentSnapshot documento : task.getResult()) {
                            String id = documento.getId();
                            Object data = documento.getData();

                            User usernew = documento.toObject(User.class);

                            // CAMBIAR DATOS
                            db.collection("users").document(id).update("apellido", "segovia").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                            Log.i("firebase firestore", "id" + id + "data:" + data.toString());
                        }
                    }
                }
            });

            Log.i("firebase", "Usuario logueado");
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
        else {
            Log.i("firebase", "Usuario sin loguear");
        }
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