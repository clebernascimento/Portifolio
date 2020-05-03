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
public class NoCartaoCreditoFragment extends Fragment {

    private MaterialButton salvarCartao;


    public NoCartaoCreditoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_cartao_credito, container, false);

        salvarCartao = view.findViewById(R.id.btnCadastrarCartao);
        salvarCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new FormCartaoCreditoFragment()).
                        commit();
            }
        });
        return view;
    }

}
