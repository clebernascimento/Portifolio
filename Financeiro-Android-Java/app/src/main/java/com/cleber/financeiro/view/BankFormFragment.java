package com.cleber.financeiro.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.BankController;
import com.cleber.financeiro.util.MaskUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankFormFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText edit_Banco;
    private TextInputEditText edit_Titular;
    private TextInputEditText edit_Agencia;
    private TextInputEditText edit_Saldo;
    private TextInputEditText edit_Tipo_Conta;
    private TextInputEditText edit_Conta;

    private MaterialButton salvar;
    private MaterialButton cancelar;

    public BankFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_form, container, false);

        edit_Banco = view.findViewById(R.id.editBanco);
        edit_Titular = view.findViewById(R.id.editTitularConta);

        edit_Agencia = view.findViewById(R.id.editAgencia);
        edit_Agencia.addTextChangedListener(MaskUtil.mask2(edit_Agencia, MaskUtil.FORMAT_AGENCIA_BANK));

        edit_Saldo = view.findViewById(R.id.editSaldo);

        edit_Tipo_Conta = view.findViewById(R.id.editTipo_Conta);

        edit_Conta = view.findViewById(R.id.editConta);
        edit_Conta.addTextChangedListener(MaskUtil.mask2(edit_Conta, MaskUtil.FORMAT_CONTA_BANK));

        salvar = view.findViewById(R.id.btn_Bank_Salvar);
        salvar.setOnClickListener(this);

        cancelar = view.findViewById(R.id.btn_Bank_Cancelar);
        cancelar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Bank_Salvar){
            if (!edit_Banco.getText().toString().isEmpty() && !edit_Titular.getText().toString().isEmpty()
                    && !edit_Agencia.getText().toString().isEmpty() && !edit_Saldo.getText().toString().isEmpty()
            && !edit_Tipo_Conta.getText().toString().isEmpty() && !edit_Conta.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"Conta cadastrada com sucesso !", Toast.LENGTH_LONG).show();
                saveContaBancaria();
            }else{
                Snackbar.make(view,"Todos os campos devem ser preenchidos !", Snackbar.LENGTH_LONG).setAction("OK",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        }else if (view.getId() == R.id.btn_Bank_Cancelar){
            cancelar();
        }
    }

    public void saveContaBancaria(){
        String banco = edit_Banco.getText().toString();
        String titular = edit_Titular.getText().toString();
        String agencia = edit_Agencia.getText().toString();
        String conta = edit_Conta.getText().toString();
        String tipo_Conta = edit_Tipo_Conta.getText().toString();
        double saldo = Double.parseDouble(edit_Saldo.getText().toString());
        BankController.createConta(banco, titular, agencia, conta, tipo_Conta, saldo);
        //Toast.makeText(getActivity(),"Banco criado", Toast.LENGTH_LONG).show();
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new BankInforFragment()).
                commit();
    }

    public void cancelar(){
        getActivity().
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new NoBankFragment()).
                commit();
    }
}
