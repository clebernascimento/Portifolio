package com.cleber.financeiro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cleber.financeiro.controller.ParcelamentoController;
import com.cleber.financeiro.model.Parcelamento;

import java.util.List;

public class ParcelamentoAdapter extends RecyclerView.Adapter<ParcelamentoHolder> {

    private List<Parcelamento> list;

    public ParcelamentoAdapter(List<Parcelamento> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ParcelamentoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcelamnto_list, parent, false);
        return new ParcelamentoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ParcelamentoHolder holder, int position) {
        Parcelamento element = list.get(position);
        holder.id = element.getId();
        holder.descricao.setText(element.getDescricao());
        holder.valor.setText(String.format("%.2f",element.getValor()));
        holder.numero_parcelas.setText(String.valueOf(element.getCurrentPart()));
        holder.parcelasMax.setText(String.valueOf(element.getTotalPart()));
        holder.progressValor = element.getCurrentPart();
        holder.progress.setMax(element.getTotalPart());
        holder.progress.setProgress(element.getCurrentPart());
        holder.parcelasItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.progressValor++;
                holder.progress.setProgress(holder.progressValor);
                holder.numero_parcelas.setText(String.valueOf(holder.progressValor));
                if (ParcelamentoController.incrementoParcelas(holder.id)){
                    int id = holder.getAdapterPosition();
                    list.remove(id);
                    notifyItemRemoved(id);
                    notifyItemRangeChanged(id, list.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
