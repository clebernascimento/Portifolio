package com.cleber.financeiro.view;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleber.financeiro.R;
import com.cleber.financeiro.controller.DespesaController;
import com.cleber.financeiro.controller.GanhoController;
import com.cleber.financeiro.helpers.NomeMes;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelatorioFragment extends Fragment {

    public RelatorioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relatorio, container, false);

        PieChart despesaGrafico = view.findViewById(R.id.grafico_despesa);
        PieChart ganhosGrafico = view.findViewById(R.id.grafico_ganhos);

        setPieChartGrafico(despesaGrafico, 1, "Gastos", "Nenhum gastos");
        setPieChartGrafico(ganhosGrafico, 2, "Ganho", "Nenhum ganho");

        return view;
    }

    private void setPieChartGrafico(PieChart pieChart, int tyoe, String label, String noDataString) {
        //configuracao basica do grafico
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        // valores do grafico
        ArrayList<PieEntry> valores = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            String nome_mes = NomeMes.getNomeMes(i);
            float valor;
            switch (tyoe) {
                case 1:
                    valor = DespesaController.retorneMesDespesa(i);
                    break;
                case 2:
                    valor = GanhoController.retorneMesGanho(i);
                    break;
                default:
                    valor = 0;
                    break;
            }

            if (valor > 0.5) {
                valores.add(new PieEntry(valor, nome_mes));
            }
        }

        if (valores.size() > 0) {

            PieDataSet pieDataSet = new PieDataSet(valores, label);
            pieDataSet.setSliceSpace(1f);
            pieDataSet.setSelectionShift(1f);

            PieData pieData = new PieData(pieDataSet);
            pieData.setValueTextSize(16f);
            pieData.setValueTextColor(Color.WHITE);

            pieChart.setData(pieData);

        } else {
            pieChart.setNoDataText(noDataString);
        }
    }
}
