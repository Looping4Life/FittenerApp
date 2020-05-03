package com.example.fittenerapp;
import java.util.ArrayList;

public class ListHolder {
    private static final ListHolder listHolder = new ListHolder();
    private ArrayList<Entry> entryList;
    private int listSize = 0;
    private int height = 0;

    public static ListHolder getInstance(){
        return listHolder;
    }
    private ListHolder(){
        entryList = new ArrayList<>();
    }
    public void AddEntry(Entry e){
        entryList.add(e);
    }
    public ArrayList<Entry> getEntryList(){
        return entryList;
    }
    public void setListSize(int size){
        listSize = size;
    }
    public int getListSize(){
        return listSize;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }
    public void reset(){
        entryList.clear();
        listSize = 0;
        height = 0;
    }
}
