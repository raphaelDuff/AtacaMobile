package com.example.cristhianc.atacamobile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristhianc on 10/22/2017.
 */

public class Carrinho {

    private LinkedList<CarrinhoItem> produtos;

    public Carrinho(){
        produtos = new LinkedList<CarrinhoItem>();
    }

    public void addCarinho(CarrinhoItem p){
        produtos.add(p);
    }

    public void deleteCarrinho(CarrinhoItem p){
        produtos.remove(p);
    }

    public LinkedList<CarrinhoItem> getCarrinho(){
        return produtos;
    }
}
