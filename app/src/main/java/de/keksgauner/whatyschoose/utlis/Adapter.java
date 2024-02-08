package de.keksgauner.whatyschoose.utlis;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class Adapter extends ArrayAdapter<String> {
    List<String> words;
    int selectedPosition = -1;

    public Adapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.words = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == selectedPosition) {
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        return view;
    }

    // Methode zum Setzen der ausgewählten Position
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }
}