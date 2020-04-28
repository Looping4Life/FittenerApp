package com.example.fittenerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;

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

    public void ButtonPressed(View view){
        Intent intent = new Intent(MainActivity.this, Calendar.class);//TODO: Update list instead
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }

    }
}
