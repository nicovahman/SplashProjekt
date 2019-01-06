package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class HomeActivity extends AppCompatActivity  {

    private Button spil;
    private Button anbefaltilven;
    private EditText editTextto;
    private EditText editTextEmne;
    private EditText editTextMessage;
    private ImageView næsteside;
    private Button next;
    private Button highscores;
    private EditText spillerNavn;
    private Button guessButton;
    String names[] = {"Ord fra DR", "Almindelige ord"};
    String record = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        anbefaltilven = (Button) findViewById(R.id.anbefal);
        editTextto = findViewById(R.id.emailadresse);
        editTextEmne = findViewById(R.id.emneTilmailen);
        editTextMessage = findViewById(R.id.emailBesked);
        spillerNavn = (EditText) findViewById(R.id.ditNavn);
        guessButton = findViewById(R.id.guessGame);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent guessSpil = new Intent(HomeActivity.this, guessGame.class);
                startActivity(guessSpil);
            }
        });


        næsteside = (ImageView) findViewById(R.id.nextpage);
        ArrayAdapter <String> adapter;

        highscores = (Button) findViewById(R.id.highscorepage);

        highscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToHighscore();
            }
        });

        Spinner spin = findViewById(R.id.spin);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




                switch (position){



                    case 0:
                        record = "Ord fra DR" ;
                        næsteside.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                spilSpillet();
                                spillerensNavn();

                            }
                        });

                        break;
                    case 1:
                        record = "Almindelig ord";
                        næsteside.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GoToAlmindeligOrd();
                            }
                        });

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









    }




    public void spilSpillet(){
        Intent startspilletnu;
        startspilletnu = new Intent(this, TheGame.class);
        startActivity(startspilletnu);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void anbefalSpilletTilenVen() {

        String modtagere = editTextto.getText().toString();
        String [] modtagerliste = modtagere.split(",");


        String emne = editTextEmne.getText().toString();
        String besked = editTextMessage.getText().toString();

        Intent intent4 = new Intent(Intent.ACTION_SEND);
        intent4.putExtra(Intent.EXTRA_EMAIL, modtagerliste);
        intent4.putExtra(Intent.EXTRA_SUBJECT,emne);
        intent4.putExtra(Intent.EXTRA_TEXT, besked);

        intent4.setType("message/rfc822");
        startActivity(Intent.createChooser(intent4, "Vælg en email du vil benytte"));

    }

    public void GoToAlmindeligOrd(){
       Intent ordFraDR = new Intent(this, AlmindeligtSpil.class);
       startActivity(ordFraDR);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    public void GoToHighscore(){
        Intent highscoreside = new Intent(this, Highscore.class);
        startActivity(highscoreside);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void spillerensNavn(){

        String fåSpillernavn = "Velkommen til hangman " + spillerNavn.getText().toString();
        Intent navnTilSpil = new Intent(HomeActivity.this, TheGame.class);

        navnTilSpil.putExtra("spillernavn", fåSpillernavn);
        startActivity(navnTilSpil);
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
                Intent startside = new Intent( HomeActivity.this, HomeActivity.class);
                startActivity(startside);

            case R.id.item2:
                Intent gættelegen = new Intent(HomeActivity.this, guessGame.class);
                startActivity(gættelegen);
            case R.id.item3:
                Intent highscore = new Intent(HomeActivity.this, Highscore.class);
                startActivity(highscore);
            case R.id.item4:
                Intent omspillet = new Intent(HomeActivity.this, omSpillet.class);
                startActivity(omspillet);

        }
        return super.onOptionsItemSelected(item);
    }
}
