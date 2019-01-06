package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class guess2 extends AppCompatActivity {

    EditText gættelade;
    TextView vinderen;
    Button okknap;
    TextView taber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess2);
        final MediaPlayer mediaPlayer = MediaPlayer.create(guess2.this, R.raw.klap);

        final Intent secondIntent = getIntent();
        String besked = "Du har valgt følgende ord: " + secondIntent.getStringExtra("et");

        TextView infoM = findViewById(R.id.guessssss);
        infoM.setText(besked);

        vinderen = findViewById(R.id.vundetET);
        gættelade = findViewById(R.id.guesfelt);
        taber = findViewById(R.id.taberfelt);

        okknap = findViewById(R.id.okknap);

        okknap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondIntent.getStringExtra("et").equals(gættelade.getText().toString())) {
                    vinderen.setText("Du har gættet rigtigt!");
                    mediaPlayer.start();

                }

                else{
                    taber.setText("Du har gættet forkert, prøv igen");
                    taber.setText("");
                    gættelade.setText("");
                }

            }
        });









    }
}
