package com.example.finala;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, departure_input, destination_input, date_input, risk_input, description_input;
    Button update_button, viewMexence, delete_button;

    String id,name, departure,destination, date, risk, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        departure_input = findViewById(R.id.departure_input2);
        destination_input= findViewById(R.id.destination_input2);
        date_input = findViewById(R.id.date_input2);
        risk_input = findViewById(R.id.risk_input2);
        description_input = findViewById(R.id.description_input2);



        update_button = findViewById(R.id.update_button);
        viewMexence = findViewById(R.id.viewMexpence);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab !=null){
            ab.setTitle(name);
        }


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, name_input.getText().toString(), departure_input.getText().toString(),destination_input.getText().toString(),date_input.getText().toString(),risk_input.getText().toString(),description_input.getText().toString());
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comfirmDialog();
            }
        });
        //first we call this
        viewMexence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, ViewMExpence.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
        getIntent().hasExtra("departure") && getIntent().hasExtra("destination") &&
        getIntent().hasExtra("date") && getIntent().hasExtra("risk") &&
        getIntent().hasExtra("description")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            departure = getIntent().getStringExtra("departure");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            risk = getIntent().getStringExtra("risk");
            description = getIntent().getStringExtra("description");

            //setting intent data
            name_input.setText(name);
            departure_input.setText(departure);
            destination_input.setText(destination);
            date_input.setText(date);
            risk_input.setText(risk);
            description_input.setText(description);
        }
        else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
    void comfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + "?");
        builder.setMessage("Are you sure you want to delete "+ name +"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}