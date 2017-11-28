package com.example.cristhianc.atacamobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class PesquisarProdutoActivity extends AppCompatActivity {


    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_produto);


        PrimaryDrawerItem mainMenuItem = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_mainmenu);
        PrimaryDrawerItem procureProdutoItem = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_procurar_produto);

        //Setando o menu lateral (drawer menu)
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .withInnerShadow(true)
                .addDrawerItems(new SectionDrawerItem(),mainMenuItem,procureProdutoItem)
                .build();

        //Marcando tela atual como selecionada
        procureProdutoItem.withSetSelected(true);

        mainMenuItem.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent i = new Intent(PesquisarProdutoActivity.this,MainActivity.class);
                startActivity(i);
                return true;

            }
        });

        final EditText txtBusca = (EditText)findViewById(R.id.txtBusca);
        Button btnPesquisar = (Button)findViewById(R.id.btn_pesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PesquisarProdutoActivity.this, ResultadoPesquisaActivity.class);
                i.putExtra("nome",txtBusca.getText().toString());
                startActivity(i);
            }
        });

        txtBusca.setSingleLine(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.carrinho) {

            Intent intent = new Intent(PesquisarProdutoActivity.this, CarrinhoProdutosActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
