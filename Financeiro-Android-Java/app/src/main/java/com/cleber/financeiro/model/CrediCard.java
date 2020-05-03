package com.cleber.financeiro.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class CrediCard extends RealmObject {

    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    private String bandeira;
    private String numeroCartao;
    private String tiutlar;
    private double limite;
    private double limiteAtual;

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(this);
        realm.commitTransaction();
        realm.close();
    }

    public CrediCard(){

    }

    public CrediCard(String bandeira, String numeroCartao, String tiutlar, double limite, double limiteAtual) {
        this.bandeira = bandeira;
        this.numeroCartao = numeroCartao;
        this.tiutlar = tiutlar;
        this.limite = limite;
        this.limiteAtual = limiteAtual;
    }

    public String getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public double getLimiteAtual() {
        return limiteAtual;
    }

    public void setLimiteAtual(double limiteAtual) {
        this.limiteAtual = limiteAtual;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getTiutlar() {
        return tiutlar;
    }

    public void setTiutlar(String tiutlar) {
        this.tiutlar = tiutlar;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
