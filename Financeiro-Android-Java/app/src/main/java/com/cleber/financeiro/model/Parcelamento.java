package com.cleber.financeiro.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Parcelamento extends RealmObject {

    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    private String descricao;
    private double valor;
    private int totalPart;
    private int currentPart;
    private boolean finalizado;

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(this);

        realm.commitTransaction();
        realm.close();
    }

    public Parcelamento(){

    }

    public Parcelamento(String descricao, double valor, int totalPart, int currentPart, boolean finalizado) {
        this.descricao = descricao;
        this.valor = valor;
        this.totalPart = totalPart;
        this.currentPart = currentPart;
        this.finalizado = finalizado;
    }

    public Parcelamento(String id, String descricao, double valor, int totalPart, int currentPart, boolean finalizado) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.totalPart = totalPart;
        this.currentPart = currentPart;
        this.finalizado = finalizado;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTotalPart() {
        return totalPart;
    }

    public void setTotalPart(int totalPart) {
        this.totalPart = totalPart;
    }

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int currentPart) {
        this.currentPart = currentPart;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
