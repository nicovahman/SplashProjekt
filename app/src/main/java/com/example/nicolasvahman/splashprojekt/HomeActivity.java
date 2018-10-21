package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    private Button spil;
    private Button anbefaltilven;
    private EditText editTextto;
    private EditText editTextEmne;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spil = (Button) findViewById(R.id.spilspillet);
        anbefaltilven = (Button) findViewById(R.id.anbefal);
        editTextto = findViewById(R.id.emailadresse);
        editTextEmne = findViewById(R.id.emneTilmailen);
        editTextMessage = findViewById(R.id.emailBesked);

        spil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spilSpillet();
            }
        });

        anbefaltilven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anbefalSpilletTilenVen();
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

}
