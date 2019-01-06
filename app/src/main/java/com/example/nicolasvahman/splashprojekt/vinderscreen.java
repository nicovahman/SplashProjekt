package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;





public class vinderscreen extends AppCompatActivity {

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
                Intent startside = new Intent( vinderscreen.this, HomeActivity.class);
                startActivity(startside);

            case R.id.item2:
                Intent gættelegen = new Intent(vinderscreen.this, guessGame.class);
                startActivity(gættelegen);
            case R.id.item3:
                Intent highscore = new Intent(vinderscreen.this, Highscore.class);
                startActivity(highscore);
            case R.id.item4:
                Intent omspillet = new Intent(vinderscreen.this, omSpillet.class);
                startActivity(omspillet);

        }
        return super.onOptionsItemSelected(item);
    }


}

