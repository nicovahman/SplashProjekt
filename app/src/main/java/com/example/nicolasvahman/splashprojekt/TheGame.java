package com.example.nicolasvahman.splashprojekt;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TheGame extends AppCompatActivity {


    Galgelogik logik = new Galgelogik();


    private TextView bbogstaver;

    private TextView info;
    private EditText gættefelt;
    private TextView forkert;
    private ImageView billeder;
    private ImageView tilbageknap;
    private ImageView restart;
    private ImageView næste;
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



        gættefelt = (EditText) findViewById(R.id.gættesboks1);

        billeder = (ImageView) findViewById(R.id.imageView);
        billeder.setBackgroundResource(R.drawable.galgebasic);
        tilbageknap = (ImageView) findViewById(R.id.tilbage);
        restart = (ImageView) findViewById(R.id.restartknap);
        næste = (ImageView) findViewById(R.id.imageView5);
        spillerscore = (TextView) findViewById(R.id.score);

        info = (TextView) findViewById(R.id.infotekst);
        info.setText("Du skal gætte ordet: " + logik.getSynligtOrd());
        gættefelt.setHint("Skriv et bogstav her");

        forkert = (TextView) findViewById(R.id.forkertebogstaver);

        tilbageknap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilbagetilForsiden();
            }
        });

        gættefelt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = gættefelt.getText().toString();
                if (bogstav.length() != 1 || næste.isPressed()) {
                    gættefelt.setError("Der kan kun gættes et bogstav ad gangen");
                    gættefelt.animate().rotationBy(3 * 360).setInterpolator(new DecelerateInterpolator());
                    return;
                }

            }
        });

        info.setText("Henter ord fra Danmarks Radio");
        new MyAsyncTask(this).execute();

        næste.setOnClickListener(new View.OnClickListener() {
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
                    info.setText("Du har gættet korrekt og har vundet!");
                    forkert.setText("Du havde følgende forkerte bogstaver" + logik.getForkertebogstaver());
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
                vundetSpilDialog().show();

                //buildTextView();

            }
            if (logik.erSpilletTabt()) {
                //info.setText("Du har tabt, ordet var : " + logik.getOrdet());
                billeder.setVisibility(View.INVISIBLE);
                forkert.setVisibility(View.INVISIBLE);
                info.setVisibility(View.INVISIBLE);
                tabtSpilDialog().show();
            }


        }

    }


    private AlertDialog.Builder tabtSpilDialog() {
        final AlertDialog.Builder dlgAlertTaber = new AlertDialog.Builder(this);
        dlgAlertTaber.setMessage("Du har brugt følgende antal forsøg: " + logik.getAntalForkerteBogstaver() + "Du skulle have gættet ordet: " + logik.getOrdet());
        dlgAlertTaber.setTitle("Du tabte");
        ((AlertDialog.Builder) dlgAlertTaber).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                opdaterSkærm();
            }
        });

        dlgAlertTaber.create();

        logik.nulstil();
        return dlgAlertTaber;

    }

    private AlertDialog.Builder vundetSpilDialog() {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Du har brugt følgende antal forsøg: " + logik.getAntalForkerteBogstaver());
        dlgAlert.setTitle("Vundet spil");
        ((AlertDialog.Builder) dlgAlert).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                opdaterSkærm();
            }
        });

        dlgAlert.create();

        logik.nulstil();
        return dlgAlert;

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

    private void gemDialog() {
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



    private void gemHighScore() {
        antalbogstaver = 6 - logik.getAntalForkerteBogstaver();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        scoreCounter = sharedPreferences.getInt("prefCount", 0);

        Log.d("LOL", "Score Counter: " + String.valueOf(scoreCounter));

        /*
        if (scoreCounter == 0) {
            scoreCounter = scoreCounter + 1;


        }
        */

        Log.d("LOL", "Antal forkert: " + String.valueOf(antalbogstaver));

        editor = sharedPreferences.edit();

        editor.putInt("highScorePref" + scoreCounter, antalbogstaver);
        editor.apply();

        editor.putInt("prefCount", ++scoreCounter);
        editor.apply();

        gemDialog();



    }



}









