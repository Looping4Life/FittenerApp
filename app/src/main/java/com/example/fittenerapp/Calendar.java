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

public class Calendar extends AppCompatActivity {
    ListView listView;
    ArrayList entryList;
    TextView dateView;
    TextView weightView;
    TextView heightView;
    TextView bmiView;

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
                weightView.setText("Weight: " + Float.toString(e.weight));
                heightView.setText("Height: " + Float.toString(e.height));
                bmiView.setText("BMI: ");//TODO: For BMI
            }
        });
    }

    public void hideInfo(View v){
        findViewById(R.id.constraintLayout).setVisibility(View.GONE);
    }

    // Creates the top action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    // Onclick selection for the action bar items
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
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
