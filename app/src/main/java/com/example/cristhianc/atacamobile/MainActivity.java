package com.example.cristhianc.atacamobile;


import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private NfcAdapter mNfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] nfcIntentFilter;
    private Produto prod;
    private DatabaseReference mDatabase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inicializarBotoes();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        initDebugProd();


        editor.putString("listaCarrinho", "");
        editor.commit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.NFC) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.NFC}, 1);
            }
        }


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);


        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};
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
            CharSequence text = "NFC não detectado!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
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


    public void inicializarBotoes(){

        Button bt = (Button) findViewById(R.id.btn_lerTag);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDetalhesProdutos(prod);
            }
        });
    }


    protected void initDetalhesProdutos(Produto prod){
        Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
        Gson gson = new Gson();
        String strProd = gson.toJson(prod);
        intent.putExtra("objProd", strProd);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        CharSequence text = "LEU NFC!!!!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();


        if(intent != null && (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction()) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction()))){


            Parcelable[] rawMsg =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if(rawMsg != null){
                NdefMessage[] mensagens = new NdefMessage[rawMsg.length];
                for(int i = 0; i < mensagens.length; i++){
                    mensagens[i] = (NdefMessage) rawMsg[i];
                }
            }
        }
    }

    protected void initDebugProd(){



        /*prod = new Produto();
        prod.setCodigo("273717");
        prod.setNome("Cervejas Skol");
        prod.setValor(7.39);
        prod.setDesc("600ml");
        prod.setDataValidade("21/01/2018");
        prod.setInfo("Lorem ipsum dolor hehe teste");
        prod.setImgCaminho("skol4.png");*/

        try {

            mDatabase.child("produtos").orderByChild("codigo").equalTo("273717").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        prod = child.getValue(Produto.class);
                    }
                }
                @Override
                public void onCancelled(DatabaseError firebaseError) {


                }
            });
        }catch (Exception e){
            String error = e.getMessage();
        }
    }
}
