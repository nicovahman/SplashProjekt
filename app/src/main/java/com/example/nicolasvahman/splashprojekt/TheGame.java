package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TheGame extends AppCompatActivity {


    Galgelogik logik = new Galgelogik();

    HomeActivity home1 = new HomeActivity();


    private TextView bbogstaver;
    private TextView velkomstinfo;

    private TextView info;
    private EditText gættefelt;
    private TextView forkert;
    private ImageView billeder;
    public String navnpaaspiller;

    private Button guess;

    private TextView spillerscore;
    private int scoreCounter;

    private int antalbogstaver;



    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editorHighscore;


    protected void onCreate(Bundle savedInstanceState) {
        final boolean spilletErVundet;
        super.onCreate(savedInstanceState);
        final boolean spilletErTabt;
        setContentView(R.layout.activity_the_game);
        getSupportActionBar().hide();

        guess = (Button) findViewById(R.id.guessbutton);

        gættefelt = (EditText) findViewById(R.id.gættesboks1);

        billeder = (ImageView) findViewById(R.id.imageView);
        billeder.setBackgroundResource(R.drawable.galgebasic);

        spillerscore = (TextView) findViewById(R.id.score);

        info = (TextView) findViewById(R.id.infotekst);
        info.setText("Du skal gætte ordet: " + logik.getSynligtOrd());
        gættefelt.setHint("Skriv et bogstav her");
        velkomstinfo = (TextView) findViewById(R.id.velkomst);

        forkert = (TextView) findViewById(R.id.forkertebogstaver);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            String fåSpillernavn = extras.getString("spillernavn");
            velkomstinfo.setText(fåSpillernavn);

        }



        gættefelt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = gættefelt.getText().toString();
                if (bogstav.length() != 1 || guess.isPressed()) {
                    gættefelt.setError("Der kan kun gættes et bogstav ad gangen");
                    gættefelt.animate().rotationBy(3 * 360).setInterpolator(new DecelerateInterpolator());
                    return;
                }

            }
        });

        info.setText("Henter ord fra Danmarks Radio");
        new MyAsyncTask(this).execute();

        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String bogstav = gættefelt.getText().toString();

                if (bogstav.length() != 1) {
                    gættefelt.setError("Indtast venligst kun et bogstav ad gangen");
                    gættefelt.animate().rotationBy(3 * 360).setInterpolator(new DecelerateInterpolator());
                    return;
                }

                if (gættefelt.getText().toString().trim().length() == 0) {
                    gættefelt.setError("Indtast et bogstav før du går videre");
                }


                Toast.makeText(getApplicationContext(), "Der gættes på bogstavet: " + bogstav.toUpperCase(), Toast.LENGTH_SHORT).show();
                gættefelt.setText("");
                logik.gætBogstav(bogstav);


                opdaterSkærm();


                if (logik.erSpilletTabt()) {
                    //info.setText("Du tabte spillet! Du havde 6 forkerte");
                    //forkert.setText("Du havde følgende forkerte bogstaver" + logik.getForkertebogstaver());
                    //gættefelt.setVisibility(View.INVISIBLE);
                    //tabtSpilDialog();


                }
                if (logik.erSidsteBogstavKorrekt()) {
                    Toast.makeText(getApplicationContext(), "Dit sidste bogstav " + bogstav.toUpperCase() + " var korrekt", Toast.LENGTH_SHORT).show();
                }

                if (logik.erSpilletVundet()) {

                    gættefelt.setVisibility(View.INVISIBLE);
                    billeder.setVisibility(View.INVISIBLE);


                }


            }


        });


    }
    private void opdaterSkærm() {

        info.setText("Gæt ordet: " + logik.getSynligtOrd());
        //info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());
        forkert.setText("Du har gættet på:\n " + logik.getForkertebogstaver().toString() + "\n" + "Antal forkerte:\n" + logik.getAntalForkerteBogstaver());
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 1) {
            billeder.setBackgroundResource(R.drawable.forkert1);

        }
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 2) {
            billeder.setBackgroundResource(R.drawable.forkert2);
        }
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 3) {
            billeder.setBackgroundResource(R.drawable.forkert3);
        }
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 4) {
            billeder.setBackgroundResource(R.drawable.forkert4);
        }
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 5) {
            billeder.setBackgroundResource(R.drawable.forkert5);
        }
        if (!logik.erSidsteBogstavKorrekt() && logik.getAntalForkerteBogstaver() == 6) {
            billeder.setBackgroundResource(R.drawable.forkert6);
        }

        if (logik.erSpilletSlut()) {
            forkert.setText("");
            if (logik.erSpilletVundet()) {
                gemHighScore();

                vinderScreen();



            }
            if (logik.erSpilletTabt()) {

               taberScreen();
            }


        }

    }





    public void tilbagetilForsiden() {
        Intent forside = new Intent(this, HomeActivity.class);
        startActivity(forside);
        this.finish();
    }


    private static class MyAsyncTask extends AsyncTask {

        private WeakReference<TheGame> wr;
        TheGame theGame;

        MyAsyncTask(TheGame game) {
            wr = new WeakReference<>(game);
            theGame = wr.get();
        }

        protected Object doInBackground(Object[] objects) {
            try {
                theGame.logik.hentOrdFraDr();
                return "Ordene blev hentet korrekt fra Danmarks Radio";
            } catch (Exception e) {
                e.printStackTrace();
                return "Ordene blev ikke hentet korrekt fra Danmarks Radio";
            }
        }

        protected void onPreExecute() {
            theGame.info.setText("Loading...");
        }

        protected void onPostExecute(Object resultat) {
            theGame.info.setText("Gæt ordet: \n" + theGame.logik.getSynligtOrd());
            System.out.println(theGame.logik.getOrdet() + "\n" + theGame.logik.getSynligtOrd() + "\n" + theGame.logik.muligeOrd);
        }
    }

    public void gemDialog() {
        final AlertDialog dlgAlert = new AlertDialog.Builder(this).create();
        dlgAlert.setMessage("Highscoren er nu blevet gemt");
        dlgAlert.setTitle("");
        dlgAlert.show();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dlgAlert.dismiss();
                finish();
            }
        }, 3000);

    }



    public void gemHighScore() {
        antalbogstaver = 6 - logik.getAntalForkerteBogstaver();








        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        scoreCounter = sharedPreferences.getInt("prefCount", 0);

        Log.d("LOL", "Score Counter: " + String.valueOf(scoreCounter));

        /*
        if (scoreCounter == 0) {
            scoreCounter = scoreCounter + 1;


        }
        */


        editor = sharedPreferences.edit();

        editor.putInt("highScorePref" + scoreCounter, antalbogstaver);
        editor.apply();


        editor.putInt("prefCount", ++scoreCounter);
        editor.apply();



        }



    public void vinderScreen(){

        String vinderInfo = "Du vandt spillet! Tillykke med det! Du har brugt følgende antal forsøg: " + logik.getAntalForkerteBogstaver() + " Din score er nu gemt. Se den under highscore.";
        Intent gåTilVinderSkærm = new Intent(TheGame.this, vinderscreen.class);


        gåTilVinderSkærm.putExtra("vinderKey",vinderInfo);
        startActivity(gåTilVinderSkærm);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }


    public void taberScreen(){

        String taberInfo = "Du har tabt spillet. " + " Du har brugt følgende antal forsøg: " + logik.getAntalForkerteBogstaver() + " Du skulle have gættet ordet: " + logik.getOrdet()  ;
        Intent gåTilTaberSkærm = new Intent(TheGame.this, taberscreen.class);


        gåTilTaberSkærm.putExtra("key",taberInfo);
        startActivity(gåTilTaberSkærm);
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









