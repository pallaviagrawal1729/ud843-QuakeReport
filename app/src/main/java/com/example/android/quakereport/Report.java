package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by PALLAVI on 10-04-2020.
 */

public class Report {
    private double magnitude;
    private String place;
    private String url;
    private Date date;
    Report( double mag, String location,Date occurenceDate,String url1){
        magnitude=mag;
        place=location;
        date=occurenceDate;
        url=url1;
    }
    public Double getMagnitude(){
        return magnitude;
    }
    public String getPlace(){
        return place;
    }
    public Date getDate(){
        return date;
    }
    public String getURL(){return  url;}

}
