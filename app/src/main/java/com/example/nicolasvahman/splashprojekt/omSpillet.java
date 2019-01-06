package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class omSpillet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om_spillet);
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
