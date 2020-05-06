package com.example.fittenerapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Class to implement a Settings activity
 *
 * @author Alexander San Miguel
 * @version 0.2 05/05/2020
 */

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private ListHolder lh;
    private EditText editText;
    Button resetButton;

    /**
     * Method sets a view of the settings page, and implements an alert dialog if the user attempts to delete their history.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        lh = ListHolder.getInstance();

        resetButton = (Button) findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() { //Draws an alert dialog to request confirmation of history deletion.
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);

                builder.setMessage("Are you sure you want to delete all entries?") // Sets the confirmation dialogue, alerts the user about the impending deletion.
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() { // Positive button. After pressing, it will delete the contents of the Calendar list.
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                                edit.clear();
                                edit.commit();
                                lh.reset();
                            }
                        }).setNegativeButton("No", null); // Negative button. After pressing, the alert dialogue closes.
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    /**
     * Method for when the user presses the "about" button. App will then call a popup layout that includes a view of the creators, and copyright information.
     * @param view Used to set the aboutPressed event.
     */

    public void aboutPressed(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.about_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT; // Sets width of the popup
        int height = LinearLayout.LayoutParams.WRAP_CONTENT; // Sets the height of the popup
        boolean focusable = true; // Makes the popup focusable
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable); // Creates the popup, while the following line gives it the placement.
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);
        popupView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event){
                popupWindow.dismiss();
                return true;
            }
        });
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
                Toast.makeText(this, "You're already here!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
