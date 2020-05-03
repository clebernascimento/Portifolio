package com.cleber.financeiro.controller;

import com.cleber.financeiro.helpers.ConverteData;
import com.cleber.financeiro.model.Despesa;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class DespesaController {

    public static void  novaDespesa(float valor, String descricao, String data, String categoria){
        Calendar calendarData = ConverteData.textoData(data);

        //instanciando objetos para salvar despesa
        Despesa despesa = new Despesa(descricao,
                categoria,
                valor,
                calendarData.get(Calendar.DAY_OF_MONTH),
                calendarData.get(Calendar.MONTH)+1,
                calendarData.get(Calendar.YEAR));
        despesa.saveDespensa();
    }

    // Metodo para rotornar o gasto total por mes
    public static float retorneTotalMes(){
        Calendar calendardataMes = Calendar.getInstance();

        int mes = calendardataMes.get(Calendar.MONTH)+1;
        int ano = calendardataMes.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Despesa> despesas = realm.where(Despesa.class).
                equalTo("mes",mes).
                equalTo("ano",ano).
                findAll();

        for (int i = 0; i < despesas.size(); i++){
            total += despesas.get(i).getValor();
        }

        realm.commitTransaction();
        realm.close();
        return total;
    }
    // Metodo para rotornar o gasto total por mes
    public static float retorneMesDespesa(int mes){
        Calendar calendardataMes = Calendar.getInstance();


        int ano = calendardataMes.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Despesa> despesas = realm.where(Despesa.class).
                equalTo("mes",mes).
                equalTo("ano",ano).
                findAll();

        for (int i = 0; i < despesas.size(); i++){
            total += despesas.get(i).getValor();
        }

        realm.commitTransaction();
        realm.close();
        return total;
    }
}
