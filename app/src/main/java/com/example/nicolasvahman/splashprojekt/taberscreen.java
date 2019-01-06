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
                Intent startside = new Intent( taberscreen.this, HomeActivity.class);
                startActivity(startside);

            case R.id.item2:
                Intent gættelegen = new Intent(taberscreen.this, guessGame.class);
                startActivity(gættelegen);
            case R.id.item3:
                Intent highscore = new Intent(taberscreen.this, Highscore.class);
                startActivity(highscore);
            case R.id.item4:
                Intent omspillet = new Intent(taberscreen.this, omSpillet.class);
                startActivity(omspillet);

        }
        return super.onOptionsItemSelected(item);
    }
}
