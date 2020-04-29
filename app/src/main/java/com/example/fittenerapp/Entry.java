package com.example.fittenerapp;

public class Entry {
    public String date;
    public float weight;
    public int height;

    public Entry(String date, float weight, int height){
        this.date = date;
        this.weight = weight;
        this.height = height;
    }

    public String toString(){
        return date + " " + weight + " " + height;
    }
}
