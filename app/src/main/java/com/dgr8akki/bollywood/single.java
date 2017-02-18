
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

import static com.dgr8akki.bollywood.R.id.imageView;

public class single extends AppCompatActivity {
    String movie;
    int mcounter=0;
    int guessedLetters=0;
    int mpts=0;
    int len=0;
    int k=1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
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
        setRandomMovie();
    }




    public void setRandomMovie(){
        String words="SHOLEY,DEEWAR,SANAM/TERI/KASAM,16/DECEMBER,AA/AB/LAUT/CHALE";
        // String words="BAB";
        String[] arraywords= words.split(",");
        int randomno=(int)(Math.random() * arraywords.length);

        String randomWord=arraywords[randomno];
        movie=randomWord;
        len=movie.length();
        createTextview(movie);
        Log.d("MY Log", "Movie is "+movie);
    }


    public void createTextview(String arg){
        LinearLayout layoutlinear = (LinearLayout) findViewById(R.id.showletterlayout);
        if(((LinearLayout) layoutlinear).getChildCount() > 0)
            ((LinearLayout) layoutlinear).removeAllViews();
        Log.d("MY Log", "2nd child count is  " + layoutlinear.getChildCount());
        for(int i=0;i<arg.length();i++)
        {

            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text, null);
            layoutlinear.addView(textView);
            Log.d("MY Log", "I is " + i);
        }

        vowelhint();
    }



    public void vowelhint()
    {

        String str = "/ AEIOU";
        for (int j=0;j<str.length();j++)
        {
            char charintroduced = str.charAt(j);
            for (int i = 0; i < movie.length(); i++)
            {
                char characterFromWord = movie.charAt(i);
                if (characterFromWord == charintroduced)
                {
                    guessedLetters++;
                    showLetters(i, charintroduced);
                }
            }
        }
    }


// |-----------------------------------------------------------------------------------|
// |                  Button Call this method                                          |
// |-----------------------------------------------------------------------------------|



    public void introduceLetter(View v) {
        EditText mytext = (EditText) findViewById(R.id.editText);
        String letter = mytext.getText().toString();
        mytext.setText("");
        if (letter.length() == 1) {
            checkLetter(letter.toUpperCase());
        } else {
            Toast.makeText(this, "Enter any letter", Toast.LENGTH_LONG).show();
        }
    }





    public void checkLetter(String str) {
        boolean letterguessed = false;
        char charintroduced= str.charAt(0);
        for(int i=0;i<movie.length();i++){
            char characterFromWord = movie.charAt(i);
            if(characterFromWord==charintroduced){
                guessedLetters++;
                Log.d("MY Log","letter "+guessedLetters+" check ho chuke");
                Log.d("MY Log","Movie k letters "+len+" h");
                letterguessed = true;
                showLetters(i,charintroduced);
            }
        }
        if (letterguessed == false){
            letterfail(Character.toString(charintroduced));
        }
        if (guessedLetters==len)
        {
            mpts++;
            clrscr();
            setRandomMovie();

        }
    }



    public void clrscr(){
        TextView failedLetters = (TextView) findViewById(R.id.textView11);
        failedLetters.setText("");

        mcounter=0;
        guessedLetters=0;

        LinearLayout myLayout = (LinearLayout)findViewById(R.id.showletterlayout);

       Log.d("MY Log", "child count is  " + myLayout.getChildCount());
        for (int i=0;i<myLayout.getChildCount();i++)
        {
                TextView currentTextView = (TextView) myLayout.getChildAt(i);
//            String inc = String.valueOf(i);
//                    currentTextView.setText(inc);
            currentTextView.setText("_");
            currentTextView.setPadding(0,0,4,0);
            Log.d("MY Log", "child no  " + (i + 1) + " Bn chuka");

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
                finish();

            }



        }

    }


    public void showLetters(int position,char letterguess){
      Log.d("MY Log","showletter m entry "+k+" baar");
        LinearLayout letterlayout = (LinearLayout) findViewById(R.id.showletterlayout);
        TextView textView1 = (TextView) letterlayout.getChildAt(position);
        textView1.setText(Character.toString(letterguess));
        k++;
    }


}
