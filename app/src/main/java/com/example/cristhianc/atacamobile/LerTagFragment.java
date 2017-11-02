package com.example.cristhianc.atacamobile;

/**
 * Created by cristhianc on 11/1/2017.
 */

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

public class LerTagFragment extends DialogFragment {

    public static final String TAG = "atacamobile-cod";


    private TextView mTvMessage;
    private FragmentDisplay fragmentDisplay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lertag,container,false);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentDisplay = (MainActivity) context;
        fragmentDisplay.onAbriuFragment();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentDisplay.onFechouFragment();

    }


}