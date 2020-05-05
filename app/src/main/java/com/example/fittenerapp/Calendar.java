package com.example.fittenerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Class for the user's details
 * @author Janne Kaukua
 * @version 0.2 05/05/2020
 */
public class Calendar extends AppCompatActivity {
    ListView listView;
    ArrayList entryList;
    TextView dateView;
    TextView weightView;
    TextView heightView;
    TextView bmiView;

    /**
     * Set on click listener and display the information in a new view
     * Also reverses the list of entries to display the latest first
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        listView = findViewById(R.id.listView);
        dateView = findViewById(R.id.dateView);
        weightView = findViewById(R.id.weightView);
        heightView = findViewById(R.id.heightView);
        bmiView = findViewById(R.id.bmiView);
        entryList = ListHolder.getInstance().getEntryList();
        Collections.reverse(entryList);

        listView.setAdapter(new ArrayAdapter<Entry>(this, R.layout.list_view_layout, entryList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                findViewById(R.id.constraintLayout).setVisibility(View.VISIBLE);
                Entry e = (Entry)entryList.get(i);
                dateView.setText("Date: " + e.date);
                weightView.setText("Weight: " + e.weight);
                heightView.setText("Height: " + e.height);
                float height = (float)e.height / 100;
                float bmi = e.weight / (height * height);
                bmiView.setText("BMI: " + Math.round(bmi * 100.0f) / 100.0f);
            }
        });
    }

    /**
     * For hiding the clicked entry's info
     * @param v view
     */
    public void hideInfo(View v){
        findViewById(R.id.constraintLayout).setVisibility(View.GONE);
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

    /**
     * Reverts the list of entries back to normal when leaving the activity
     */
    @Override
    public void onPause(){
        super.onPause();
        Collections.reverse(entryList);
    }
}
