package com.example.myfirstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button myButton;
    TextView resultView;
    EditText userInput;

    String FromSelectedUnit, ToSelectedUnit, FinalResult;
    double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.button);
        resultView = findViewById(R.id.Result);
        Spinner fromUnitSpinner = (Spinner) findViewById(R.id.fromUnit);
        Spinner toUnitSpinner = (Spinner) findViewById(R.id.toUnit);
        userInput = findViewById(R.id.editTextTextPersonName);
        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                FromSelectedUnit= fromUnitSpinner.getSelectedItem().toString().toLowerCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                FromSelectedUnit = "inch";
            }
        });
        toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ToSelectedUnit= toUnitSpinner.getSelectedItem().toString().toLowerCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ToSelectedUnit = "inch";
            }
        });

        myButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                FinalResult = null;

                String input = userInput.getText().toString().trim();
                if(TextUtils.isEmpty(input)) {
                    Toast.makeText(MainActivity.this, "Input Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double inputNumber = Double.parseDouble(input);
                    if (inputNumber <= 0) {
                        throw new NumberFormatException();
                    }
                    switch (FromSelectedUnit) {
                        case "foot":
                            switch (ToSelectedUnit) {
                                case "inch":
                                    result = inputNumber * 12.0;
                                    break;
                                case "yard":
                                    result = inputNumber / 3.0;
                                    break;
                                case "mile":
                                    result = inputNumber / 5280.0;
                                    break;
                                default:
                                    result = inputNumber;
                                    break;
                            }
                            break;
                        case "inch":
                            switch (ToSelectedUnit) {
                                case "foot":
                                    result = inputNumber / 12.0;
                                    break;
                                case "yard":
                                    result = inputNumber / 36.0;
                                    break;
                                case "mile":
                                    result = inputNumber / 63360.0;
                                    break;
                                default:
                                    result = inputNumber;
                                    break;
                            }
                            break;
                        case "yard":
                            switch (ToSelectedUnit) {
                                case "foot":
                                    result = inputNumber * 3.0;
                                    break;
                                case "inch":
                                    result = inputNumber * 36.0;
                                    break;
                                case "mile":
                                    result = inputNumber / 1760.0;
                                    break;
                                default:
                                    result = inputNumber;
                                    break;
                            }
                            break;
                        case "mile":
                            switch (ToSelectedUnit) {
                                case "foot":
                                    result = inputNumber * 5280.0;
                                    break;
                                case "inch":
                                    result = inputNumber * 63360.0;
                                    break;
                                case "yard":
                                    result = inputNumber * 1760.0;
                                    break;
                                default:
                                    result = inputNumber;
                                    break;
                            }
                            break;
                        default:
                            result = inputNumber;
                            break;
                    }
                    FinalResult = Double.toString(result);
                    if (FinalResult == null) {
                        Toast.makeText(MainActivity.this, "Invalid conversion", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (NumberFormatException a) {
                    Toast.makeText(MainActivity.this, "Input is not a valid number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (FromSelectedUnit.equals(ToSelectedUnit)) {
                    Toast.makeText(MainActivity.this, "Source unit and destination unit are the same", Toast.LENGTH_SHORT).show();
                    return;
                }

                resultView.setText("Converted Value From "+FromSelectedUnit+" to "+ToSelectedUnit+" : "+FinalResult + " " + ToSelectedUnit);
                Toast.makeText(MainActivity.this, FromSelectedUnit + " To " + ToSelectedUnit, Toast.LENGTH_SHORT).show();
            }
        });
    }
}