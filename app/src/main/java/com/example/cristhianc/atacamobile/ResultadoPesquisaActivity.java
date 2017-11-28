package com.example.cristhianc.atacamobile;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultadoPesquisaActivity extends AppCompatActivity {

    ListView lista;
    String searchNome;
    DatabaseReference mDatabase;
    final ArrayList<CarrinhoItem> Produtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_pesquisa);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        initLista();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Resultados");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.voltar_img);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object object = lista.getAdapter().getItem(i);
                CarrinhoItem dataModel = (CarrinhoItem) object;
                float coordX = dataModel.getProd().getCoordX();
                float coordY = dataModel.getProd().getCoordY();

                Intent intent = new Intent(ResultadoPesquisaActivity.this,MapaActivity.class);
                intent.putExtra("coordx", coordX);
                intent.putExtra("coordy", coordY);
                startActivity(intent);
            }
        });
    }

    private void initLista() {
        searchNome = getIntent().getStringExtra("nome");

        lista = (ListView) findViewById(R.id.listaResultados);
        carregarProdutos();

    }

    protected void carregarProdutos(){
        try {
//            searchNome = searchNome.substring(0,1).toUpperCase() + searchNome.substring(1);


            ConnectivityManager cm =
                    (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if(!isConnected){
                Helper.mostrarMensagem("Nenhuma conexao com a internet", getApplicationContext());
            }else {
                //ToDo: É necessario que a pesquisa funcione como o Like do SQL, isso será realizado através do webapp em NodeJS que fará a query e retornará para a aplicaçao abaixo
                //Por enquanto o codigo funciona buscando pelo nome EXATO do produto
                mDatabase.child("produtos").orderByChild("nome").equalTo(searchNome).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                                Helper.mostrarMensagem("Produto não encontrado", getApplicationContext());
                                finish();
                        } else {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                Produto prod = child.getValue(Produto.class);
                                CarrinhoItem carrinhoItem = new CarrinhoItem();
                                carrinhoItem.setQuantidade(1);
                                carrinhoItem.setProd(prod);
                                Produtos.add(carrinhoItem);
                            }

                            UpdateLista();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }

        }catch (Exception e){
            String error = e.getMessage();
        }

    }

    private void UpdateLista() {
        lista = (ListView) findViewById(R.id.listaResultados);
        lista.setAdapter(new ResultAdapter(Produtos, getApplicationContext()));
    }


}
