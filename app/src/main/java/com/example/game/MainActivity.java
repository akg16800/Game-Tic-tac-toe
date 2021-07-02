package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    public void dropIn(View view)
    {
        ImageView counter= (ImageView)view;
//        Log.i("Tag",counter.getTag().toString());
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive)
        {
        gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1500);
        if(activePlayer==0 )
            {
                counter.setImageResource(R.drawable.x_red);
                activePlayer = 1;
            }
        else
            {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);
            for (int[] winningPostion : winningPositions) {
                if (gameState[winningPostion[0]] == gameState[winningPostion[1]] && gameState[winningPostion[1]] == gameState[winningPostion[2]] && gameState[winningPostion[0]] != 2) {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1)
                        winner = "x";
                    else
                        winner = "zero";
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playagainbutton=findViewById(R.id.playagain);
                    TextView winnerT=findViewById(R.id.textView2);
                    winnerT.setText(winner+" has won");
                    playagainbutton.setVisibility(view.VISIBLE);
                    winnerT.setVisibility(view.VISIBLE);

                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button playagainbutton=findViewById(R.id.playagain);
        TextView winnerT=findViewById(R.id.textView2);
        playagainbutton.setVisibility(view.INVISIBLE);
        winnerT.setVisibility(view.INVISIBLE);
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}