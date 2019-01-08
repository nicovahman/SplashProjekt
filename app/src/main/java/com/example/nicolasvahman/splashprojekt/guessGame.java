package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class guessGame extends HomeActivity {

    ListView listView;
    EditText input;
    Intent videreinfo;
    Button tilfoj;
    EditText indtast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        tilfoj = findViewById(R.id.addbutton);
        indtast = findViewById(R.id.resultat);

        videreinfo = new Intent(guessGame.this, guess2.class);
        listView = findViewById(R.id.listviewGame);



        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Canada");
        arrayList.add("Danmark");
        arrayList.add("Frankrig");
        arrayList.add("Holland");
        arrayList.add("England");

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

      listView.setAdapter(myAdapter);
      listView.setOnItemClickListener(listclick);



      tilfoj.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String add = indtast.getText().toString();
              arrayList.add(add);
              myAdapter.notifyDataSetChanged();
          }
      });







    }


    private AdapterView.OnItemClickListener listclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String detvalgte = (String) listView.getItemAtPosition(position).toString();
            videreinfo.putExtra("ett", detvalgte);
            startActivity(videreinfo);
            finish();

        }
    };






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
