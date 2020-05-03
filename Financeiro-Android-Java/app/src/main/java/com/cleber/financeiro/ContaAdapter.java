package com.cleber.financeiro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cleber.financeiro.controller.ContaController;
import com.cleber.financeiro.model.Conta;

import java.util.List;

public class ContaAdapter extends RecyclerView.Adapter<ContaHolder>{

    private List<Conta> contaList;

    public ContaAdapter(List<Conta> contaList) {
        this.contaList = contaList;
    }


    @NonNull
    @Override
    public ContaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conta_item, parent,false);
        return new ContaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContaHolder holder, int position) {
        Conta conta = contaList.get(position);
        holder.descricao.setText(conta.getDescricao());
        holder.valor.setText(String.format("R$ "+"%.2f",conta.getValor()));
        holder.data.setText(conta.getDia()+"/"+conta.getMes()+"/"+conta.getAno());
        holder.conta_id = conta.getId();
        holder.pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContaController.setPagar(holder.conta_id);
                int id = holder.getAdapterPosition();
                contaList.remove(id);
                notifyItemRemoved(id);
                notifyItemChanged(id,contaList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return contaList.size();
    }
}