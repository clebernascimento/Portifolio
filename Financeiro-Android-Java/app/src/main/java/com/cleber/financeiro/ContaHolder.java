package com.cleber.financeiro;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContaHolder extends RecyclerView.ViewHolder {

    public String conta_id;

    public TextView descricao;
    public TextView valor;
    public TextView data;

    public Button pagar;

    public ContaHolder(@NonNull View itemView) {
        super(itemView);

        descricao = itemView.findViewById(R.id.txt_item_descricao);
        valor = itemView.findViewById(R.id.txt_item_valor);
        data = itemView.findViewById(R.id.txt_item_data);

        pagar = itemView.findViewById(R.id.btn_item_parcelas);
    }
}
