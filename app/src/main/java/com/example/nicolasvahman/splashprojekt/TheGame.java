package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;protected void onCreate(Bundle savedInstanceState) {
        final boolean spilletErVundet;    super.onCreate(savedInstanceState);
        final boolean spilletErTabt;    setContentView(R.layout.activity_the_game);



        gættefelt = (EditText) findViewById(R.id.gættesboks);

        billeder = (ImageView) findViewById(R.id.imageView);
        billeder.setBackgroundResource(R.drawable.galgebasic);
        tilbageknap = (ImageView) findViewById(R.id.tilbage);
        restart = (ImageView) findViewById(R.id.restartknap);
        næste = (ImageView) findViewById(R.id.imageView5);

        info = (TextView) findViewById(R.id.infotekst);
        info.setText("Du skal gætte ordet: " + logik.getSynligtOrd());
        gættefelt.setHint("Skriv et bogstav her");

        forkert = (TextView) findViewById(R.id.forkertebogstaver);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


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


        næste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = gættefelt.getText().toString();

                if (bogstav.length() != 1) {
                    gættefelt.setError("Indtast venligst kun et bogstav ad gangen");
                    gættefelt.animate().rotationBy(3 * 360).setInterpolator(new DecelerateInterpolator());
                    return;
                }

                if(gættefelt.getText().toString().trim().length()==0){
                    gættefelt.setError("Indtast et bogstav før du går videre");
                }


                Toast.makeText(getApplicationContext(), "Der gættes på bogstavet: " + bogstav.toUpperCase(), Toast.LENGTH_SHORT).show();
                gættefelt.setText("");
                logik.gætBogstav(bogstav);




                opdaterSkærm();



                if(antalForkerteBogstaver>6 && logik.erSpilletTabt()){
                    info.setText("Du tabte spillet! Du havde 6 forkerte");
                    forkert.setText("Du havde følgende forkerte bogstaver" + logik.getForkertebogstaver());
                    gættefelt.setVisibility(View.INVISIBLE);
                    billeder.setVisibility(View.INVISIBLE);
                }
                if(logik.erSidsteBogstavKorrekt()){
                    Toast.makeText(getApplicationContext(),"Dit sidste bogstav " + bogstav.toUpperCase() + " var korrekt", Toast.LENGTH_SHORT).show();
                }

                if(logik.erSpilletVundet()){
                    info.setText("Du har gættet korrekt og har vundet!");
                    forkert.setText("Du havde følgende forkerte bogstaver" + logik.getForkertebogstaver()+ " Men det er da ligemeget!");
                    gættefelt.setVisibility(View.INVISIBLE);
                    billeder.setVisibility(View.INVISIBLE);
                }
                if(logik.erSpilletVundet() && logik.getAntalForkerteBogstaver()==0 ){
                    info.setText("Du vandt og havde ovenikøbet 0 forkerte gæt!");
                    forkert.setText("");
                    billeder.setVisibility(View.INVISIBLE);
                    gættefelt.setVisibility(View.INVISIBLE);
                }

            }


        });



    }



    private void opdaterSkærm() {

        info.setText("Gæt ordet: " + logik.getSynligtOrd().toString());
        //info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());
        forkert.setText("Du har gættet på:\n "  +logik.getForkertebogstaver().toString()+"\n"+"Antal forkerte:\n"+logik.getAntalForkerteBogstaver());
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==1){
            billeder.setBackgroundResource(R.drawable.forkert1);
        }
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==2){
            billeder.setBackgroundResource(R.drawable.forkert2);
        }
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==3){
            billeder.setBackgroundResource(R.drawable.forkert3);
        }
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==4){
            billeder.setBackgroundResource(R.drawable.forkert4);
        }
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==5){
            billeder.setBackgroundResource(R.drawable.forkert5);
        }
        if(!logik.erSidsteBogstavKorrekt()&&logik.getAntalForkerteBogstaver()==6){
            billeder.setBackgroundResource(R.drawable.forkert6);
        }

        if (logik.erSpilletVundet()) {
            info.append("\nDu har vundet!");
        }
        if (logik.erSpilletTabt()) {
            info.setText("Du har tabt, ordet var : " + logik.getOrdet());
        }
    }




    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void restartGame(){
        Intent resfresh = new Intent(this, TheGame.class);
        startActivity(resfresh);
        this.finish();
    }
    public void tilbagetilForsiden(){
        Intent forside = new Intent(this, HomeActivity.class);
        startActivity(forside);
        this.finish();
    }

}
