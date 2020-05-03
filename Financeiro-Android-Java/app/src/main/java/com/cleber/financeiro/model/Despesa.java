package com.cleber.financeiro.model;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

//Model de despesa
public class Despesa extends RealmObject {

    @PrimaryKey
    @Required
    //cada despesa tera seu id unico usando o metodo UUID
    private String id = UUID.randomUUID().toString();

    @Required
    private String descricao;

    @Required
    private String categoria;

    private double valor;

    private int dia, mes, ano;

    public String getDescricao() {
        return descricao;
    }

    public Despesa(){

    }

    public void saveDespensa(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(this);// salvando as despesa
        realm.commitTransaction();
        realm.close();
    }

    public Despesa(String id, String descricao, String categoria, double valor, int dia, int mes, int ano) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Despesa(String descricao, String categoria, double valor, int dia, int mes, int ano) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
