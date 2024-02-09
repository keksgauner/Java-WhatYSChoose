package de.keksgauner.whatyschoose.utlis;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.keksgauner.whatyschoose.R;

public class Adapter extends ArrayAdapter<String> {
    List<String> words;
    int selectedPosition = -1;
    int bgColor;

    public Adapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.words = objects;
        this.bgColor = context.getResources().getColor(R.color.colorAccent, context.getTheme());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == selectedPosition) {
            view.setBackgroundColor(this.bgColor);
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