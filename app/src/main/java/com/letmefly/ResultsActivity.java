package com.letmefly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView result_destination;
    ScrollView scrollView;
    String canVisit;
    String canTransfer;
    String quarantineRequired;

    TextView resultsHeader;
    ImageView resultSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result_destination = findViewById(R.id.textView_result_destination);
        resultSign = findViewById(R.id.image_result);
        resultsHeader = findViewById(R.id.result_header);
        scrollView = findViewById(R.id.result_scrollview);

        Intent intent = getIntent();

        String output = intent.getStringExtra("destination") + "\n \n" +
                        intent.getStringExtra("line1") + "\n \n" +
                        intent.getStringExtra("line2") + "\n \n" +
                        intent.getStringExtra("details");

        result_destination.setText(output);

        canVisit = intent.getStringExtra("canVisit");
        canTransfer = intent.getStringExtra("canTransit");
        quarantineRequired = intent.getStringExtra("quarantineRequired");

        setScreenState();
    }

    private void setScreenState(){

        if(canVisit.equals("true")){
            if(quarantineRequired.equals("true")){
                scrollView.setBackground(getResources().getDrawable(R.drawable.border_blue));
                resultSign.setImageResource(R.drawable.blue_final);
                resultsHeader.setText(R.string.results_Header_Blue);
            }
            else{
                scrollView.setBackground(getResources().getDrawable(R.drawable.border_green));
                resultSign.setImageResource(R.drawable.green_final);
                resultsHeader.setText(R.string.results_Header_Green);
            }
        }
        else if(canTransfer.equals("true")){
            scrollView.setBackground(getResources().getDrawable(R.drawable.border_orange));
            resultSign.setImageResource(R.drawable.yellow_final);
            resultsHeader.setText(R.string.results_Header_Orange);
        }
        else{
            scrollView.setBackground(getResources().getDrawable(R.drawable.border_red));
            resultSign.setImageResource(R.drawable.red_final);
            resultsHeader.setText(R.string.results_Header_Red);
        }

    }
}