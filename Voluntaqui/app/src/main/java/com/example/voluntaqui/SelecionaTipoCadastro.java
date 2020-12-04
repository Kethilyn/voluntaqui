package com.example.voluntaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelecionaTipoCadastro extends AppCompatActivity {
    Button Ajuda, Ajudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_tipo_cadastro);

        Ajuda=(Button)findViewById(R.id.btnAjuda);
        Ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecionaTipoCadastro.this, CadastrarAjuda.class);
                startActivity(intent);

            }
        });

        Ajudante=(Button)findViewById(R.id.btnAjudante);
        Ajudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecionaTipoCadastro.this, CadastrarAjudante.class);
                startActivity(intent);

            }


        });
    }
}