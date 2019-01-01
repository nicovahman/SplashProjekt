package com.example.nicolasvahman.splashprojekt;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class guessGame extends AppCompatActivity {

    ListView listView;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);


        listView = (ListView) findViewById(R.id.listviewGame);
        final ArrayList<String> arrayList = new ArrayList<>();


        arrayList.add("Oh Canada");
        arrayList.add("GDay Mate");
        arrayList.add("Rød Grød Med Fløde");
        arrayList.add("Flicka, Hey Dår, Boten Anna");
        arrayList.add("Bond, James Bond");
        arrayList.add("Red Neck");
        arrayList.add("Sangria");
        arrayList.add("Weissbier");
        arrayList.add("Crossaint");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(guessGame.this,"Du har trykket på:" + " " + arrayList.get(position),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(guessGame.this);
                builder.setTitle("Gæt ordet");
                builder.setMessage("Du skal gætte ordet " + arrayList.get(position));

                input = new EditText(guessGame.this);
                builder.setView(input);

                builder.setPositiveButton("Gæt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tekst = input.getText().toString();
                        Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
                    }
                });
                }





        });

    }



}
