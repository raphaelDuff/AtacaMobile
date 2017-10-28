package com.example.cristhianc.atacamobile;

import android.content.Context;
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

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
    }

    public CustomAdapter(ArrayList<CarrinhoItem> data, Context context) {
        super(context, R.layout.item_lista, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CarrinhoItem dataModel=(CarrinhoItem) object;

        switch (v.getId())
        {
            case R.id.item_edit:
                Snackbar.make(v, "Teste", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
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

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_lista, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.txtName.setText(dataModel.getProd().getNome() + " - " + dataModel.getQuantidade());
        viewHolder.txtType.setText(String.format("R$ %.2f", dataModel.getProd().getValor() * dataModel.getQuantidade()));
        // Return the completed view to render on screen
        return convertView;
    }
}
