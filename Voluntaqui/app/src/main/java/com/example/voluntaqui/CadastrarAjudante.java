package com.example.voluntaqui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadastrarAjudante extends AppCompatActivity {
    EditText Nome, Endereco, Telefone, Email, Senha;
    Button Cadastrar, Voltar;
    RadioButton Opcao1, Opcao2, Opcao3, Opcao4, Opcao5, Opcao6;
    Ajudante ajudante;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrarajudante);

        Cadastrar = (Button)findViewById(R.id.btnCadastrar);
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nome = (EditText)findViewById(R.id.edNome);
                Endereco = (EditText)findViewById(R.id.edEndereco);
                Telefone = (EditText)findViewById(R.id.edTel);
                Email = (EditText)findViewById(R.id.edEmail);
                Senha = (EditText)findViewById(R.id.edSenha);
                Opcao1 = (RadioButton)findViewById(R.id.rb1);
                Opcao2 = (RadioButton)findViewById(R.id.rb2);
                Opcao3 = (RadioButton)findViewById(R.id.rb3);
                Opcao4 = (RadioButton)findViewById(R.id.rb4);
                Opcao5 = (RadioButton)findViewById(R.id.rb5);
                Opcao6 = (RadioButton)findViewById(R.id.rb6);

                String TipoAjuda = "";
                int idAjuda = 0;

                if (Opcao1.isChecked()){
                    TipoAjuda = "Voluntário";
                    idAjuda = 1;
                } else if (Opcao2.isChecked()){
                    TipoAjuda = "Agasalhos";
                    idAjuda = 2;
                } else if (Opcao3.isChecked()){
                    TipoAjuda = "Alimentos";
                    idAjuda = 3;
                } else if (Opcao4.isChecked()){
                    TipoAjuda = "Medicamentos";
                    idAjuda = 4;
                } else if (Opcao5.isChecked()){
                    TipoAjuda = "Material escolar";
                    idAjuda = 5;
                } else if (Opcao6.isChecked()){
                    TipoAjuda = "Outro";
                    idAjuda = 6;
                }

                ajudante = new Ajudante(Nome.getText().toString(), Endereco.getText().toString(), Telefone.getText().toString(), TipoAjuda, idAjuda);

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Senha.getText().toString())
                        .addOnCompleteListener(CadastrarAjudante.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    firebaseUser = firebaseAuth.getCurrentUser();
                                    UserProfileChangeRequest userProfile = new UserProfileChangeRequest.Builder()
                                            .setDisplayName("Ajudante").build();
                                    firebaseUser.updateProfile(userProfile);
                                    firebaseFirestore = FirebaseFirestore.getInstance();
                                    firebaseFirestore.collection("Ajudante").document(firebaseUser.getUid())
                                            .set(ajudante).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(CadastrarAjudante.this, "Usuário cadastrado, faça login!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(CadastrarAjudante.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(CadastrarAjudante.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                } else {
                                    Toast.makeText(CadastrarAjudante.this, "Email já cadastrado!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

        Voltar = (Button)findViewById(R.id.btnVoltar);
        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarAjudante.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}