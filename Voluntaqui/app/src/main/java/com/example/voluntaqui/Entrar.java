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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Entrar extends AppCompatActivity {
    EditText Email, Senha;
    Button Entrar, Esqueci;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrar);

        Entrar = (Button)findViewById(R.id.btnEntrar);
        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = (EditText)findViewById(R.id.edEmail);
                Senha = (EditText)findViewById(R.id.edSenha);

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(Email.getText().toString(), Senha.getText().toString())
                        .addOnCompleteListener(Entrar.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(Entrar.this, TelaPrincipal.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Entrar.this, "Email ou senha invalidos", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        Esqueci = (Button)findViewById(R.id.btnEsqueci);
        Esqueci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.voluntaqui.Entrar.this, RedefinirSenha.class);
                startActivity(intent);
            }
        });
    }
}