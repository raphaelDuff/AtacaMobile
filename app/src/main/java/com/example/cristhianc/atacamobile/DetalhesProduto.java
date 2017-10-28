package com.example.cristhianc.atacamobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class DetalhesProduto extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();

        TextView tv;

        Typeface fontItalico = Typeface.createFromAsset(getAssets(), "fonts/Karla-BoldItalic.ttf");
        Typeface fontNormal = Typeface.createFromAsset(getAssets(), "fonts/Karla-Regular.ttf");
        tv = (TextView) findViewById(R.id.nomeProduto);
        tv.setTypeface(fontItalico);
        tv = (TextView) findViewById(R.id.precoProduto);
        tv.setTypeface(fontNormal);
        tv = (TextView) findViewById(R.id.textView3);
        tv.setTypeface(fontNormal);
        Button btn = (Button) findViewById(R.id.button);
        btn.setTypeface(fontNormal);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.voltar_img);

        String json = sp.getString("carrinhoObj", "");
        Gson gson = new Gson();

        Carrinho car = gson.fromJson(json, Carrinho.class);

        detalharProduto(car.getCarrinho().getFirst());
    }

    public void detalharProduto(Produto p){
        //TextView nome = (TextView) findViewById(R.id.tv_nome_val);
        //TextView codigo = (TextView) findViewById(R.id.tv_codigo_val);
        //TextView valor = (TextView) findViewById(R.id.tv_valor_val);
        //TextView data = (TextView) findViewById(R.id.tv_data_val);

        //nome.setText(p.getNome());
        //codigo.setText(String.valueOf(p.getId()));
        //valor.setText("R$ " + p.getValor());
        //data.setText(p.getDataValidade());
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
                Intent intent = new Intent(DetalhesProduto.this, CarrinhoProdutos.class);
                startActivity(intent);

                return true;
            case android.R.id.home:
                finish();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
