package com.example.fittenerapp;
import java.util.ArrayList;

public class ListHolder {
    private static final ListHolder listHolder = new ListHolder();
    private ArrayList<Entry> entryList;

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
}
