package com.example.cristhianc.atacamobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetalhesProdutoActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Produto prod;
    TextView tvQtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.voltar_img);

        prod = getProduto();
        carregarViews();
        setProdutoTela(prod);
        configurarBotoes();
        alterarFontes();
    }

    protected void carregarViews(){
        tvQtd = (TextView) findViewById(R.id.quantidade);
    }

    protected void setProdutoTela(Produto p){
        TextView prodNome = (TextView) findViewById(R.id.nomeProduto);
        TextView prodPreco = (TextView) findViewById(R.id.precoProduto);
        TextView prodDesc = (TextView)findViewById(R.id.descProduto);
        TextView prodCodigo = (TextView)findViewById(R.id.codigo);
        TextView prodValidade = (TextView)findViewById(R.id.validade);
        TextView prodDetalhes = (TextView)findViewById(R.id.detalhes);

        prodNome.setText(p.getNome());
        prodPreco.setText(String.format("R$ %.2f", p.getValor()));
        prodDesc.setText(String.format("/%s", p.getDesc()));
        prodCodigo.setText(p.getCodigo());
        prodValidade.setText(p.getDataValidade());
        prodDetalhes.setText(p.getInfo());

    }


    protected Produto getProduto(){
        Intent i = getIntent();
        Produto p = new Gson().fromJson(i.getStringExtra("objProd"), Produto.class);

        return p;

    }

    protected void alterarFontes(){
        TextView tv;

        Typeface fontBoldItalico = Typeface.createFromAsset(getAssets(), "fonts/Karla-BoldItalic.ttf");
        Typeface fontItalico = Typeface.createFromAsset(getAssets(), "fonts/Karla-Italic.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(), "fonts/Karla-Bold.ttf");
        Typeface fontNormal = Typeface.createFromAsset(getAssets(), "fonts/Karla-Regular.ttf");
        tv = (TextView) findViewById(R.id.nomeProduto);
        tv.setTypeface(fontBoldItalico);
        tv = (TextView) findViewById(R.id.precoProduto);
        tv.setTypeface(fontNormal);
        tv = (TextView) findViewById(R.id.validade);
        tv.setTypeface(fontNormal);
        tv = (TextView) findViewById(R.id.codigo);
        tv.setTypeface(fontNormal);

        tv = (TextView) findViewById(R.id.detalhes);
        tv.setTypeface(fontNormal);
        tv = (TextView) findViewById(R.id.selecioneLabel);
        tv.setTypeface(fontItalico);
        tv = (TextView) findViewById(R.id.codigolabel);
        tv.setTypeface(fontItalico);
        tv = (TextView) findViewById(R.id.validadelabel);
        tv.setTypeface(fontItalico);
        tv = (TextView) findViewById(R.id.detalheslabel);
        tv.setTypeface(fontItalico);
        tv = (TextView) findViewById(R.id.descProduto);
        tv.setTypeface(fontBold);
        Button btn = (Button) findViewById(R.id.btn_adicionarCarrinho);
        btn.setTypeface(fontNormal);
    }


    protected void configurarBotoes(){
        Button btn_mais = (Button) findViewById(R.id.btn_mais);
        Button btn_menos = (Button) findViewById(R.id.btn_menos);
        Button btn_adicionarCarrinho = (Button) findViewById(R.id.btn_adicionarCarrinho);

        btn_adicionarCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtd = Integer.parseInt(tvQtd.getText().toString());
                addToCarrinho(prod, qtd);
            }
        });


        btn_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int qtd = Integer.parseInt(tvQtd.getText().toString());

                if(qtd < 99){
                    qtd++;
                    String strQtd = String.valueOf(qtd);
                    tvQtd.setText(strQtd);
                }
            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtd = Integer.parseInt(tvQtd.getText().toString());

                if(qtd > 1){
                    qtd--;
                    String strQtd = String.valueOf(qtd);
                    tvQtd.setText(strQtd);
                }
            }
        });

    }



    protected void addToCarrinho(Produto p, int quantidade){
        Intent intent = new Intent(DetalhesProdutoActivity.this, CarrinhoProdutosActivity.class);

        try {
            Gson gson = new Gson();
            CarrinhoItem ci = new CarrinhoItem();
            ci.setProd(p);
            ci.setQuantidade(quantidade);

            String strListaCarrinho = sp.getString("listaCarrinho", "");
            ArrayList<CarrinhoItem> itensCarrinho = new ArrayList<CarrinhoItem>();

            if(!strListaCarrinho.equals("")){
                itensCarrinho = new Gson().fromJson(strListaCarrinho, new TypeToken<List<CarrinhoItem>>(){}.getType());
            }

            boolean achouIgual = false;
            for(CarrinhoItem item : itensCarrinho){
                if(ci.getProd().equals(item.getProd())){
                    item.setQuantidade(item.getQuantidade() + ci.getQuantidade());
                    achouIgual = true;
                    break;
                }
            }

            if(!achouIgual) {
                itensCarrinho.add(ci);
            }
            editor.putString("listaCarrinho", new Gson().toJson(itensCarrinho));
            editor.commit();

            startActivity(intent);
        }catch (Exception e){
            mostrarMensagem("Erro ao adicionar ao carrinho.");
        }
    }

    protected void mostrarMensagem(String msg){

        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
        toast.show();
    }

    protected void initCarrinho(){
        Intent intent = new Intent(DetalhesProdutoActivity.this, CarrinhoProdutosActivity.class);
        startActivity(intent);
    }

    protected CarrinhoItem getCarrinhoItem(){
        TextView quantidade = (TextView)findViewById(R.id.quantidade);
        int qtd = Integer.parseInt(quantidade.getText().toString());
        CarrinhoItem item = new CarrinhoItem();

        item.setProd(prod);
        item.setQuantidade(qtd);

        return item;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.carrinho:
                initCarrinho();
                return true;
            case android.R.id.home:
                finish();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
