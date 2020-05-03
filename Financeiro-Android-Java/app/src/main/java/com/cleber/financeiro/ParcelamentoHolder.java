package com.cleber.financeiro;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParcelamentoHolder extends RecyclerView.ViewHolder {

    public TextView descricao;
    public TextView valor;
    public TextView numero_parcelas;
    public TextView parcelasMax;

    public ProgressBar progress;

    public Button parcelasItem;

    public String id;

    public int progressValor;

    public ParcelamentoHolder(@NonNull View itemView){
        super(itemView);

        descricao =  itemView.findViewById(R.id.txt_item_descricao_parcelas);
        valor = itemView.findViewById(R.id.txt_item_valor_parcelas);

        progress = itemView.findViewById(R.id.progressBar);

        numero_parcelas = itemView.findViewById(R.id.txt_numero_parcelas);
        parcelasMax = itemView.findViewById(R.id.maxParcela);

        parcelasItem = itemView.findViewById(R.id.btn_item_parcelas);
    }
}
