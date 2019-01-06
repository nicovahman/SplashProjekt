package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class Highscore extends AppCompatActivity {

    private ListView listView;
    private Button tilbage;

    private int scoreCounter;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        //setTitle("Highscore liste");

        getSupportActionBar().hide();


        listView = (ListView) findViewById(R.id.hsl);

        læsHighscore();


        tilbage = (Button) findViewById(R.id.tilbagetilforside);
        tilbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilbageTilForsiden();
            }
        });

    }

    public void læsHighscore(){
        ArrayList<Integer> highsScoreArrayList = new ArrayList<Integer>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        scoreCounter = sharedPreferences.getInt("prefCount", 0);

        Log.d("LOL - HIGHSCORE", "Score Count: " + scoreCounter);

        for(int i = 0; i < scoreCounter; i++){
            score =  sharedPreferences.getInt(String.valueOf("highScorePref" + i), 0);
            Log.d("LOL - HIGHSCORE", "Score: " + score);
            highsScoreArrayList.add(score);
        }
        Collections.sort(highsScoreArrayList);
        Collections.reverse(highsScoreArrayList);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, highsScoreArrayList);
        listView.setAdapter(arrayAdapter);
    }


    private void tilbageTilForsiden(){
        Intent tilbage = new Intent(this, HomeActivity.class);
        startActivity(tilbage);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

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
                Intent startside = new Intent( this, HomeActivity.class);
                startActivity(startside);

            case R.id.item2:
                Intent gættelegen = new Intent(this, guessGame.class);
                startActivity(gættelegen);
            case R.id.item3:
                Intent highscore = new Intent(this, Highscore.class);
                startActivity(highscore);
            case R.id.item4:
                Intent omspillet = new Intent(this, omSpillet.class);
                startActivity(omspillet);

        }
        return super.onOptionsItemSelected(item);
    }

}