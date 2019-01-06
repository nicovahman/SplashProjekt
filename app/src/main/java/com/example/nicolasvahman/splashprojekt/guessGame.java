package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class guessGame extends AppCompatActivity {

    ListView listView;
    EditText input;
    Intent myIntent;
    Button tilfoj;
    EditText indtast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        tilfoj = findViewById(R.id.addbutton);
        indtast = findViewById(R.id.resultat);

        myIntent = new Intent(guessGame.this, guess2.class);
        listView = findViewById(R.id.listviewGame);

        final String[] myData = {"Canada", "Danmark", "Frankrig","Holland", "England"};

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

            String item = (String) listView.getItemAtPosition(position).toString();
            myIntent.putExtra("et", item);
            startActivity(myIntent);

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
                Intent startside = new Intent( guessGame.this, HomeActivity.class);
                startActivity(startside);

            case R.id.item2:
                Intent gættelegen = new Intent(guessGame.this, guessGame.class);
                startActivity(gættelegen);
            case R.id.item3:
                Intent highscore = new Intent(guessGame.this, Highscore.class);
                startActivity(highscore);
            case R.id.item4:
                Intent omspillet = new Intent(guessGame.this, omSpillet.class);
                startActivity(omspillet);

        }
        return super.onOptionsItemSelected(item);
    }


}
