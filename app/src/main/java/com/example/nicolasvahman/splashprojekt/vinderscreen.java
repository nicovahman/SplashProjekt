package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;





public class vinderscreen extends HomeActivity{

    private TextView infoTilvinder;
    TheGame game = new TheGame();
    private Button tilforside;
    private Button tilHighscore;

    private TextView navnet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinderscreen);
        getSupportActionBar().hide();

        navnet = findViewById(R.id.hello);

        final MediaPlayer mediaPlayer = MediaPlayer.create(vinderscreen.this, R.raw.klap);



        Bundle bb = getIntent().getExtras();
        if (bb != null) {
            String getSpillernavn = bb.getString("spillernavn");
            navnet.setText(getSpillernavn);
        }

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

    }

    private void TilHighscore() {
        Intent highscore = new Intent(vinderscreen.this, HomeActivity.class);
        startActivity(highscore);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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


}

