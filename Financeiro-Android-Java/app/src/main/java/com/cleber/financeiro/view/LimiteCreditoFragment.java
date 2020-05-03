package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.CartaoController;
import com.cleber.financeiro.model.CrediCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class LimiteCreditoFragment extends Fragment {

    private TextView limite;


    public LimiteCreditoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_limite_credito, container, false);

        limite = view.findViewById(R.id.txtLimite_Disponivel);
        CrediCard cartaoCredito = CartaoController.get();
        if (cartaoCredito != null){
            limite.setText(String.format(getString(R.string.cifrao)+" "+"%.2f",cartaoCredito.getLimiteAtual()));
        }else{
            limite.setText(String.format(getString(R.string.cifrao)+" "+"%.2f",0.0));
        }
        return view;
    }

}
