package com.cleber.financeiro.view;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cleber.financeiro.controller.GanhoController;
import com.cleber.financeiro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class GanhoFormFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText dataText;
    private TextInputEditText descricaoText;
    private TextInputEditText valorText;

    //private CheckBox consolida;

    private MaterialButton cancelar;
    private MaterialButton salvar;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    private Spinner spinner_categoria_list;

    public GanhoFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ganho_form, container, false);

        valorText = view.findViewById(R.id.edit_Valor);
        descricaoText = view.findViewById(R.id.edit_Descrição);

        spinner_categoria_list = view.findViewById(R.id.spinner_Lista_categoria);
        setCategoriaSpinner(view.getContext());

        //setando data
        dataText = view.findViewById(R.id.edit_Data);
        setdateField();

        salvar = view.findViewById(R.id.btn_Salvar);
        salvar.setOnClickListener(this);

        cancelar = view.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(this);
        return view;
    }

    // Ação do botao salvar

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Salvar) {
            //validando campos
            if (!valorText.getText().toString().isEmpty() && !descricaoText.getText().toString().isEmpty()){
                dadosDoCampoganho();
                Toast.makeText(getActivity(),"Ganho salvo com sucesso!", Toast.LENGTH_LONG).show();
                voltarTelaPrincipal();
            }else{
                // menssagem com Snackbar
                Snackbar.make(view,"Todos os campos devem ser preenchidos!", Snackbar.LENGTH_LONG).setAction("Ok",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                return;
            }
        }else if (view.getId() == R.id.btn_Cancelar){
            voltarTelaPrincipal();
        }
    }

    // Metodo para pegar os dados dos campos de ganho
    private void dadosDoCampoganho(){
        float valor = Float.parseFloat(valorText.getText().toString());
        String descricao = descricaoText.getText().toString();
        String data = dataText.getText().toString();
        String categoria = spinner_categoria_list.getSelectedItem().toString();
        GanhoController.novoGanho(valor, descricao, data, categoria);
    }

    //Metodo para voltar a tela principal apos salvar oganho
    private void voltarTelaPrincipal(){
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MainFragment()).commit();
    }

    //Metodo para listar as categorias de ganhos
    private void setCategoriaSpinner(Context context) {
        ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categoria_ganho)
        );
        //definindo o tema para a lista de categria
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner_categoria_list.setAdapter(categoriaAdapter);
    }

    //Metod para colocar a data atual no campo data do ganho
    private void setdateField() {
        // usando a biblioteca Calendar
        Calendar calendar = Calendar.getInstance();

        //declarando variavs ano mes dia
        final int ano = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        //Convertendo para string as variavies ano mes dia
        String dataString = Integer.toString(dia) + '/' + Integer.toString(mes + 1) + '/' + Integer.toString(ano);

        dataText.setText(dataString);
        dataText.setKeyListener(null);// nao deixa editar a data no campo

        dataText.setOnClickListener(new View.OnClickListener() {
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
                String dataString = Integer.toString(dia)+'/'+ Integer.toString(mes+1)+'/'+Integer.toString(ano);
                dataText.setText(dataString);
            }
        };
    }
}
