package com.example.fittenerapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Class to implement a Settings activity
 *
 * @author Alexander San Miguel
 * @version 0.1 30/4/2020
 */

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS = "SavedValues";
    private ListHolder lh;
    private EditText editText;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        resetButton = (Button) findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() { //Draws an alert dialog to request confirmation of history deletion
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);

                builder.setMessage("You are about to delete everything.\nAre you sure?")
                        .setPositiveButton("Hit me daddy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                                edit.clear();
                                edit.commit();
                                lh.getEntryList().clear();
                            }
                        }).setNegativeButton("Nu uh", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void aboutPressed(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.about_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);
        popupView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event){
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
