package com.example.qdo010.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button calcButton;
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
}
