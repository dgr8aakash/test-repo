package com.dgr8akki.bollywood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class multi extends AppCompatActivity {
    String movie;
    int mcounter=0;
    int guessedLetters=0;
    int mpts=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming Soon", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String movietoguess = getIntent().getStringExtra("MovieName");
        movie=movietoguess;
        Log.d("MY LOG", "Movie is" + movietoguess);
        createTextview(movietoguess);

    }

    /**
     * @param v
     */
    public void introduceLetter(View v) {
        EditText mytext = (EditText) findViewById(R.id.editText);
        String letter = mytext.getText().toString();
       Log.d("MY LOG", "Letter pressed is" + letter);
        mytext.setText("");
        if (letter.length() == 1) {
            checkLetter(letter.toUpperCase());
        } else {
            Toast.makeText(this, "Enter any letter", Toast.LENGTH_LONG).show();
        }
    }

public void createTextview(String arg){
    LinearLayout layoutlinear = (LinearLayout) findViewById(R.id.showwordlayout);

    for(int i=0;i<arg.length();i++)
    {

            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text, null);
            layoutlinear.addView(textView);
    }
    vowelhint();
}

    public void vowelhint() {
        String str = "/ AEIOU";
        for (int j=0;j<str.length();j++) {
            char charintroduced = str.charAt(j);
            for (int i = 0; i < movie.length(); i++) {
                char characterFromWord = movie.charAt(i);
                Log.d("MY LOG", "Letter comparing " + characterFromWord);
                if (characterFromWord == charintroduced) {
                    guessedLetters++;
                    showLetters(i, charintroduced);
                }
            }
        }
    }

    public void checkLetter(String str) {
        boolean letterguessed = false;
        char charintroduced= str.charAt(0);
        for(int i=0;i<movie.length();i++){
            char characterFromWord = movie.charAt(i);
            Log.d("MY LOG","Letter comparing "+characterFromWord);
            if(characterFromWord==charintroduced){
                guessedLetters++;
                Log.d("MY LOG","Letter matched");
                letterguessed = true;
                showLetters(i,charintroduced);
            }
        }
        if (letterguessed == false){
            letterfail(Character.toString(charintroduced));
        }
        if (guessedLetters==movie.length())
        {
          finish();
        }
    }


    public void clrscr(){
        TextView failedLetters = (TextView) findViewById(R.id.textView11);
        failedLetters.setText("");

        int mcounter=0;
        int guessedLetters=0;
        LinearLayout Layout = (LinearLayout)findViewById(R.id.showwordlayout);
        for (int i=0;i<Layout.getChildCount();i++)
        {
                TextView currentTextView = (TextView) Layout.getChildAt(i);
                    currentTextView.setText("_ ");
        }
        ImageView image= (ImageView)findViewById(R.id.imageView);
        image.setImageResource(R.drawable.b0);
    }





    public void letterfail(String failed)
    {

        TextView failedLetters = (TextView) findViewById(R.id.textView11);
        String prevFail = failedLetters.getText().toString();
        failedLetters.setText(prevFail+failed);
        mcounter++;
        ImageView image= (ImageView)findViewById(R.id.imageView);
        switch(mcounter){
            case 1: image.setImageResource(R.drawable.b1);
                break;
            case 2: image.setImageResource(R.drawable.b2);
                break;
            case 3: image.setImageResource(R.drawable.b3);
                break;
            case 4: image.setImageResource(R.drawable.b4);
                break;
            case 5: image.setImageResource(R.drawable.b5);
                break;
            case 6: image.setImageResource(R.drawable.b6);
                break;
            case 7: image.setImageResource(R.drawable.b7);
                break;
            case 8: image.setImageResource(R.drawable.b8);
                break;
            case 9:
            {
                Intent gameOver = new Intent(this,GameOver.class);

                gameOver.putExtra("Game_Points",mpts);

                startActivity(gameOver);
            }

        }

    }



    public void showLetters(int position,char letteruess){
        LinearLayout letterlayout = (LinearLayout) findViewById(R.id.showwordlayout);
        TextView textView1 = (TextView) letterlayout.getChildAt(position);
        textView1.setText(Character.toString(letteruess));

    }
}
