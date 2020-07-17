package com.example.game3connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int AlivePlayer =0;
    int counter = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winner[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean checkStateGame = true;
    public void couner(View view){
        TextView victory = (TextView)findViewById(R.id.textView);
        Button playagain = (Button)findViewById(R.id.playagain);
        ImageView couners = (ImageView)view;

        int tapperCouner = Integer.parseInt(couners.getTag().toString());
        if(checkStateGame && gameState[tapperCouner] == 2) {
            couners.setTranslationY(-1000);
            gameState[tapperCouner] = AlivePlayer;

            if (AlivePlayer == 0) {
                couners.setImageResource(R.drawable.yellow);
                couners.animate().translationYBy(1000).setDuration(500);
                AlivePlayer = 1;
            } else {
                couners.setImageResource(R.drawable.red);
                couners.animate().translationYBy(1000).setDuration(500);
                AlivePlayer = 0;
            }

            counter++;
            if(counter == gameState.length){
                victory.setText("Draw");
                victory.setVisibility(View.VISIBLE);
                playagain.setVisibility(View.VISIBLE);
            }

            for (int[] i : winner) {
                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2) {
                    checkStateGame = false;
                    String winner;
                    if (AlivePlayer == 0) {
                        winner = "Red winner";
                    } else {
                        winner = "Yellow winner";
                    }
                    counter = 0;
                    Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    victory.setText(winner);
                    victory.setVisibility(View.VISIBLE);
                    playagain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        TextView victory = (TextView)findViewById(R.id.textView);
        Button playagain = (Button)findViewById(R.id.playagain);
        victory.setVisibility(View.INVISIBLE);
        playagain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i< gridLayout.getChildCount(); i++){
            ImageView image = (ImageView)gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
        for(int i =0 ;i< gameState.length; i++){
            gameState[i] = 2;
        }
        /*int gameState1[] = {2,2,2,2,2,2,2,2,2};
        gameState = gameState1;*/
        checkStateGame = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
