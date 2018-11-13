package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Key;

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
}
