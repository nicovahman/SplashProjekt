package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class vinderscreen extends HomeActivity{

    private TextView infoTilvinder;
    HomeActivity home = new HomeActivity();
    private Button tilforside;
    private Button tilHighscore;

    private TextView navnet;


    KonfettiView viewKonfetti;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinderscreen);

        navnet = findViewById(R.id.infoNavnjubi);








        final MediaPlayer mediaPlayer = MediaPlayer.create(vinderscreen.this, R.raw.klap);


        viewKonfetti = findViewById(R.id.viewKonfetti);
        makeGrafitti();

        tilforside = findViewById(R.id.tilForside);
        tilforside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TilbageTilforsiden();
            }
        });


        tilHighscore = findViewById(R.id.tilHighscore);
        tilHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TilHighscore();
            }
        });
        infoTilvinder = findViewById(R.id.VinderInfo);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            String vinderInfo = extras.getString("vinderKey");

            infoTilvinder.setText(vinderInfo);
            mediaPlayer.start();


        }


    }

    private void TilbageTilforsiden() {
        Intent forside = new Intent(vinderscreen.this, HomeActivity.class);
        startActivity(forside);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();

    }

    private void TilHighscore() {
        Intent highscore = new Intent(vinderscreen.this, Highscore.class);
        startActivity(highscore);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
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
        finish();
    }

    public void GoToGættelegen(){
        Intent gættelegen = new Intent(this, guessGame.class);
        startActivity(gættelegen);
        finish();
    }

    public void GoToHighscoreClass(){
        Intent hhscore = new Intent(this, Highscore.class);
        startActivity(hhscore);
        finish();
    }
    public void GoToOmSpillet(){
        Intent omspillet = new Intent(this, omSpillet.class);
        startActivity(omspillet);
        finish();
    }


    public void makeGrafitti(){

        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

}

