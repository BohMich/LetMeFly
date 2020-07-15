package com.letmefly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.letmefly.models.Result;
import com.letmefly.viewmodels.PassengerViewModel;

public class MainActivity extends AppCompatActivity {

    private PassengerViewModel passengerViewModel;
    private Button search_Flight_Button;
    private EditText search_Flight_InputFlight;
    private EditText search_Flight_InputName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_Flight_Button = findViewById(R.id.searchPage_button_makeRequest);
        search_Flight_InputFlight = findViewById(R.id.searchPage_input_makeRequest);
        search_Flight_InputName = findViewById(R.id.searchPage_input_makeRequest_Name);

        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.getResult().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Flight Code", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    //TODO Pass the result object to Results Fragment
                    Toast toast = Toast.makeText(getApplicationContext(), result.getInfo(), Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        search_Flight_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passengerViewModel.checkFlight(search_Flight_InputFlight.getText().toString(), search_Flight_InputName.getText().toString());
            }
        });

    }

}