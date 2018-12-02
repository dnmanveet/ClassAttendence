package com.example.android.classattendence;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent marking = new Intent(MainActivity.this,Marking.class);
                Date c = Calendar.getInstance().getTime();
                String message = df.format(c);
                marking.putExtra("key", message);
                startActivity(marking);

            }
        });
    }

}
