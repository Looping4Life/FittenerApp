package com.example.fittenerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Calendar extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        lv = findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<Entry>(this, R.layout.list_view_layout, ListHolder.getInstance().getEntryList()));
    }
}
