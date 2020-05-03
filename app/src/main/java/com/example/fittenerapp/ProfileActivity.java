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

public class ProfileActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private static final String LISTSIZE = "List size";
    private ListHolder lh;
    private String date;
    private Person person;
    private Boolean entryAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        person = new Person(0,0,0);
        lh = ListHolder.getInstance();
    }

    public void onClickSaveValues(View View) {
        EditText et = (EditText) findViewById(R.id.height1);
        person.setHeight(Float.parseFloat(et.getText().toString()));


        EditText et2 = (EditText) findViewById(R.id.weight1);
        person.setWeight(Float.parseFloat(et2.getText().toString()));

        TextView tv = (TextView) findViewById(R.id.bmi);
        tv.setText(person.getBMI());
        Log.d("!!!!!!!!!!!!!!!", "person.setheight: " + person.height);
        TextView tv2 = (TextView) findViewById(R.id.fatorfit);
        tv2.setText(person.checkBMI());
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        ListHolder.getInstance().AddEntry(new Entry(date, person.weight, Math.round(person.height)));//TODO: add only if it does not exist
        lh.setListSize(lh.getListSize()+1);

        lh.setHeight(Math.round(person.height));
        Log.d("!!!!!!!!!!!!!!!", "lh.setheight: " + lh.getHeight());
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        String s = Integer.toString(lh.getListSize()-1);
        edit.putString("string " + s, date);
        edit.putFloat("float " + s, person.weight);
        edit.putInt("integer " + s, Math.round(person.height));
        edit.putInt(LISTSIZE, lh.getListSize());
        edit.commit();
    }

    // Creates the action navbar
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