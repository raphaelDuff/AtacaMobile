package com.example.cristhianc.atacamobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Produto prod;
    private TextView tvQtd;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private String strCodigoProduto;
    public Bitmap bitmapBackground;
    private int headerLayoutHeight;
    private int headerLayoutWidth;

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
        progressBar = (ProgressBar)findViewById(R.id.progressBar_detalhes);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();

        strCodigoProduto = getIntent().getStringExtra("atacamobile-cod");

        carregarProduto();

        carregarViews();

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
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout_detalhes_body);

        prodNome.setVisibility(View.VISIBLE);
        prodPreco.setVisibility(View.VISIBLE);
        prodDesc.setVisibility(View.VISIBLE);
        prodCodigo.setVisibility(View.VISIBLE);
        prodValidade.setVisibility(View.VISIBLE);
        prodDetalhes.setVisibility(View.VISIBLE);
        rl.setVisibility(View.VISIBLE);
        prodNome.setText(p.getNome());
        prodPreco.setText(String.format("R$ %.2f", p.getValor()));
        prodDesc.setText(String.format("/%s", p.getDesc()));
        prodCodigo.setText(p.getCodigo());
        prodValidade.setText(p.getDataValidade());
        prodDetalhes.setText(p.getInfo());

    }


    protected void carregarProduto(){
        try {

            ConnectivityManager cm =
                    (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if(!isConnected){
                Helper.mostrarMensagem("Nenhuma conexao com a internet", getApplicationContext());
            }else {

                mDatabase.child("produtos").orderByChild("codigo").equalTo(strCodigoProduto).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Helper.mostrarMensagem("Produto não encontrado", getApplicationContext());
                            finish();
                        } else {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                prod = child.getValue(Produto.class);
                                progressBar.setVisibility(View.GONE);
                                setProdutoTela(prod);
                                setImagemProduto(prod.getImgCaminho());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError firebaseError) {

                    }
                });

            }

        }catch (Exception e){
            String error = e.getMessage();
        }

    }

    protected void setImagemProduto(String nomeImagem){
        Glide.with(DetalhesProdutoActivity.this)
                .using(new FirebaseImageLoader())
                .load(mStorage.child("imagens/" + nomeImagem))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {

                        setHeaderBackground(resource, Helper.getLarguraTela(DetalhesProdutoActivity.this));
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        Helper.mostrarMensagem("Erro ao carregar imagem", DetalhesProdutoActivity.this);
                    }
                });
    }


    public void setHeaderBackground(Bitmap bitmap, int screenWidth){

        int largura = screenWidth;
        int altura = (bitmap.getWidth()/bitmap.getHeight()) * screenWidth;

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getApplicationContext().getResources(), Bitmap.createScaledBitmap(bitmap,largura, altura,true ));
        bitmapDrawable.setAlpha(50);

        bitmapDrawable.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CLIP_VERTICAL);


        ColorDrawable color = new ColorDrawable(0xFFFFD900);

        Drawable[] layers = {color, bitmapDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout_detalhes_header);
        rl.setBackground(layerDrawable);
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
            Helper.mostrarMensagem("Erro ao adicionar ao carrinho.", getApplicationContext());
        }
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
