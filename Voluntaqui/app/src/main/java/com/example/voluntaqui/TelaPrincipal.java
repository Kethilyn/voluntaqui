package com.example.voluntaqui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    RecyclerView listaAjuda;
    AjudaListAdapter listAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Ajuda> dados;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        listaAjuda = (RecyclerView)findViewById(R.id.listaAjuda);
        listaAjuda.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listaAjuda.setLayoutManager(layoutManager);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        dados = new ArrayList<Ajuda>();

        firebaseFirestore.collection("Ajuda").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()){
                    Ajuda ajuda = new Ajuda();
                    ajuda.setNome(document.getString("nome"));
                    ajuda.setEndereco(document.getString("endereco"));
                    ajuda.setTelefone(document.getString("telefone"));
                    ajuda.setTipoAjuda(document.getString("tipoAjuda"));

                    dados.add(ajuda);
                }

                listAdapter = new AjudaListAdapter(dados);
                listaAjuda.setAdapter(listAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TelaPrincipal.this, "Nenhuma solicitação de ajuda cadastrada!", Toast.LENGTH_LONG).show();
            }
        });
    }
}