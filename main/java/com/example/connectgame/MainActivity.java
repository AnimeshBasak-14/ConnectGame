package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // 0: yellow, 1: red, 2: empty

    String[][] funnyLine={{"I bet you look like you were drawn with my left hand"},
            {"Quit being a spherical dumbass"},
            {"You live in your mom’s basement you anti social fucking loser"},
            {"You won at what BEING USELESS"},
            {" That someone hopes I get herpes from sitting on a toilet seat, because he was sure I would never get the opportunity to get it from having sex "},
            {"I would have win this game by my BUTT!"},
            {"Did you just get your damn goldfish and let it flop all over the screen? That's why you won IDIOT"},
            {"I hope you're better at being a dad than you are at this game, old man."},
            {"I bet your bathroom floor smells like piss with the aim you have"},
            {"I'd say your aim is cancer but even cancer win in life"},
            {"Tell me, what do crayons taste like?"},
            {"If the child worker could see how bad you use your life he made, he would throw himself out of the sweat shop."},
            {"PADHLE BHOSDIKE"},
            {"You’re the reason God created the middle finger"},
            {"You bring everyone so much joy when you leave the room"},
            {"I’d give you a nasty look but you’ve already got one."},
            {"Were you born this stupid or did you take lessons?"},
            {"You are so full of shit, the toilet’s jealous"},
            {"I thought of you today. It reminded me to take out the trash"},
            {"Do your parents even realize they’re living proof that two wrongs don’t make a right?"},
            {"You are more disappointing than an unsalted pretzel"},
            {"Your face makes onions cry."},
            {"Isn’t there a bullet somewhere you could be jumping in front of?"},
            {"I’d slap you but I don’t want to make your face look any better."}
    };

    public int randGenerator()
    {
        final int min = 0;
        final int max = 24;
        return new Random().nextInt((max - min) + 1) + min;
    }
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Yellow";

                    } else {

                        winner = "Red";

                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                    Toast.makeText(this, funnyLine[randGenerator()][0], Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}