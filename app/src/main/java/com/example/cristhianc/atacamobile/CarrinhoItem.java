package com.example.cristhianc.atacamobile;

/**
 * Created by cristhianc on 10/28/2017.
 */

public class CarrinhoItem {

    private Produto prod;
    private int quantidade;

    public CarrinhoItem(){
        prod = new Produto();
    }
    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

}
