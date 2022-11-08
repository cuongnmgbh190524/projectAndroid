package com.example.finala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExppenceActivity extends AppCompatActivity {
    String id;
    EditText nameExpence, costExpence, DateStartExpence, DateEndExpence, OverviewExpence;
    Button add_expence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_add_exppence);

        nameExpence = findViewById(R.id.nameExpence);
        costExpence = findViewById(R.id.costExpence);
        DateStartExpence = findViewById(R.id.DateStartExpence);
        DateEndExpence = findViewById(R.id.DateEndExpence);
        OverviewExpence = findViewById(R.id.OverviewExpence);
        add_expence = findViewById(R.id.add_expence);
        add_expence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataExpence myDB1 = new DataExpence(AddExppenceActivity.this);
                myDB1.addExpence(nameExpence.getText().toString().trim(),
                        costExpence.getText().toString().trim(),
                        DateStartExpence.getText().toString().trim(),
                        DateEndExpence.getText().toString().trim(),
                        OverviewExpence.getText().toString().trim());
            }
        });
    }
}