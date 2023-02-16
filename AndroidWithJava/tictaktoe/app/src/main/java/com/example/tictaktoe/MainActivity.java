package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, restart;
    TextView turnshow;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    String turn = "O";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        turnshow = findViewById(R.id.turnshow);

        turn = "O";
        turnshow.setText("turn for " + turn);
        count = 0;
    }

    private void winMessage(String winner) {
        Toast.makeText(this, "winner is :" + winner, Toast.LENGTH_SHORT).show();
    }

    private void drawMessage() {
        Toast.makeText(this, "Match is drawn :", Toast.LENGTH_SHORT).show();
    }

    private void newGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count = 0;
        this.turn = turn == "X" ? "O" : "X";
        turnshow.setText("turn for " + turn);

    }

    public void check(View view) {


        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().equals("New Game")) {
            newGame();
        }

        if (btnCurrent.getText().toString().equals("")) {
            count++;
            if (turn.equals("X")) {
                btnCurrent.setText(turn);
                turn = "O";
            } else {
                btnCurrent.setText(turn);
                turn = "X";
            }turnshow.setText("turn for " + turn);

            //conditions
            if (count >= 5) {
                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();

                //wining condition
                {
                    //row 1
                    if (b1.equals(b2) && b2.equals(b3) && !b3.equals("")) {
                        winMessage(b3);
                        newGame();
                    }
                    //row 2
                    else if (b4.equals(b5) && b5.equals(b6) && !b6.equals("")) {
                        winMessage(b6);
                        newGame();

                    }
                    //row 3
                    else if (b7.equals(b8) && b8.equals(b9) && !b9.equals("")) {
                        winMessage(b9);
                        newGame();
                    } else if (b1.equals(b4) && b4.equals(b7) && !b7.equals("")) {
                        winMessage(b7);
                        newGame();
                    }
                    // col 2
                    else if (b2.equals(b5) && b5.equals(b8) && !b8.equals("")) {
                        winMessage(b8);
                        newGame();
                    }
                    //col 3
                    else if (b3.equals(b6) && b6.equals(b9) && !b9.equals("")) {
                        winMessage(b9);
                        newGame();
                    }
                    // principle diagonal 1
                    else if (b1.equals(b5) && b5.equals(b9) && !b9.equals("")) {
                        winMessage(b9);
                        newGame();
                    }
                    // principle diagonal 2
                    else if (b3.equals(b5) && b5.equals(b7) && !b7.equals("")) {
                        winMessage(b7);
                        newGame();
                    } else if (count == 9) {
                        drawMessage();
                        newGame();
                    }
                }

            }
        }

    }
}