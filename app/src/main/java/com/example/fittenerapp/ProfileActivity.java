package com.example.fittenerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        person = new Person(0,0,0);
    }

    public void onClickSaveValues(View View) {
        EditText et = (EditText) findViewById(R.id.height1);
        person.setHeight(Integer.parseInt(et.getText().toString()));

        EditText et2 = (EditText) findViewById(R.id.weight1);
        person.setWeight(Integer.parseInt(et2.getText().toString()));

        TextView tv = (TextView) findViewById(R.id.bmi);
        tv.setText(person.getBMI());

        TextView tv2 = (TextView) findViewById(R.id.fatorfit);
        tv2.setText(person.checkBMI());
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