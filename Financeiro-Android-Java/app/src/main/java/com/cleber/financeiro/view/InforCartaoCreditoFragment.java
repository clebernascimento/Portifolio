package com.cleber.financeiro.view;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.BankController;
import com.cleber.financeiro.controller.CartaoController;
import com.cleber.financeiro.model.CrediCard;
import com.cleber.financeiro.util.MaskUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class InforCartaoCreditoFragment extends Fragment implements View.OnClickListener{

    private TextView bandeira;
    private TextView titular;
    private TextInputEditText numeroCartao;

    private Button resetarLimite;
    private Button manipularLimite;


    public InforCartaoCreditoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_infor_cartao_credito, container, false);

        bandeira = view.findViewById(R.id.txtBandeira);
        titular = view.findViewById(R.id.txtTitularCartao);
        numeroCartao = view.findViewById(R.id.txtNumeroCartao);
        numeroCartao.addTextChangedListener(MaskUtil.mask2(numeroCartao, MaskUtil.FORMAT_CARTAO));

        resetarLimite = view.findViewById(R.id.btnResetarLimite);
        resetarLimite.setOnClickListener(this);
        manipularLimite= view.findViewById(R.id.btnManipularLimite);
        manipularLimite.setOnClickListener(this);

        CrediCard cartaoCredito = CartaoController.get();

        bandeira.setText(cartaoCredito.getBandeira());
        titular.setText(cartaoCredito.getTiutlar());
        numeroCartao.setText(cartaoCredito.getNumeroCartao());

        return view;
    }

    public void limiteDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.valordialog);
        TextView tituloDialog = dialog.findViewById(R.id.txtTituloDialog);

        tituloDialog.setText("Manipular limite");

        final TextInputEditText txtValor = dialog.findViewById(R.id.editValorDialog);

        MaterialButton btnConfirmar = dialog.findViewById(R.id.btnConfirmarDialog);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double limite = Double.parseDouble(txtValor.getText().toString());
               CartaoController.LimiteCredito(limite);

                dialog.cancel();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InforCartaoCreditoFragment()).commit();

            }
        });

        dialog.show();
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnManipularLimite){
            limiteDialog();
        }else if (view.getId() == R.id.btnResetarLimite){
            CartaoController.ReseteLimiteCredito();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InforCartaoCreditoFragment()).commit();

        }
    }
}
