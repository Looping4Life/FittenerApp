package com.example.fittenerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class is for the Profile page, the user can save their current weight and height here.
 * @author Jan Buben
 * @version 0.1 30/04/2020
 */
public class ProfileActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private static final String LISTSIZE = "List size";
    private ListHolder lh;
    private String date;
    private Person person;
    private Boolean entryAdded = false;

    /**
     * Method sets the view and creates a person object using the Person.class
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        person = new Person(0,0,0);
        lh = ListHolder.getInstance();
    }

    /**
     * Onclick event, where on the click of the button the method sets the weight and height for the user into the Person-class, and sets them into the viewTexts. Also validates the input so the fields are not empty.
     * @param View Used to set the onClick event
     */
    public void onClickSaveValues(View View) {
        EditText et = (EditText) findViewById(R.id.height1);
        if (et.length() > 0) {
            person.setHeight(Float.parseFloat(et.getText().toString()));
        }

        EditText et2 = (EditText) findViewById(R.id.weight1);
        if (et2.length() > 0) {
            person.setWeight(Float.parseFloat(et2.getText().toString()));
        }

        TextView tv = (TextView) findViewById(R.id.bmi);
        tv.setText(person.getBMI());

        TextView tv2 = (TextView) findViewById(R.id.fatorfit);
        tv2.setText(person.checkBMI());
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        ListHolder.getInstance().AddEntry(new Entry(date, person.weight, Math.round(person.height)));//TODO: add only if it does not exist
        lh.setListSize(lh.getListSize()+1);

        lh.setHeight(Math.round(person.height));
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        String s = Integer.toString(lh.getListSize()-1);
        edit.putString("string " + s, date);
        edit.putFloat("float " + s, person.weight);
        edit.putInt("integer " + s, Math.round(person.height));
        edit.putInt(LISTSIZE, lh.getListSize());
        edit.commit();
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
             // The default case
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}