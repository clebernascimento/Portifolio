package com.cleber.financeiro.view;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cleber.financeiro.ContaAdapter;
import com.cleber.financeiro.ParcelamentoAdapter;
import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.ContaController;
import com.cleber.financeiro.controller.ParcelamentoController;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParcelamentoListFragment extends Fragment {

    private Button confirmar;


    public ParcelamentoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parcelamento_list, container, false);

        confirmar = view.findViewById(R.id.btnAdicionarParcela);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        RecyclerView parcela_Recycler = view.findViewById(R.id.parcelamento_lista);
        parcela_Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ParcelamentoAdapter parcelaAdapter = new ParcelamentoAdapter(ParcelamentoController.getAll());
        parcela_Recycler.setAdapter(parcelaAdapter);

        return view;
    }

    public void createDialog() {
        final Dialog myDialog = new Dialog(getActivity());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setContentView(R.layout.parcelamento_dialog);

        final EditText descricao_parcelas = myDialog.findViewById(R.id.edit_descricao_parcelamento);
        final EditText valor_parcelas = myDialog.findViewById(R.id.edit_valor_parcelamento);
        final EditText numero_parelas = myDialog.findViewById(R.id.edit_num_parcelas);

        Button confimar_parcelas = myDialog.findViewById(R.id.btn_confirma_parcela);
        confimar_parcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!descricao_parcelas.getText().toString().isEmpty() && !numero_parelas.getText().toString().isEmpty()
                        && !valor_parcelas.getText().toString().isEmpty()) {

                    String descricao = descricao_parcelas.getText().toString();
                    int quantidade = Integer.parseInt(numero_parelas.getText().toString());
                    double valor = Double.parseDouble(valor_parcelas.getText().toString());

                    ParcelamentoController.createNovoParcelamnento(descricao, valor, quantidade);

                    Toast.makeText(getActivity().getApplicationContext(), "Parcelamento criado", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new ParcelamentoListFragment())
                            .commit();
                    myDialog.cancel();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Todos os campos devem ser preenchidos !", Toast.LENGTH_LONG).show();
//                    Snackbar.make(view, "Todos os campos devem ser preenchidos !", Snackbar.LENGTH_LONG).setAction("OK",
//                            new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                }
//                            }).show();
                }
            }
        });
        myDialog.show();
    }
}
