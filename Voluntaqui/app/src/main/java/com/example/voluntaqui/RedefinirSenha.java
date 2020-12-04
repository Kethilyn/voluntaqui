package com.example.voluntaqui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RedefinirSenha extends AppCompatActivity {
    EditText Email;
    Button Enviar, Voltar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        Enviar = (Button)findViewById(R.id.btnEnviar);
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = (EditText)findViewById(R.id.edEmail);
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.sendPasswordResetEmail(Email.getText().toString())
                        .addOnCompleteListener(RedefinirSenha.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RedefinirSenha.this, "Email enviado com sucesso", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RedefinirSenha.this, Entrar.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RedefinirSenha.this, "Email invalido", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        Voltar = (Button)findViewById(R.id.btnVoltar);
        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RedefinirSenha.this, Entrar.class);
                startActivity(intent);
            }
        });
    }
}