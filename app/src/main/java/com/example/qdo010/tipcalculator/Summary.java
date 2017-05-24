package com.example.qdo010.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Summary extends AppCompatActivity {

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
}
