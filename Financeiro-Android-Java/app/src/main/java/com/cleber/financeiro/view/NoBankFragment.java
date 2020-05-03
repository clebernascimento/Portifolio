package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleber.financeiro.R;
import com.google.android.material.button.MaterialButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoBankFragment extends Fragment implements View.OnClickListener {

    private MaterialButton btnCadastrarConta;


    public NoBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_bank, container, false);

        btnCadastrarConta = view.findViewById(R.id.btnCadastrarCartao);
        btnCadastrarConta.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCadastrarCartao){
            getActivity().
                    getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragmentContainer, new BankFormFragment()).
                    commit();
        }
    }
}
