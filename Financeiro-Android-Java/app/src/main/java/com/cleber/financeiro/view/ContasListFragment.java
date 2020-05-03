package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cleber.financeiro.ContaAdapter;
import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.ContaController;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContasListFragment extends Fragment{

    private Button adicionarConta;


    public ContasListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas_list, container, false);

        adicionarConta = view.findViewById(R.id.btnAdicionarConta);
        adicionarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new ContasFormFragment())
                        .commit();
            }
        });

        RecyclerView conta_Recycler = view.findViewById(R.id.ContaLista);
        conta_Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ContaAdapter contaAdapter = new ContaAdapter(ContaController.getAll());
        conta_Recycler.setAdapter(contaAdapter);
        return view;
    }
}
