package com.cleber.financeiro.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Banco extends RealmObject {

    //gerando um id para cada cadastro
    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    private String nomeBanco;
    private String titular;
    private String agencia;
    private String conta;
    private String tipoConta;
    private double saldo;

    public Banco(){

    }

    public void save(){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(this);
        realm.commitTransaction();
        realm.close();
    }

    public Banco(String nomeBanco, String titular, String agencia, String conta, String tipoConta, double saldo) {
        this.nomeBanco = nomeBanco;
        this.titular = titular;
        this.agencia = agencia;
        this.conta = conta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
