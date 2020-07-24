package com.letmefly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.letmefly.viewmodels.SearchCountryViewModel;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    SearchCountryViewModel searchCountryViewModel;
    private CountryCodePicker country_departure;
    private CountryCodePicker country_destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button search_Flight_Button = findViewById(R.id.searchPage_button_makeRequest);
        country_departure = findViewById(R.id.searchPage_input_makeRequest);
        country_destination = findViewById(R.id.searchPage_country_picker);

        searchCountryViewModel.getResult().observe(this, result -> {
            if(result == null) {
                Toast toast = Toast.makeText(getApplicationContext(), "Location unavailable, we are working on expanding the database.", Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Intent intent = new Intent(MainActivity.this , ResultsActivity.class);
                intent.putExtra("destination", result.getDestination());
                intent.putExtra("line1", result.getLine1());
                intent.putExtra("line2", result.getLine2());
                intent.putExtra("details", result.getLine3());
                intent.putExtra("canVisit", String.valueOf(result.isCanVisit()));
                intent.putExtra("canTransit", String.valueOf(result.isCanTransit()));
                intent.putExtra("quarantineRequired", String.valueOf(result.isQuarantine_required()));

                startActivity(intent);

            }
        });

        search_Flight_Button.setOnClickListener(view -> searchCountryViewModel.checkCountry(country_destination.getSelectedCountryEnglishName(),
                country_departure.getSelectedCountryEnglishName()));
    }

}