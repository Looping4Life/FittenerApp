package com.example.fittenerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class for the user's details
 * @author Janne Kaukua, Jan Buben
 * @version 0.1 28/4/2020
 */
public class MainActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private static final String LISTSIZE = "List size";
    public static final String EXTRA_MESSAGE = "com.examplemyfirstapp.MESSAGE";
    private ListHolder lh; //Singleton for holding the entry history
    private Person p;
    private EditText editText;
    private int listSize;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        lh = ListHolder.getInstance();
        lh.getEntryList().clear();

        listSize = pref.getInt(LISTSIZE, 0);
        if(listSize > 0) {
            for (int i = 0; i < listSize; i++) {
                String s = Integer.toString(i);
                lh.AddEntry(new Entry(pref.getString("string " + s, "DEFAULT"), pref.getFloat("float " + s, 0), pref.getInt("integer " + s, 0)));
            }
        }
        editText = findViewById(R.id.editText);
        lh.setListSize(listSize);
    }

    public void ButtonPressed(View view){
        if(view == findViewById(R.id.add_entry) && !editText.getText().toString().isEmpty()){
            if(lh.getListSize() > 0){
                lh.AddEntry(new Entry(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()), Float.parseFloat(editText.getText().toString()), lh.getEntryList().get(listSize-1).height));
                findViewById(R.id.initialize_profile_text).setVisibility(View.GONE);
            }else{
                findViewById(R.id.initialize_profile_text).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        listSize = lh.getEntryList().size();
        if(listSize > 0){
            for (int i = 0; i < listSize; i++) {
                Entry entry = lh.getEntryList().get(i);
                edit.putString("string " + i, entry.date);
                edit.putFloat("float " + i, entry.weight);
                edit.putInt("integer " + i, entry.height);
            }
            edit.putInt(LISTSIZE, listSize);
            edit.commit();
            lh.setListSize(listSize);
        }
    }
    /**
     * Method creates the action bar on top of the screen so the user can navigate through the app.
     * @param menu creates the menu
     * @return returns the view
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    /**
     * Method creates the items and their onclick events for the action bar so the user can click on them. The switch statement is used to differentiate each button
     * @param item creates the items
     * @return completes each case on the switch statement
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // List item
            case R.id.item1:
                Intent calendarIntent = new Intent(this, Calendar.class);
                startActivity(calendarIntent);
                return true;
            // Profile item
            case R.id.item2:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
             // Settings item
            case R.id.item3:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}
