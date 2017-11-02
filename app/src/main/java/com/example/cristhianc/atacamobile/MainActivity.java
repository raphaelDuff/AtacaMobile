package com.example.cristhianc.atacamobile;

import android.Manifest;
import android.app.PendingIntent;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.graphics.drawable.VectorDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;

import android.os.Build;
import android.os.Parcelable;

import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements FragmentDisplay {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private NfcAdapter mNfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] nfcIntentFilter;
    private Produto prod;
    private DatabaseReference mDatabase;
    private ProgressBar pb;
    private Button bt;
    private LerTagFragment fragment;
    private boolean leituraLiberada = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarBotoes();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.NFC) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.NFC}, 1);
            }

            if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }

        }

        initNFC();


    }

    public void initNFC(){
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {
            techDetected.addDataType("application/com.example.cristhianc.atacamobile");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }

        nfcIntentFilter = new IntentFilter[]{techDetected, tagDetected, ndefDetected};
        pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mNfcAdapter!= null) {
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);
        }else{
            Helper.mostrarMensagem("NFC não detectado!", getApplicationContext());
        }
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

            Intent intent = new Intent(MainActivity.this, CarrinhoProdutosActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAbriuFragment() {

        leituraLiberada = true;
    }

    @Override   
    public void onFechouFragment() {

        leituraLiberada  = false;

    }

    public void inicializarBotoes(){

        bt = (Button) findViewById(R.id.btn_lerTag);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFragment();
            }
        });

    }

    public void mostrarFragment(){
        fragment = (LerTagFragment) getFragmentManager().findFragmentByTag(LerTagFragment.TAG);

        if(fragment == null){
            fragment = new LerTagFragment();
        }

        fragment.show(getFragmentManager(), LerTagFragment.TAG);
    }


    protected void initDetalhesProdutos(){

        Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
        Gson gson = new Gson();
        String strProd = gson.toJson(prod);
        intent.putExtra("objProd", strProd);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        //Helper.mostrarMensagem("LEU NFC!!!!", getApplicationContext());

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(leituraLiberada) {

            fragment.dismiss();

            if (intent != null &&
                    (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction()) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction()) || NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction()))) {
                Parcelable[] rawMsg = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                if (rawMsg != null) {
                    String str;
                    NdefMessage[] mensagens = new NdefMessage[rawMsg.length];
                    for (int i = 0; i < mensagens.length; i++) {
                        mensagens[i] = (NdefMessage) rawMsg[i];
                        try {
                            str = new String(mensagens[i].getRecords()[0].getPayload(), "UTF-8");
                            if (!str.startsWith("atacamobile-cod:")) {
                                Helper.mostrarMensagem("Erro ao ler tag", getApplicationContext());
                                finish();
                            }

                            String[] strTag = str.split(":");

                            Intent intentDetalhes = new Intent(MainActivity.this, DetalhesProdutoActivity.class);

                            intentDetalhes.putExtra("atacamobile-cod", strTag[1]);

                            startActivity(intentDetalhes);


                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
