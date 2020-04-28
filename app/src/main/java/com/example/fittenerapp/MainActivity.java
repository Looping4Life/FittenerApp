package com.example.fittenerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private static final String LISTSIZE = "List size";
    ListHolder lh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        lh = ListHolder.getInstance();
        int listSize = pref.getInt(LISTSIZE, 0);
        for (int i = 0; i < listSize; i++) {
            String s = Integer.toString(i);
            lh.getEntryList().add(new Entry(pref.getString(s, ""), pref.getFloat(s, 0), pref.getInt(s, 0)));
        }

    }

    public void ButtonPressed(){
        Intent intent = new Intent(MainActivity.this, Calendar.class);
        startActivity(intent);
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        int listSize = lh.getEntryList().size();
        for (int i = 0; i < listSize; i++) {
            Entry entry = lh.getEntryList().get(i);
            edit.putString(Integer.toString(i), entry.toString());
            edit.putFloat(Integer.toString(i), entry.weight);
            edit.putInt(Integer.toString(i), entry.height);

        }
        edit.putInt(LISTSIZE, listSize);
        edit.apply();
    }
}
