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
    public Intent spillernavnet;

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



        næsteside = (ImageView) findViewById(R.id.nextpage);
        ArrayAdapter <String> adapter;





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
        finish();
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
        startActivity(Intent.createChooser(intent4, "Skriv en mail til en ven, og fortæl om spillet. Vælg en email du vil benytte"));
        finish();

    }

    public void GoToAlmindeligOrd(){
       Intent ordFraDR = new Intent(this, AlmindeligtSpil.class);
       startActivity(ordFraDR);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();

    }

    public void GoToHighscore(){
        Intent highscoreside = new Intent(this, Highscore.class);
        startActivity(highscoreside);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void spillerensNavn(){

        String fåSpillernavn = "Velkommen til hangman " + spillerNavn.getText().toString();
        Intent navnTilSpil = new Intent(HomeActivity.this, TheGame.class);

        navnTilSpil.putExtra("spillernavn", fåSpillernavn);
        startActivity(navnTilSpil);
        finish();
    }

    public void returnSpillernavnetTilVinder(){
        String returTilVinder = "Tillykke med det!" + spillerNavn.getText().toString();

        Intent tilVinder = new Intent(getApplicationContext(), vinderscreen.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("vindernavnetoveralle", returTilVinder);
        tilVinder.putExtras(tilVinder);

        startActivity(tilVinder);
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
            case R.id.item5:
                anbefalSpilletTilenVen();
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


}
