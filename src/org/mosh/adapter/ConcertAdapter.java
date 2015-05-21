package org.mosh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.mosh.R;
import org.mosh.domain.Concert;

import java.util.ArrayList;

/**
 * Created by crodriguez on 15/05/15.
 */
public class ConcertAdapter extends ArrayAdapter<Concert> {

    public ConcertAdapter(Context context, ArrayList<Concert> concerts) {
        super(context, R.layout.list_featured, concerts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Concert concert = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_featured, parent, false);
        }

        // Lookup view for data population
        TextView concertNameTextView = (TextView) convertView.findViewById(R.id.concertName);
        TextView concertDateTextView = (TextView) convertView.findViewById(R.id.concertDate);

        // Populate the data into the template view using the data object
        concertNameTextView.setText(concert.getName());
        concertDateTextView.setText(concert.getDate());

        // Return the completed view to render on screen
        return convertView;

    }
}
