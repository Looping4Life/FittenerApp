package com.example.fittenerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

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
    private EditText editText;

    /**
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        lh = ListHolder.getInstance();
        lh.getEntryList().clear();
        int listSize = pref.getInt(LISTSIZE, 0);
        if(listSize > 0) {
            for (int i = 0; i < listSize; i++) {
                String s = Integer.toString(i);
                lh.AddEntry(new Entry(pref.getString("string " + s, "DEFAULT"), pref.getFloat("float " + s, 0), pref.getInt("integer " + s, 0)));
            }
        }
        editText = findViewById(R.id.editText);
    }

    public void ButtonPressed(View view){
        if(view == findViewById(R.id.add_entry) && !editText.getText().toString().isEmpty()){
            lh.getEntryList().add(new Entry(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()), Float.parseFloat(editText.getText().toString()), 180));//TODO: Change to look for height
        }
        if(view == findViewById(R.id.calendar)){
            Intent intent = new Intent(MainActivity.this, Calendar.class);
            startActivity(intent);
        }
        if(view == findViewById(R.id.reset_prefs)){
            SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.clear();
            edit.commit();
            lh.getEntryList().clear();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        int listSize = lh.getEntryList().size();
        if(listSize > 0){
            for (int i = 0; i < listSize; i++) {
                Entry entry = lh.getEntryList().get(i);
                String s = Integer.toString(i);
                edit.putString("string " + s, entry.date);
                edit.putFloat("float " + s, entry.weight);
                edit.putInt("integer " + s, entry.height);

            }
            edit.putInt(LISTSIZE, listSize);
            edit.commit();
        }
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
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }

    }
}
