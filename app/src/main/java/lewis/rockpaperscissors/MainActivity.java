package lewis.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreView, highScoreView, duelReportView;
    Button newGameButton;
    ImageButton rockButton, paperButton, scissorsButton;
    int points = 0, highscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = (TextView) findViewById(R.id.currentScore);
        highScoreView = (TextView) findViewById(R.id.highScore);
        duelReportView = (TextView) findViewById(R.id.duel);

        rockButton = (ImageButton) findViewById(R.id.rockButton);
        paperButton = (ImageButton) findViewById(R.id.paperButton);
        scissorsButton = (ImageButton) findViewById(R.id.scissorsButton);

        newGameButton = (Button) findViewById(R.id.newGameButton) ;

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duelReportView.setText("");
                gameOver();
            }
        });

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duel(0);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duel(1);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duel(2);
            }
        });
    }

    // simulates duel outcome between computer and human, given human attack type
    private void duel(int human) {
        int computer = new Random().nextInt(3);
        String duelReport;

        // determine outcome
        if (computer - human == 0) {
            points += 1;
            duelReport = "DRAW!";
        }
        else if ((computer - human + 3) % 3 == 1) {
            duelReport = "GAME OVER!\nComputer's " + attackName(computer) + " beats your " +
                    attackName(human) + "!\n\n A new game has been started.";
            gameOver();
        }
        else {
            points += 3;
            duelReport = "NICE!\n Your " + attackName(human) + " beats Computer's " + attackName(computer) + "!";
        }

        // report to user and adjust scores
        duelReportView.setText(duelReport);
        scoreView.setText("Points: " + points);
        highScoreView.setText("HIGHSCORE: " + highscore);
    }

    // ugly way to implement associative array
    private String attackName(int n) {
        if (n == 0) {
            return "rock";
        }
        if (n == 1) {
            return "paper";
        }
        if (n == 2) {
            return "scissors";
        }
        else {
            return "Invalid";
        }
    }

    // adjusts highscore and starts new game
    private void gameOver() {
        highscore = Math.max(highscore, points);
        points = 0;
        scoreView.setText("Points: 0");
        highScoreView.setText("HIGHSCORE: " + highscore);
    }
}
