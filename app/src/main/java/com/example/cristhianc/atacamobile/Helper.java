package com.example.cristhianc.atacamobile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cristhianc on 10/31/2017.
 */

public class Helper {

    protected static void mostrarMensagem(String msg, Context context){

        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    protected static void inserirBD(){
        //Produto p = new Produto("111111", "Sabão em pó OMO Multiação", "12/12/2020", "2kg", 19.60, "Lorem ipsum dolor", "omo.jpg");

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        //db.child("produtos").push().setValue(p);
    }

    protected static int getLarguraTela(Activity context){
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}
