package com.example.cristhianc.atacamobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarrinhoProdutosActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView tv_subtotal_label;
    TextView tv_subtotal;
    ListView lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_produtos);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();


        initTVs();
        setFontes();

        atualizarCarrinho();
        updTotal(lista.getAdapter());


        DataSetObserver dso = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updTotal(lista.getAdapter());
                ArrayList<CarrinhoItem> itensCarrinho = ((CustomAdapter) lista.getAdapter()).getDataSet();

                editor.putString("listaCarrinho", new Gson().toJson(itensCarrinho));
                editor.commit();

            }
        };

        ((CustomAdapter) lista.getAdapter()).setNotifyOnChange(true);
        lista.getAdapter().registerDataSetObserver(dso);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Carrinho");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.voltar_img);
    }


    protected void updTotal(ListAdapter adpt){
        double total = 0;
        TextView tv_subtotal = (TextView) findViewById(R.id.subtotal);

        for(int i = 0; i < adpt.getCount(); i++){
            CarrinhoItem ci = (CarrinhoItem) adpt.getItem(i);
            total+= ci.getSubtotal();
        }

        tv_subtotal.setText(String.format("R$ %.2f", total));

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

    protected ArrayList<CarrinhoItem> getListaCarrinho(){
        String strListaCarrinho = sp.getString("listaCarrinho", "");
        ArrayList<CarrinhoItem> itensCarrinho = new ArrayList<CarrinhoItem>();

        if (!strListaCarrinho.equals("")){
            itensCarrinho = new Gson().fromJson(strListaCarrinho, new TypeToken<List<CarrinhoItem>>(){}.getType());
        }

        return itensCarrinho;
    }


    protected void atualizarCarrinho(){
        CarrinhoItem ci = new CarrinhoItem();
        lista = (ListView) findViewById(R.id.lista);

        Gson gson = new Gson();
        if(getIntent()!= null && getIntent().getExtras() != null){
            Bundle b = getIntent().getExtras();
            if(!b.getString("objAddCarrinho").equals(null)){
                ci = gson.fromJson(b.getString("objAddCarrinho"), CarrinhoItem.class);

                ArrayList<CarrinhoItem> itens = getListaCarrinho();
                lista.setAdapter(new CustomAdapter(itens, getApplicationContext()));
                ((CustomAdapter) lista.getAdapter()).add(ci);
                ArrayList<CarrinhoItem> itensCarrinho = ((CustomAdapter) lista.getAdapter()).getDataSet();

                editor.putString("listaCarrinho", new Gson().toJson(itensCarrinho));
                editor.commit();

            }
        }

    }

}
