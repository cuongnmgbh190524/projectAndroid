package com.example.finala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText name_input, departure_input, destination_input, date_input, risk_input, description_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        departure_input = findViewById(R.id.departure_input);
        destination_input = findViewById(R.id.destination_input);
        date_input = findViewById(R.id.date_input);
        risk_input = findViewById(R.id.risk_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTrip(name_input.getText().toString().trim(),
                        departure_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        risk_input.getText().toString().trim(),
                        description_input.getText().toString().trim());
            }
        });
    }
}