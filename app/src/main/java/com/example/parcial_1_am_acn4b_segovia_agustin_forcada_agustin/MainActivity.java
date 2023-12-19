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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

    public FirebaseAuth mAuth;

    public FirebaseFirestore db;

    public User usuario_actual;

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
        List<User> listUser = new ArrayList<>();



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
                login(usuario,passwordInput);

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

            DocumentReference userDocRef = db.collection("users").document(uid);

            userDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            usuario_actual = document.toObject(User.class);
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.putExtra("usuario", usuario_actual);
                            startActivity(intent);
                        }
                    }
                }
            });
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
    private void dialog(String text)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void login(TextView user, TextView pass) {
        String username = user.getText().toString();
        String password = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null){
                        String uid = currentUser.getUid();
                        DocumentReference userDocRef = db.collection("users").document(uid);
                        userDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        usuario_actual = document.toObject(User.class);
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        intent.putExtra("usuario", usuario_actual);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
                    }
                } else {
                    showErrorDialog();
                }
            }
        });

    }

}