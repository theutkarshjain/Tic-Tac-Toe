package utkarshjain.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 is tic and 1 is for cross
    int activePlayer = 0;
    boolean gameisactive = true;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningposition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view)
    {

        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
          if(gamestate[tappedcounter] == 2 && gameisactive)
          {
            gamestate[tappedcounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.checkmark);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(200);
            for(int[] win : winningposition )
                if (gamestate[win[0]] == gamestate[win[1]] &&
                        gamestate[win[1]] == gamestate[win[2]] &&
                        gamestate[win[0]] != 2) {
                    System.out.println(gamestate[win[0]]);
                    gameisactive = false;
                    String winner;

                    if(gamestate[win[0]] == 0)
                        winner = "Tick";
                    else
                        winner = "Cross";

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText( winner+ " Won");
                    LinearLayout layout =(LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                boolean gameisover = true;
              for (int counterstate : gamestate) {
                  if(counterstate == 2)
                      gameisover = false;
              }
              if(gameisover)
              {
                  TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                  winnerMessage.setText( "It's a Draw");
                  LinearLayout layout =(LinearLayout) findViewById(R.id.playAgainLayout);
                  layout.setVisibility(View.VISIBLE);
              }
         }
    }

    public void playagain(View view)
    {
        gameisactive =true;
        activePlayer = 0;
        LinearLayout layout =(LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        for(int  i=0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        GridLayout gridlayout = (GridLayout) findViewById(R.id.grid);
        for(int i =0; i<gridlayout.getChildCount();i++)
        {
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
