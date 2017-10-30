package com.example.cristhianc.atacamobile;

/**
 * Created by cristhianc on 10/22/2017.
 */

public class Produto {

    private static int id = 0;
    private String nome;
    private String dataValidade;
    private String desc;
    private String info;
    private double valor;

    public Produto(){
        this.id = ++id;
    }

    public Produto(String nome, String dataValidade, String desc, double valor,String info){
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.desc = desc;
        this.valor = valor;
        this.id = ++id;
        this.info = info;
    }


    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString(){
        return nome + " - " + valor;
    }

}
