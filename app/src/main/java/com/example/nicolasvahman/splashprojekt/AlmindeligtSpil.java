package com.example.nicolasvahman.splashprojekt;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlmindeligtSpil extends AppCompatActivity {

    Galgelogik2 logik2 = new Galgelogik2();
    TheGame game = new TheGame();

    private TextView info2;
    private EditText gættefelt2;
    private ImageView billeder2;
    private ImageView næsteknap2;
    private TextView forkert2;

    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almindeligt_spil);
        final boolean spilletErVundet2;
        final boolean spilletErTabt2;

        info2 = (TextView) findViewById(R.id.info2);
        gættefelt2 = (EditText) findViewById(R.id.gætteboks2);
        billeder2 = (ImageView) findViewById(R.id.galge);
        næsteknap2 = (ImageView) findViewById(R.id.næsteknap2);
        forkert2 = (TextView) findViewById(R.id.forkertebogstaver2);

        info2.setText("Du skal gætte ordet: " + logik2.getSynligtOrd());
        gættefelt2.setHint("Skriv et bogstav her");

        næsteknap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = gættefelt2.getText().toString();

                if (bogstav.length() != 1) {
                    gættefelt2.setError("Indtast venligst kun et bogstav ad gangen");
                    gættefelt2.animate().rotationBy(3 * 360).setInterpolator(new DecelerateInterpolator());
                    return;
                }

                if (gættefelt2.getText().toString().trim().length() == 0) {
                    gættefelt2.setError("Indtast et bogstav før du går videre");
                }


                Toast.makeText(getApplicationContext(), "Der gættes på bogstavet: " + bogstav.toUpperCase(), Toast.LENGTH_SHORT).show();
                gættefelt2.setText("");
                logik2.gætBogstav(bogstav);


                opdaterSkærm2();


                if (antalForkerteBogstaver > 6 && logik2.erSpilletTabt()) {
                    info2.setText("Du tabte spillet! Du havde 6 forkerte");
                    forkert2.setText("Du havde følgende forkerte bogstaver" + logik2.getForkertebogstaver());
                    gættefelt2.setVisibility(View.INVISIBLE);
                    billeder2.setVisibility(View.INVISIBLE);
                }
                if (logik2.erSidsteBogstavKorrekt()) {
                    Toast.makeText(getApplicationContext(), "Dit sidste bogstav " + bogstav.toUpperCase() + " var korrekt", Toast.LENGTH_SHORT).show();
                }

                if (logik2.erSpilletVundet()) {
                    info2.setText("Du har gættet korrekt og har vundet!");
                    forkert2.setText("Du havde følgende forkerte bogstaver" + logik2.getForkertebogstaver() + " Men det er da ligemeget!");
                    gættefelt2.setVisibility(View.INVISIBLE);
                    billeder2.setVisibility(View.INVISIBLE);

                }
                if (logik2.erSpilletVundet() && logik2.getAntalForkerteBogstaver() == 0) {
                    info2.setText("Du vandt og havde ovenikøbet 0 forkerte gæt!");
                    forkert2.setText("");
                    billeder2.setVisibility(View.INVISIBLE);
                    gættefelt2.setVisibility(View.INVISIBLE);

                }

            }


        });







    }

    private void opdaterSkærm2(){
        info2.setText("Gæt ordet: " + logik2.getSynligtOrd());
        //info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());
        forkert2.setText("Du har gættet på:\n " + logik2.getForkertebogstaver().toString() + "\n" + "Antal forkerte:\n" + logik2.getAntalForkerteBogstaver());
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 1) {
            billeder2.setBackgroundResource(R.drawable.forkert1);
        }
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 2) {
            billeder2.setBackgroundResource(R.drawable.forkert2);
        }
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 3) {
            billeder2.setBackgroundResource(R.drawable.forkert3);
        }
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 4) {
            billeder2.setBackgroundResource(R.drawable.forkert4);
        }
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 5) {
            billeder2.setBackgroundResource(R.drawable.forkert5);
        }
        if (!logik2.erSidsteBogstavKorrekt() && logik2.getAntalForkerteBogstaver() == 6) {
            billeder2.setBackgroundResource(R.drawable.forkert6);
        }

        if (logik2.erSpilletVundet()) {
            info2.append("\nDu har vundet!");
        }
        if (logik2.erSpilletTabt()) {
            info2.setText("Du har tabt, ordet var : " + logik2.getOrdet());
        }
    }
}




