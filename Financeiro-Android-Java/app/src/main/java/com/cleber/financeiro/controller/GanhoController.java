package com.cleber.financeiro.controller;

import com.cleber.financeiro.helpers.ConverteData;
import com.cleber.financeiro.model.Ganho;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class GanhoController {
    public static void  novoGanho(float valor, String descricao, String data, String categoria){
        Calendar calendarData = ConverteData.textoData(data);

        //instanciando objetos para salvar ganho
        Ganho ganho = new Ganho(descricao,
                categoria,
                valor,
                calendarData.get(Calendar.DAY_OF_MONTH),
                calendarData.get(Calendar.MONTH)+1,
                calendarData.get(Calendar.YEAR));
        ganho.saveGanho();
    }

    // Metodo para rotornar o ganho total por mes
    public static float retorneTotalMes(){
        Calendar calendardataMes = Calendar.getInstance();

        int mes = calendardataMes.get(Calendar.MONTH)+1;
        int ano = calendardataMes.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Ganho> ganhos = realm.where(Ganho.class).
                equalTo("mes",mes).
                equalTo("ano",ano).
                findAll();

        for (int i = 0; i < ganhos.size(); i++){
            total += ganhos.get(i).getValor();
        }

        realm.commitTransaction();
        realm.close();
        return total;
    }

    // Metodo para rotornar o ganho total por mes
    public static float retorneMesGanho(int mes){
        Calendar calendardataMes = Calendar.getInstance();

        int ano = calendardataMes.get(Calendar.YEAR);
        float total = 0.0f;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Ganho> ganhos = realm.where(Ganho.class).
                equalTo("mes",mes).
                equalTo("ano",ano).
                findAll();

        for (int i = 0; i < ganhos.size(); i++){
            total += ganhos.get(i).getValor();
        }

        realm.commitTransaction();
        realm.close();
        return total;
    }
}
