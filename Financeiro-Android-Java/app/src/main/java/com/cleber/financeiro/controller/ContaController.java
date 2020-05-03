package com.cleber.financeiro.controller;

import com.cleber.financeiro.helpers.ConverteData;
import com.cleber.financeiro.model.Conta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ContaController {

    public static void createNovo(double valor, String descricao, String data) {
        Calendar calendar = ConverteData.textoData(data);
        Conta conta = new Conta(descricao, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), valor, false);
        conta.save();
    }

    public static List<Conta> getAll() {
        List<Conta> contaList = new ArrayList<Conta>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Conta> tempList = realm.where(Conta.class).equalTo("paga", false).findAll();

        for (int i = 0; i < tempList.size(); i++) {
            Conta conta = new Conta(
                    tempList.get(i).getId(),
                    tempList.get(i).getDescricao(),
                    tempList.get(i).getDia(),
                    tempList.get(i).getMes(),
                    tempList.get(i).getAno(),
                    tempList.get(i).getValor(),
                    tempList.get(i).isPaga()
            );
            contaList.add(conta);
        }

        realm.commitTransaction();
        realm.close();

        return contaList;
    }

    public static void setPagar(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Conta contaPagar = realm.where(Conta.class).equalTo("id",id).findFirst();
        contaPagar.setPaga(true);

        realm.commitTransaction();
        realm.close();
    }
}
