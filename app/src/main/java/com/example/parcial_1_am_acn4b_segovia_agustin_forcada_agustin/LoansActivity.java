package com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Modelo.User;

public class LoansActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);
        Button button_back = findViewById(R.id.button_back);

        User usuario_actual = (User) getIntent().getSerializableExtra("usuario");
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoansActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
