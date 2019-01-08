package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class guess2 extends AppCompatActivity {

    EditText gættelade;
    TextView vinderen;
    Button okknap;
    TextView taber;
    vinderscreen vinder = new vinderscreen();
    KonfettiView viewKonfetti1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess2);
        final MediaPlayer mediaPlayer = MediaPlayer.create(guess2.this, R.raw.klap);

        viewKonfetti1 = findViewById(R.id.viewKonfetti1);

        final Intent secondIntent = getIntent();
        String besked = "Du har valgt følgende ord: " + secondIntent.getStringExtra("ett");

        TextView infoM = findViewById(R.id.guessssss);
        infoM.setText(besked);

        vinderen = findViewById(R.id.vundetET);
        gættelade = findViewById(R.id.guesfelt);
        taber = findViewById(R.id.taberfelt);

        okknap = findViewById(R.id.okknap);

        okknap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondIntent.getStringExtra("ett").equals(gættelade.getText().toString())) {
                    vinderen.setText("Du har gættet rigtigt!");
                    makeGrafitti1();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item1:
                GoToStart();
                return true;
            case R.id.item2:
                GoToGættelegen();
                return true;
            case R.id.item3:
                GoToHighscoreClass();
                return true;
            case R.id.item4:
                GoToOmSpillet();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToStart(){
        Intent start = new Intent(this, HomeActivity.class);
        startActivity(start);
    }

    public void GoToGættelegen(){
        Intent gættelegen = new Intent(this, guessGame.class);
        startActivity(gættelegen);
    }

    public void GoToHighscoreClass(){
        Intent hhscore = new Intent(this, Highscore.class);
        startActivity(hhscore);
    }
    public void GoToOmSpillet(){
        Intent omspillet = new Intent(this, omSpillet.class);
        startActivity(omspillet);
    }

    public void makeGrafitti1(){

        viewKonfetti1.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, viewKonfetti1.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

}
