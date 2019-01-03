package com.example.nicolasvahman.splashprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class guessGame extends AppCompatActivity {

    ListView listView;
    EditText input;
    Intent myIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        myIntent = new Intent(guessGame.this, guess2.class);
        listView = findViewById(R.id.listviewGame);

        String[] myData = {"Canada", "Danmark", "Frankrig","Holland", "England"};

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myData);

      listView.setAdapter(myAdapter);
      listView.setOnItemClickListener(listclick);










    }


    private AdapterView.OnItemClickListener listclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String item = (String) listView.getItemAtPosition(position).toString();
            myIntent.putExtra("et", item);
            startActivity(myIntent);

        }
    };



}
