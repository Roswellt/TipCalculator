package com.example.qdo010.tipcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity{

    private Button calcButton, upButton, downButton, tipSuggest;
    private CharSequence totalCost, tipPercent, numPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonListener();
    }

    public void setButtonListener(){
        calcButton = (Button) findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillIntegers();
                if(editTextEmpty()){
                    Toast.makeText(getApplicationContext(), "Cannot leave a line empty", Toast.LENGTH_LONG).show();
                }
                else {
                    if (Integer.valueOf(totalCost.toString()) < 0 || (Integer.valueOf(tipPercent.toString()) < 0 || Integer.valueOf(tipPercent.toString()) > 100) || Integer.valueOf(numPeople.toString()) <= 0) {
                        Toast.makeText(getApplicationContext(), "Numbers are outside of valid range", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent("com.example.qdo010.tipcalculator.Summary");
                        intent.putExtra("totalCost", totalCost.toString());
                        intent.putExtra("tipPercent", tipPercent.toString());
                        intent.putExtra("numPeople", numPeople.toString());
                        startActivity(intent);
                    }
                }
            }
        });
        upButton = (Button) findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPeople = ((TextView) findViewById(R.id.numPeople)).getText();
                if(numPeople.length()==0){
                    ((TextView) findViewById(R.id.numPeople)).setText("1");
                } else {
                    int addOne = Integer.valueOf(numPeople.toString()) + 1;
                    ((TextView) findViewById(R.id.numPeople)).setText(Integer.toString(addOne));
                }
            }
        });
        downButton = (Button) findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPeople = ((TextView) findViewById(R.id.numPeople)).getText();
                if(numPeople.length()==0 || Integer.valueOf(numPeople.toString()) == 0){
                } else {
                    int minusOne = Integer.valueOf(numPeople.toString()) - 1;
                    ((TextView) findViewById(R.id.numPeople)).setText(Integer.toString(minusOne));
                }
            }
        });
        tipSuggest = (Button) findViewById(R.id.tipSuggest);
        tipSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.qdo010.tipcalculator.RatingMenu");
                startActivity(intent);
            }
        });
    }

    public void fillIntegers(){
        totalCost = ((TextView) findViewById(R.id.totalCost)).getText();
        tipPercent = ((TextView) findViewById(R.id.tipPercent)).getText();
        numPeople = ((TextView) findViewById(R.id.numPeople)).getText();
    }
    public boolean editTextEmpty(){
        if(((TextView) findViewById(R.id.totalCost)).getText().length()==0 || ((TextView) findViewById(R.id.tipPercent)).getText().length()==0 || ((TextView) findViewById(R.id.numPeople)).getText().length()==0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.options:
                Intent intent = new Intent("com.example.qdo010.tipcalculator.Menu");
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView currency = (TextView) findViewById(R.id.currencySign);
        TextView tip = (TextView) findViewById(R.id.tipPercent);
        currency.setText(preferences.getString("currency", "$"));
        int tipp = preferences.getInt("defaultTip", 15);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            tip.setText((String) extras.get("tipPercent"));
        } else {
            tip.setText(Integer.toString(tipp));
        }
        //Toast.makeText(this, Integer.toString(tipp), Toast.LENGTH_LONG).show();
        //String tipPercent = preferences.getString("defaultTip", "15");
        //tip.setText(tipPercent);
    }
}
