package com.example.qdo010.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Summary extends Activity {

    private Double totalCost,tipPercent,billCost,tipPP,eachPP,totalTip;
    private int numPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        fillSummary();
    }

    private void fillSummary(){
        Intent intent = getIntent();

        numPeople = Integer.valueOf(intent.getStringExtra("numPeople"));
        tipPercent = Double.valueOf(intent.getStringExtra("tipPercent"));
        billCost = Double.valueOf(intent.getStringExtra("totalCost"));
        totalTip = billCost * (tipPercent/100);
        totalTip = (double)Math.round(totalTip * 100d) / 100d;
        totalCost = billCost + totalTip;
        tipPP = totalTip/numPeople;
        tipPP = (double)Math.round(tipPP * 100d) / 100d;
        eachPP = totalCost/numPeople;
        eachPP = (double)Math.round(eachPP * 100d) / 100d;

        ((TextView) findViewById(R.id.summaryBill)).setText(Double.toString(billCost));
        ((TextView) findViewById(R.id.summaryTip)).setText(Double.toString(totalTip));
        ((TextView) findViewById(R.id.summaryTotal)).setText(Double.toString(totalCost));
        ((TextView) findViewById(R.id.tipPP)).setText(Double.toString(tipPP));
        ((TextView) findViewById(R.id.eachPP)).setText(Double.toString(eachPP));
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView currency1 = (TextView) findViewById(R.id.currency1);
        TextView currency2 = (TextView) findViewById(R.id.currency2);
        TextView currency3 = (TextView) findViewById(R.id.currency3);
        TextView currency4 = (TextView) findViewById(R.id.currency4);
        TextView currency5 = (TextView) findViewById(R.id.currency5);
        currency1.setText(preferences.getString("currency", "$"));
        currency2.setText(preferences.getString("currency", "$"));
        currency3.setText(preferences.getString("currency", "$"));
        currency4.setText(preferences.getString("currency", "$"));
        currency5.setText(preferences.getString("currency", "$"));
    }
}
