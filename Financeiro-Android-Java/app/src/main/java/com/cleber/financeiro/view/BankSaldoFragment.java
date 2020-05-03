package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.BankController;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankSaldoFragment extends Fragment {

    private TextView saldoBancario;


    public BankSaldoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_saldo, container, false);

        saldoBancario = view.findViewById(R.id.txtSaldo_bancario);
        saldoBancario.setText(String.format(getString(R.string.cifrao)+" "+"%.2f",BankController.getSaldo()));
        return view;
    }

}
