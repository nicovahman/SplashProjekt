package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class vinderscreen extends AppCompatActivity {

    private TextView infoTilvinder;
    TheGame game = new TheGame();
    private Button tilforside;
    private Button tilHighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinderscreen);
        getSupportActionBar().hide();

        tilforside = (Button) findViewById(R.id.tilForside);
        tilforside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TilbageTilforsiden();
            }
        });

        tilHighscore = (Button) findViewById(R.id.tilHighscore);
        tilHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TilHighscore();
            }
        });
        infoTilvinder = (TextView) findViewById(R.id.VinderInfo);



        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            String vinderInfo = extras.getString("vinderKey");
            infoTilvinder.setText(vinderInfo);

        }



    }

    private void TilbageTilforsiden(){
        Intent forside = new Intent(vinderscreen.this, HomeActivity.class);
        startActivity(forside);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void TilHighscore(){
        Intent highscore = new Intent(vinderscreen.this, HomeActivity.class);
        startActivity(highscore);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


}
