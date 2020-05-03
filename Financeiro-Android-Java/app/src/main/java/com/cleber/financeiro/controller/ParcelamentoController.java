package com.cleber.financeiro.controller;

import com.cleber.financeiro.model.Parcelamento;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ParcelamentoController {

    public static void createNovoParcelamnento(String descricao, double valor, int parcelas) {
        Parcelamento parcela = new Parcelamento(descricao, valor, parcelas, 0, false);
        parcela.save();
    }

    public static List<Parcelamento> getAll() {
        List<Parcelamento> define_List = new ArrayList<Parcelamento>();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<Parcelamento> list_temporaria = realm.where(Parcelamento.class).equalTo("finalizado", false).findAll();

        for (int i = 0; i < list_temporaria.size(); i++) {
            Parcelamento parcelamento = new Parcelamento(
                    list_temporaria.get(i).getId(),
                    list_temporaria.get(i).getDescricao(),
                    list_temporaria.get(i).getValor(),
                    list_temporaria.get(i).getTotalPart(),
                    list_temporaria.get(i).getCurrentPart(),
                    list_temporaria.get(i).isFinalizado()
            );
            define_List.add(parcelamento);
        }

        realm.commitTransaction();
        realm.close();

        return define_List;
    }

    public static boolean incrementoParcelas(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Parcelamento item = realm.where(Parcelamento.class).equalTo("id", id).findFirst();

        int atual = item.getCurrentPart();
        // na ultima parcela
        if (atual+1 >= item.getTotalPart()) {
            item.setCurrentPart((item.getCurrentPart() + 1));
            item.setFinalizado(true);

            realm.commitTransaction();
            realm.close();
            return true;

            // so incrementa
        }else{
            item.setCurrentPart((item.getCurrentPart() + 1));

            realm.commitTransaction();
            realm.close();
            return false;
        }
    }
}
