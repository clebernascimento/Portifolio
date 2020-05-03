package com.cleber.financeiro.view;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.ContaController;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContasFormFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText valor;
    private TextInputEditText descricao;
    private TextInputEditText data;

    private MaterialButton salvar;
    private MaterialButton cancelar;

    private DatePickerDialog.OnDateSetListener dateSetListener;


    public ContasFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contas_form, container, false);

        valor = view.findViewById(R.id.edit_Valor);
        descricao = view.findViewById(R.id.edit_Descrição);
        data = view.findViewById(R.id.edit_Data_conta);
        setdateField();

        salvar = view.findViewById(R.id.btn_Salvar);
        salvar.setOnClickListener(this);

        cancelar = view.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Salvar:
                if (!valor.getText().toString().isEmpty() && !descricao.getText().toString().isEmpty()
                        && !data.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Conta cadastrada com sucesso !", Toast.LENGTH_LONG).show();
                    getData();
                } else {
                    Snackbar.make(view, "Todos os campos devem ser preenchidos !", Snackbar.LENGTH_LONG).setAction("OK",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }
                break;

            case R.id.btn_Cancelar:
                voltar();
                break;
        }
    }

    public void voltar() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new ContasListFragment())
                .commit();
    }

    public void getData() {
        double valor_Txt = Double.parseDouble(valor.getText().toString());
        String descricao_txt = descricao.getText().toString();
        String data_txt = data.getText().toString();
        ContaController.createNovo(valor_Txt, descricao_txt, data_txt);
        Toast.makeText(getActivity().getApplicationContext(), "Conta criada", Toast.LENGTH_LONG).show();
        voltar();
    }

    //Metod para colocar a data atual no campo data da despesa
    private void setdateField() {
        // usando a biblioteca Calendar
        Calendar calendar = Calendar.getInstance();

        //declarando variavs ano mes dia
        final int ano = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        //Convertendo para string as variavies ano mes dia
        String dataString = Integer.toString(dia) + '/' + Integer.toString(mes + 1) + '/' + Integer.toString(ano);

        data.setText(dataString);
        data.setKeyListener(null);// nao deixa editar a data no campo

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, ano, mes, dia
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// fundo transparente
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                //Convertendo para string as variavies ano mes dia
                String dataString = Integer.toString(dia) + '/' + Integer.toString(mes + 1) + '/' + Integer.toString(ano);
                data.setText(dataString);
            }
        };
    }

}
