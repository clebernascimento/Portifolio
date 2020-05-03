package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.CartaoController;
import com.cleber.financeiro.util.MaskUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormCartaoCreditoFragment extends Fragment implements View.OnClickListener{

    private EditText txt_bandeira;
    private EditText txt_titular;
    private TextInputEditText txt_numeroCartao;
    private EditText txt_limite;

    private Button cancelar_cartao;
    private Button salvar_cartao;


    public FormCartaoCreditoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_cartao_credito, container, false);

        txt_bandeira = view.findViewById(R.id.editBandeira);
        txt_titular = view.findViewById(R.id.editTitularCartao);

        txt_numeroCartao = view.findViewById(R.id.editNumeroCartao);
        txt_numeroCartao.addTextChangedListener(MaskUtil.mask2(txt_numeroCartao, MaskUtil.FORMAT_CARTAO));

        txt_limite = view.findViewById(R.id.editLimite);

        cancelar_cartao = view.findViewById(R.id.btn_Cartao_Cancelar);
        cancelar_cartao.setOnClickListener(this);

        salvar_cartao = view.findViewById(R.id.btn_Cartao_Salvar);
        salvar_cartao.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Cartao_Salvar){
            if (!txt_bandeira.getText().toString().isEmpty() && !txt_titular.getText().toString().isEmpty()
            && !txt_numeroCartao.getText().toString().isEmpty() && !txt_limite.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"Cartão cadastrado com sucesso !", Toast.LENGTH_LONG).show();
                getInfo();
                showinfor();
            }else{
                Snackbar.make(view,"Todos os campos devem ser preenchidos !", Snackbar.LENGTH_LONG).setAction("OK",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        }else if (view.getId() == R.id.btn_Cartao_Cancelar){
            cancelar();
        }
    }

    public void getInfo() {
        String bandeira = txt_bandeira.getText().toString();
        String numeroCartao = txt_numeroCartao.getText().toString();
        String titular = txt_titular.getText().toString();
        double limite = Double.parseDouble(txt_limite.getText().toString());
        CartaoController.createCartao(bandeira, numeroCartao, titular, limite);
    }

    public void showinfor() {
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new InforCartaoCreditoFragment()).
                commit();
    }

    public void cancelar(){
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new NoCartaoCreditoFragment()).
                commit();
    }
}
