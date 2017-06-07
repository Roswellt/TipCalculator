package com.example.qdo010.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingMenu extends Activity {

    public RatingBar ratingBar;
    public TextView tipPer;
    public Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_menu);
        addListenerOnRatingBar();
        addListeners();
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tipPer = (TextView) findViewById(R.id.suggestedTip);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int tip = Math.round(10+(rating*2));
                tipPer.setText(Integer.toString(tip));
            }
        });
    }

    public void addListeners(){
        finishBtn = (Button) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.qdo010.tipcalculator.MainActivity");
                intent.putExtra("tipPercent", tipPer.getText());
                startActivity(intent);
            }
        });
    }
}
