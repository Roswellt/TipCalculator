package com.example.qdo010.tipcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Activity implements AdapterView.OnItemSelectedListener{

    private Spinner textSpinner;
    private Button saveButton;
    public SharedPreferences.Editor editor;
    private String currentCurrency, spinnerText, defaultTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Context context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPrefs.edit();
        addToSpinner();
        addButton();
        textSpinner.setOnItemSelectedListener(this);
    }

    private void addButton() {
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultTip = ((TextView) findViewById(R.id.defaultTip)).getText().toString();

                if(defaultTip.isEmpty()){
                    Toast.makeText(Menu.this, "Tip percentage cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    editor.putString("currency", currentCurrency);
                    editor.putInt("defaultTip", Integer.parseInt(defaultTip));
                    //editor.putString("defaultTip", defaultTip);
                    editor.commit();
                    Toast.makeText(Menu.this, "Saved", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    //Adds items to spinner in the activity
    public void addToSpinner() {
        textSpinner = (Spinner) findViewById(R.id.currencySpinner);

        List<String> list = new ArrayList<String>();
        list.add("Dollar"); //$
        list.add("Euro"); //€
        list.add("Pound"); //£

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        textSpinner.setAdapter(dataAdapter);
    }

    //Handles the logic whenever an item is selected on the drop down spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        spinnerText = textSpinner.getSelectedItem().toString();

        switch (spinnerText)
        {

            case "Dollar":
                currentCurrency = "$";
                break;
            case "Euro":
                currentCurrency = "€";
                break;
            case "Pound":
                currentCurrency = "£";
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
