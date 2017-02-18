package com.dgr8akki.bollywood;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
int mypoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming,On the Way :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

       int points= getIntent().getIntExtra("Game_Points", 0);
        TextView text = (TextView)findViewById(R.id.textViewscore);
        text.setText(String.valueOf(points));
        mypoints=points;


    }


    public void savescore(View view){
        SharedPreferences preferences = getSharedPreferences("MYPREFRENCES", Context.MODE_PRIVATE);
        EditText editText = (EditText)findViewById(R.id.editTextname);
        String name = editText.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        String prev = preferences.getString("Scores","");
        editor.putString("Scores",name + " "+ mypoints+" Points\n" + prev);
        editor.commit();
        finish();
    }

}
