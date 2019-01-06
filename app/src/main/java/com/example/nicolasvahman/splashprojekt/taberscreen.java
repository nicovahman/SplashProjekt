package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class taberscreen extends AppCompatActivity {


    private TextView infoTilTaber;
    private Button tilforsidetiltaberen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taberscreen);
        getSupportActionBar().hide();

        infoTilTaber = (TextView) findViewById(R.id.taberInfo);
        tilforsidetiltaberen = (Button) findViewById(R.id.tilforside);

        tilforsidetiltaberen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilbageTilForsidenTaber();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            String taberInfo = extras.getString("key");
            infoTilTaber.setText(taberInfo);
        }


    }

    private void tilbageTilForsidenTaber(){
        Intent back = new Intent(taberscreen.this, HomeActivity.class);
        startActivity(back);
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
