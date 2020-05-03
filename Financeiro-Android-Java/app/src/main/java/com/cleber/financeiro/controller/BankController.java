package com.cleber.financeiro.controller;

import com.cleber.financeiro.model.Banco;

import io.realm.Realm;

public class BankController {

    public static void createConta(String nomeBanco, String titular, String agencia, String conta, String tipoConta, double saldo){
        Banco novo_Banco = new Banco(nomeBanco, titular, agencia, conta, tipoConta, saldo);
        novo_Banco.save();
    }

    //RETORNANDO UM OBJETO DO BANCO
    public static Banco get(){
        Banco info;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Banco tirularConta = realm.where(Banco.class).findFirst();
        if (tirularConta != null){
            info = new Banco( tirularConta.getNomeBanco(), tirularConta.getTitular(),
                    tirularConta.getAgencia(),tirularConta.getConta(), tirularConta.getTipoConta(), tirularConta.getSaldo());
        }else {
            info = null;
        }
        realm.commitTransaction();
        realm.close();
        return info;
    }

    public static double getSaldo(){
        Banco banco = get();
        if (banco != null){
            return banco.getSaldo();
        }else{
            return 0.0;
        }
    }

    public static void deposito(double valor){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Banco meuBanco = realm.where(Banco.class).findFirst();
        meuBanco.setSaldo(meuBanco.getSaldo() + valor);
        realm.commitTransaction();
        realm.close();
    }

    public static void saque(double valor){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Banco meuBanco = realm.where(Banco.class).findFirst();
        meuBanco.setSaldo(meuBanco.getSaldo() - valor);
        realm.commitTransaction();
        realm.close();
    }
}
