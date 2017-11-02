package com.example.cristhianc.atacamobile;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cristhianc on 10/31/2017.
 */

public class Helper {

    protected static void mostrarMensagem(String msg, Context context){

        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }
}
