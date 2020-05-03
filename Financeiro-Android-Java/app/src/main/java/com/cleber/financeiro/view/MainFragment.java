package com.cleber.financeiro.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.DespesaController;
import com.cleber.financeiro.controller.GanhoController;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Ganhos(view);
        Despesas(view);
        Economia(view);

        return view;
    }

    private void Ganhos(View view) {
        TextView txtGanho = view.findViewById(R.id.txtGanhos);// pegando referencia
        float totalGanhos = GanhoController.retorneTotalMes();// pegando total de gastos no mes
        String totalGanhoTxt = String.format("%.2f",totalGanhos);// formatando para duas casas decimais
        txtGanho.setText(getString(R.string.cifrao) + " " + totalGanhoTxt);// define o total no texto
    }

    private void Despesas(View view) {
        TextView txtGastos = view.findViewById(R.id.txtGastos);// pegando referencia
        float totalGastos = DespesaController.retorneTotalMes();// pegando total de gastos no mes
        String totalGastosTxt = String.format("%.2f",totalGastos);// formatando para duas casas decimais
        txtGastos.setText(getString(R.string.cifrao) + " " + totalGastosTxt);// define o total no texto
    }

    private void Economia(View view){
        float totalGanhos = GanhoController.retorneTotalMes();// pegando total de gastos no mes
        float totalGastos = DespesaController.retorneTotalMes();// pegando total de gastos no mes
        TextView txtEconomia = view.findViewById(R.id.txtEconomia);
        float totalEconomia = totalGanhos - totalGastos;
        String txtTotalEconomia = String.format("%.2f",totalEconomia);// formatando para duas casas decimais
        txtEconomia.setText("R$" + " " + txtTotalEconomia);
        if (totalEconomia > 0){
            //Define a cor azul para positivo
            txtEconomia.setTextColor(getResources().getColor(R.color.economia_positiva));
        }else if (totalEconomia < 0){
            //Define a cor vermelha para negativo
            txtEconomia.setTextColor(getResources().getColor(R.color.economia_negativa));
        }
    }
}
