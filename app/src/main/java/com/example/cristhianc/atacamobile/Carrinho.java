package com.example.cristhianc.atacamobile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristhianc on 10/22/2017.
 */

public class Carrinho {

    private ArrayList<CarrinhoItem> produtos;

    public Carrinho(){
        produtos = new ArrayList<CarrinhoItem>();
    }

    public void addCarinho(CarrinhoItem p){
        produtos.add(p);
    }

    public void deleteCarrinho(CarrinhoItem p){
        produtos.remove(p);
    }

    public ArrayList<CarrinhoItem> getCarrinho(){
        return produtos;
    }
}
