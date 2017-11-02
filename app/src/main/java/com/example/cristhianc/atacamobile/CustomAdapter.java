package com.example.cristhianc.atacamobile;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cristhianc on 10/28/2017.
 */

public class CustomAdapter extends ArrayAdapter<CarrinhoItem> implements View.OnClickListener{

    private ArrayList<CarrinhoItem> dataSet;
    Context mContext;
    FragmentManager fm;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView editBtn;
        ImageView rmvBtn;
        TextView total;
    }

    public CustomAdapter(ArrayList<CarrinhoItem> data, Context context) {
        super(context, R.layout.item_lista, data);
        this.dataSet = data;
        this.mContext = context;

    }

    public ArrayList<CarrinhoItem> getDataSet(){
        return this.dataSet;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();


        switch (v.getId())
        {
            case R.id.item_edit:
                Object object = getItem(position);
                CarrinhoItem dataModel = (CarrinhoItem) object;
                showDialog(dataModel, position);
//                Snackbar.make(v, "Teste", Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
                break;
            case R.id.item_remove:

                removerLista(position);
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CarrinhoItem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        Context context = parent.getContext();
        fm = ((Activity) context).getFragmentManager();

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_lista, null);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.editBtn = (ImageView) convertView.findViewById(R.id.item_edit);
            viewHolder.rmvBtn = (ImageView) convertView.findViewById(R.id.item_remove);
            viewHolder.total = (TextView) convertView.findViewById(R.id.subtotal);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.txtName.setText(dataModel.getProd().getNome());
        viewHolder.txtType.setText(String.format("%d x %.2f = R$ %.2f", dataModel.getQuantidade(), dataModel.getProd().getValor(),  dataModel.getSubtotal()));
        viewHolder.editBtn.setOnClickListener(this);
        viewHolder.editBtn.setTag(position);

        viewHolder.rmvBtn.setOnClickListener(this);
        viewHolder.rmvBtn.setTag(position);

        //viewHolder.total.setText(String.format("R$ %.2f", getTotal()));
        // Return the completed view to render on screen
        return convertView;
    }

    protected void removerLista(int pos){
        Object object= getItem(pos);
        CarrinhoItem dataModel=(CarrinhoItem) object;
        remove(dataModel);
        notifyDataSetChanged();

    }
    public void updateObjLista(int pos, int quantidade){
        Object object = getItem(pos);
        CarrinhoItem dataModel = (CarrinhoItem) object;

        dataModel.setQuantidade(quantidade);
        dataSet.set(pos, dataModel);

        notifyDataSetChanged();

    }

    protected double getTotal(){
        double total = 0;
        for(int i = 0; i < getCount(); i++){
            CarrinhoItem ci = getItem(i);
            total += ci.getSubtotal();
        }

        return total;
    }


    public void showDialog(CarrinhoItem CI, int pos) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = CarrinhoEditar.newInstance(CI, pos);
        newFragment.setTargetFragment(newFragment,1 );

        newFragment.show(ft, "dialog");
    }

}