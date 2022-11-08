package com.example.finala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewMExpence extends AppCompatActivity {
    RecyclerView recyclerViewExpence;
    FloatingActionButton addMexpence;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mexpence);
        id = getIntent().getStringExtra("id");
        recyclerViewExpence = findViewById(R.id.recyclerViewExpence);
        addMexpence = findViewById(R.id.addMexpence);
        addMexpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMExpence.this, AddExppenceActivity.class);

                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}