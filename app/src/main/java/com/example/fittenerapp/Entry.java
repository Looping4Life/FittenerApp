package com.example.fittenerapp;

/**
 * Class for the user's details
 * @author Janne Kaukua
 * @version 0.2 05/05/2020
 */
public class Entry {
    public String date;
    public float weight;
    public int height;

    /**
     * Initializes an entry object
     * @param date
     * @param weight
     * @param height
     */
    public Entry(String date, float weight, int height){
        this.date = date;
        this.weight = weight;
        this.height = height;
    }

    /**
     * For displaying date in the calendar list
     * @return
     */
    public String toString(){
        return date;
    }
}
