package com.example.cristhianc.atacamobile;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarrinhoProdutosActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView tv_subtotal_label;
    TextView tv_subtotal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_produtos);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        initTVs();
        setFontes();

        String json = sp.getString("carrinhoObj", "");
        ArrayList<CarrinhoItem> produtos = new ArrayList<CarrinhoItem>();
        CarrinhoItem ci = new CarrinhoItem();
        ci.setProd(new Produto(12, "Cerveja Skol", "","",2.3,""));
        ci.setQuantidade(5);
        produtos.add(ci);
        ci = new CarrinhoItem();
        ci.setProd(new Produto(12, "Sabão em Pó Omo", "","",5.1,""));
        ci.setQuantidade(3);
        produtos.add(ci);
        setSubtotal(produtos);
        ListView lv = (ListView) findViewById(R.id.lista);

        CustomAdapter adapter = new CustomAdapter(produtos, getApplicationContext());
        lv.setAdapter(adapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Carrinho");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.voltar_img);
    }

    protected void setSubtotal(List<CarrinhoItem> l){
        double subtotal = 0;
        TextView tv_subtotal = (TextView) findViewById(R.id.subtotal);

        for (CarrinhoItem carrinhoItem : l) {
            subtotal += carrinhoItem.getProd().getValor() * carrinhoItem.getQuantidade();
        }

        tv_subtotal.setText(String.format("R$ %.2f", subtotal));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case android.R.id.home:
                finish();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    protected void initTVs(){
        tv_subtotal_label = (TextView)findViewById(R.id.subtotal_label);
        tv_subtotal = (TextView)findViewById(R.id.subtotal);
    }

    protected void setFontes(){
        Typeface fontItalico = Typeface.createFromAsset(getAssets(), "fonts/Karla-Italic.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(), "fonts/Karla-Bold.ttf");
        tv_subtotal_label.setTypeface(fontItalico);
        tv_subtotal.setTypeface(fontBold);
    }

}
