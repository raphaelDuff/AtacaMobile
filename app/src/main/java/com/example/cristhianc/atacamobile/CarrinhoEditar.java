package com.example.cristhianc.atacamobile;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lucas on 01/11/2017.
 */

public class CarrinhoEditar extends DialogFragment {

    private int Qtd;
    private String Nome;
    static int posicaoItem;
    private EditText ETQtd;

    static CarrinhoEditar newInstance(CarrinhoItem CI, int pos)
    {
        posicaoItem = pos;
        CarrinhoEditar CE = new CarrinhoEditar();
        Bundle args = new Bundle();
        args.putInt("QUANTIDADE", CI.getQuantidade());
        args.putString("PRODUTO", CI.getProd().getNome());
        args.putString("CODIGO", CI.getProd().getCodigo());
        CE.setArguments(args);

        return CE;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Stuff to do, dependent on requestCode and resultCode
        if(requestCode == 1) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if(resultCode == 1) { // 1 is an arbitrary number, can be any int
                // Now do what you need to do after the dialog dismisses.
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Qtd = getArguments().getInt("QUANTIDADE");
        Nome = getArguments().getString("PRODUTO");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragmentdialog_editar_carrinho, container, false);
        ETQtd = (EditText)v.findViewById(R.id.edit_qtd);

        ETQtd.setEnabled(false);
        final View Title = v.findViewById(R.id.txtTitulo);

        Button btnIncrement = (Button)v.findViewById(R.id.btn_increment);
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qtd = Integer.parseInt(ETQtd.getText().toString());
                if(Qtd<99){
                    Qtd++;
                    ETQtd.setText(String.valueOf(Qtd));
                }
            }
        });

        Button btnDecrement = (Button)v.findViewById(R.id.btn_decrement);
        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qtd = Integer.parseInt(ETQtd.getText().toString());
                if(Qtd>1)
                {
                    Qtd--;
                    ETQtd.setText(String.valueOf(Qtd));

                }
            }
        });



        Button btnOk = (Button)v.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((CarrinhoProdutosActivity) getActivity()).updateItemCarrinho(posicaoItem, Integer.parseInt(ETQtd.getText().toString()));

                dismiss();

            }
        });

        ((TextView)Title).setText(Nome);
        ETQtd.setText(String.valueOf(Qtd));


        return v;

    }
}