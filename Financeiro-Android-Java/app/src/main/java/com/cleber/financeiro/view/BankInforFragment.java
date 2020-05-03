package com.cleber.financeiro.view;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.BankController;
import com.cleber.financeiro.model.Banco;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankInforFragment extends Fragment implements View.OnClickListener {

    private TextView nome_banco;
    private TextView titular;
    private TextView agencia;
    private TextView conta;
    private TextView tipo_conta;

    private MaterialButton deposito;
    private MaterialButton saque;


    public BankInforFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_infor, container, false);

        nome_banco = view.findViewById(R.id.txtNome_Banco);
        titular = view.findViewById(R.id.txtNome_Titular);
        agencia = view.findViewById(R.id.txtAgencia);
        conta = view.findViewById(R.id.txtConta);
        tipo_conta = view.findViewById(R.id.txtTipo_Conta);

        Banco inforBanco = BankController.get();

        nome_banco.setText(inforBanco.getNomeBanco());
        titular.setText(inforBanco.getTitular());
        agencia.setText(inforBanco.getAgencia());
        conta.setText(inforBanco.getConta());
        tipo_conta.setText(inforBanco.getTipoConta());

        deposito = view.findViewById(R.id.btnDeposito);
        deposito.setOnClickListener(this);

        saque = view.findViewById(R.id.btnSaque);
        saque.setOnClickListener(this);
        return view;
    }

    public void saldoDialog(final boolean saldo) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.valordialog);
        TextView tituloDialog = dialog.findViewById(R.id.txtTituloDialog);

        if (saldo) {
            tituloDialog.setText("Depósito");
            tituloDialog.setTextColor(Color.GREEN);
        } else {
            tituloDialog.setText("Saque");
            tituloDialog.setTextColor(Color.RED);
        }

        final TextInputEditText txtValor = dialog.findViewById(R.id.editValorDialog);

        MaterialButton btnConfirmar = dialog.findViewById(R.id.btnConfirmarDialog);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double valor = Double.parseDouble(txtValor.getText().toString());
                if (saldo) {
                    BankController.deposito(valor);
                    Toast.makeText(getActivity(),"Depósito realizado com sucesso !", Toast.LENGTH_LONG).show();

                } else {
                    BankController.saque(valor);
                    Toast.makeText(getActivity(), "Saque realizado com sucesso!", Toast.LENGTH_LONG).show();
                }

                dialog.cancel();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BankInforFragment()).commit();

            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDeposito:
                saldoDialog(true);
                break;
            case R.id.btnSaque:
                saldoDialog(false);
                break;
        }
    }
}
