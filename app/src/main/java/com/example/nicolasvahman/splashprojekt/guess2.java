package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class guess2 extends AppCompatActivity {

    EditText gættelade;
    TextView vinderen;
    Button okknap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess2);

        final Intent secondIntent = getIntent();
        String besked = "Du har valgt følgende ord: " + secondIntent.getStringExtra("et");

        TextView infoM = (TextView) findViewById(R.id.guessssss);
        infoM.setText(besked);

        vinderen = findViewById(R.id.vundetET);

        okknap = findViewById(R.id.okknap);

        okknap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        gættelade = findViewById(R.id.guesfelt);

        gættelade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gættelade.equals(  secondIntent.getStringExtra("et"))){
                    vinderen.setText("Du har gættet rigtigt");

                }
            }
        });




    }
}
