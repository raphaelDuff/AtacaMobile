package com.example.cristhianc.atacamobile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristhianc on 10/22/2017.
 */

public class Carrinho {

    private LinkedList<Produto> produtos;

    public Carrinho(){
        produtos = new LinkedList<Produto>();
    }

    public void addCarinho(Produto p){
        produtos.add(p);
    }

    public void deleteCarrinho(Produto p){
        produtos.remove(p);
    }

    public LinkedList<Produto> getCarrinho(){
        return produtos;
    }
}
