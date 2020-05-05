package com.example.fittenerapp;
import java.util.ArrayList;

/**
 * Class for the user's details
 * @author Janne Kaukua
 * @version 0.2 05/05/2020
 */
public class ListHolder {
    private static final ListHolder listHolder = new ListHolder();
    private ArrayList<Entry> entryList;
    private int listSize = 0;
    private int height = 0;

    /**
     * Gets the ListHolder instance
     * @return
     */
    public static ListHolder getInstance(){
        return listHolder;
    }

    /**
     * Constructor for the ListHolder
     */
    private ListHolder(){
        entryList = new ArrayList<>();
    }

    /**
     * For adding entries to the list
     * @param e for entry
     */
    public void AddEntry(Entry e){
        entryList.add(e);
    }

    /**
     * For getting the list of entries
     * @return
     */
    public ArrayList<Entry> getEntryList(){
        return entryList;
    }

    /**
     * For setting the list size when loading from shared preferences
     * @param size for list length
     */
    public void setListSize(int size){
        listSize = size;
    }

    /**
     * For getting the list size when saving data to shared preferences
     * @return
     */
    public int getListSize(){
        return listSize;
    }

    /**
     * For setting the height
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * For getting the height
     * @return
     */
    public int getHeight(){
        return height;
    }

    /**
     * Reset the singleton
     */
    public void reset(){
        entryList.clear();
        listSize = 0;
        height = 0;
    }
}
