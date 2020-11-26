package com.example.german_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView numbers = findViewById(R.id.numbers);

        //Set a clickListener on that View
        numbers.setOnClickListener(view -> {
            // Create a new Intent to open the @link NumbersActivity
            Intent numberIntent = new Intent(MainActivity.this, NumbersActivity.class);
            // Start the new activity
            startActivity(numberIntent);
        });

        // Find the View that shows the family category
        TextView family = findViewById(R.id.family);

        //Set a clickListener on that View
        family.setOnClickListener(view -> {
            // Create a new Intent to open the @link FamilyActivity
            Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
            // Start the new activity
            startActivity(familyIntent);
        });


        // Find the View that shows the colors category
        TextView colors = findViewById(R.id.colors);

        //Set a clickListener on that View
        colors.setOnClickListener(view -> {
            // Create a new Intent to open the @link ColorsActivity
            Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
            // Start the new activity
            startActivity(colorsIntent);
        });


        // Find the View that shows the phrases category
        TextView phrases = findViewById(R.id.phrases);

        //Set a clickListener on that View
        phrases.setOnClickListener(view -> {
            // Create a new Intent to open the @link PhrasesActivity
            Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
            // Start the new activity
            startActivity(phrasesIntent);
        });


    }

}


