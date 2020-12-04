package com.example.voluntaqui;

import android.widget.EditText;

public class Ajuda {
    String Nome, Endereco, Telefone, TipoAjuda;
    int idAjuda;

    public Ajuda() {

    }

    public Ajuda(String nome, String endereco, String telefone, String tipoAjuda, int idAjuda) {
        Nome = nome;
        Endereco = endereco;
        Telefone = telefone;
        TipoAjuda = tipoAjuda;
        this.idAjuda = idAjuda;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getTipoAjuda() {
        return TipoAjuda;
    }

    public void setTipoAjuda(String tipoAjuda) {
        TipoAjuda = tipoAjuda;
    }

    public int getIdAjuda() {
        return idAjuda;
    }

    public void setIdAjuda(int idAjuda) {
        this.idAjuda = idAjuda;
    }
}
