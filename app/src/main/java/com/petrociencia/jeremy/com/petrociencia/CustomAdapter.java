package com.petrociencia.jeremy.com.petrociencia;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JEREMY on 26-03-2017.
 */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;

public class CustomAdapter extends ArrayAdapter<Notas> {

    private final Context context;
    private final ArrayList<Notas> itemsArrayList;

    public CustomAdapter(Context context, ArrayList<Notas> itemsArrayList) {

        super(context, R.layout.item_todo, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.item_todo, parent, false);

        // 3. Get the two text view from the rowView
        ImageButton buttton = (ImageButton) rowView.findViewById(R.id.task_confirm);
        ImageButton button = (ImageButton) rowView.findViewById(R.id.task_edit);

        TextView id = (TextView) rowView.findViewById(R.id.idlist);
        TextView text = (TextView) rowView.findViewById(R.id.task_title);

        if(itemsArrayList.get(position).getTerminado() == 1 )
        {

            buttton.setBackgroundResource(R.drawable.com_facebook_button_like_icon_selected);
            buttton.setEnabled(false);
            buttton.setClickable(false);
            button.setEnabled(false);
            button.setClickable(false);
        }

        // 4. Set the text for textView
        id.setText(String.valueOf(itemsArrayList.get(position).getIdNota()));
        text.setText(itemsArrayList.get(position).getTexto());

        // 5. retrn rowView
        return rowView;
    }
}