package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by PALLAVI on 10-04-2020.
 */

public class ReportAdapter extends ArrayAdapter<Report> {

//    DELIMITER is used for splitting the OriginalLocation
    private final String DELIMITER="of";
    DecimalFormat decimalFormat;
    private double magnitudeOfEarthquake;

    private String url;
//    Constructor
    ReportAdapter(Context context, ArrayList<Report> report){
        super(context,0,report);
        decimalFormat=new DecimalFormat("0.0");
    }
    /**
     * Return the formatted date string (i.e. "Jan 23, 2020") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,parent,false);
        }
        //report holds earthquake report for item at position
        final Report report =getItem(position);

        TextView magnitude=(TextView)listItemView.findViewById(R.id.magnitude);
        magnitudeOfEarthquake=report.getMagnitude();
        magnitude.setText(decimalFormat.format(magnitudeOfEarthquake));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitudeOfEarthquake);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String[] originalLocation=report.getPlace().split(DELIMITER);

        TextView location_offset=(TextView)listItemView.findViewById(R.id.location_offset) ;
        TextView primary_location=(TextView)listItemView.findViewById(R.id.primary_location);

        try{
            primary_location.setText(originalLocation[1]);
            location_offset.setText(originalLocation[0].concat(DELIMITER).toUpperCase());
        }
        catch(IndexOutOfBoundsException e){
            location_offset.setText(getContext().getString(R.string.near_the));
            primary_location.setText(originalLocation[0]);
        }


        TextView date=(TextView)listItemView.findViewById(R.id.date);
        date.setText(formatDate(report.getDate()));

        TextView time=(TextView)listItemView.findViewById(R.id.time);
        time.setText(formatTime(report.getDate()));


        return listItemView;

    }
}
