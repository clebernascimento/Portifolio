package com.cleber.financeiro.controller;

import com.cleber.financeiro.model.CrediCard;

import io.realm.Realm;

public class CartaoController {

    public static void createCartao(String bandeira, String numeroCartao, String titular, double limite){
        CrediCard cartaoCredito = new CrediCard(bandeira, numeroCartao, titular, limite, limite);
        cartaoCredito.save();
    }

    public static CrediCard get(){
        CrediCard crediCard;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CrediCard crediCardTemp = realm.where(CrediCard.class).findFirst();

        if (crediCardTemp != null){
            crediCard = new CrediCard(crediCardTemp.getBandeira(), crediCardTemp.getNumeroCartao(), crediCardTemp.getTiutlar(), crediCardTemp.getLimite(), crediCardTemp.getLimiteAtual());

        }else{
            crediCard = null;
        }

        realm.commitTransaction();
        realm.close();
        return crediCard;
    }
    public static void LimiteCredito(double valor){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CrediCard crediCard = realm.where(CrediCard.class).findFirst();
        crediCard.setLimiteAtual(crediCard.getLimiteAtual() - valor);

        realm.commitTransaction();
        realm.close();
    }

    public static void ReseteLimiteCredito(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        CrediCard crediCard = realm.where(CrediCard.class).findFirst();
        crediCard.setLimiteAtual(crediCard.getLimite());

        realm.commitTransaction();
        realm.close();
    }

}
