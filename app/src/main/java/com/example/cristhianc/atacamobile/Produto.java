package com.example.cristhianc.atacamobile;

/**
 * Created by cristhianc on 10/22/2017.
 */

public class Produto {

    private String codigo;
    private String nome;
    private String dataValidade;
    private String desc;
    private String info;
    private double valor;
    private String imgCaminho;

    public String getImgCaminho() {
        return imgCaminho;
    }

    public void setImgCaminho(String imgCaminho) {
        this.imgCaminho = imgCaminho;
    }



    public Produto(){

    }

    public Produto(String codigo, String nome, String dataValidade, String desc, double valor,String info, String imgCaminho){
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.desc = desc;
        this.valor = valor;
        this.codigo = codigo;
        this.info = info;
        this.imgCaminho = imgCaminho;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object obj){
        boolean igual = false;

        if(obj instanceof Produto){
            Produto p = (Produto) obj;
            igual = (this.getCodigo().equals(p.getCodigo()));
        }

        return igual;
    }
}
