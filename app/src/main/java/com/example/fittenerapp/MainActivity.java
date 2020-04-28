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
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private static final String LISTSIZE = "List size";
    private ListHolder lh;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
        lh = ListHolder.getInstance();
        int listSize = pref.getInt(LISTSIZE, 0);
        if(listSize > 0) {
            for (int i = 0; i < listSize; i++) {
                String s = Integer.toString(i);
                lh.getEntryList().add(new Entry(pref.getString("entrydata " + s, ""), pref.getFloat("entrydata " + s, 0), pref.getInt("entrydata " + s, 0)));
            }
        }
        editText = findViewById(R.id.editText);
    }

    public void ButtonPressed(View view){
        if(view == findViewById(R.id.add_entry)){
            lh.getEntryList().add(new Entry(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), Float.parseFloat(editText.getText().toString()), 180));
        }
        if(view == findViewById(R.id.calendar)){
            Intent intent = new Intent(MainActivity.this, Calendar.class);//TODO: Update list instead
            startActivity(intent);
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
                edit.putString("entrydata " + s, entry.toString());
                edit.putFloat("entrydata " + s, entry.weight);
                edit.putInt("entrydata " + s, entry.height);

            }
            edit.putInt(LISTSIZE, listSize);
            edit.commit();
        }
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
