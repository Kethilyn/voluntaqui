package com.example.voluntaqui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AjudaListAdapter extends RecyclerView.Adapter<AjudaListAdapter.ViewHolderAjuda> {

    List<Ajuda> dados;

    public AjudaListAdapter(List<Ajuda> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public AjudaListAdapter.ViewHolderAjuda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lista_ajuda, parent, false);

        ViewHolderAjuda holderAjuda = new ViewHolderAjuda(view);

        return holderAjuda;
    }

    @Override
    public void onBindViewHolder(@NonNull AjudaListAdapter.ViewHolderAjuda holder, int position) {

        if ((dados != null) && (dados.size() > 0)){
            holder.Nome.setText(dados.get(position).getNome());
            holder.Endereco.setText(dados.get(position).getEndereco());
            holder.Telefone.setText(dados.get(position).getTelefone());
            holder.TipoAjuda.setText(dados.get(position).getTipoAjuda());
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderAjuda extends RecyclerView.ViewHolder{

        public TextView Nome, Endereco, Telefone, TipoAjuda;

        public ViewHolderAjuda(@NonNull View itemView) {
            super(itemView);

            Nome = (TextView) itemView.findViewById(R.id.txtNome);
            Endereco = (TextView) itemView.findViewById(R.id.txtEndereco);
            Telefone = (TextView) itemView.findViewById(R.id.txtTelefone);
            TipoAjuda = (TextView) itemView.findViewById(R.id.txtTipoAjuda);
        }
    }
}
