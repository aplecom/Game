package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Krenol extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private static final String CROSS = "X",ZERO = "0";
    private boolean playerTurn = true; //  чей ход , true - игрок
    private boolean gameOver = false;

    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krenol);

        for (int i = 0; i < buttons.length; i++) {
            int buttonId = getResources().getIdentifier("button" + (i + 1), "id", getPackageName());
            buttons[i] = findViewById(buttonId);
        }

        result = findViewById(R.id.result);
    }


    public void clickButton(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().toString().isEmpty() && !gameOver) {
            if (playerTurn) {
                clickedButton.setText(CROSS);
            }
            checkWinner(CROSS);
            movePC();
        }
    }
    private void movePC() {
        if (!gameOver) {
            Random random = new Random();
            int randomPosition;
            do {
                randomPosition = random.nextInt(9);
            } while (!buttons[randomPosition].getText().toString().isEmpty());

            buttons[randomPosition].setText(ZERO);
            playerTurn = true;
            checkWinner(ZERO);
        }
    }

    private boolean checkPlayerWin(String player) {
        int[][] WIN_COMBINATIONS = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Горизонтальные
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Вертикальные
                {0, 4, 8}, {2, 4, 6}             // Диагональные
        };

        for (int[] winCombo : WIN_COMBINATIONS) {
            String text1 = buttons[winCombo[0]].getText().toString();
            String text2 = buttons[winCombo[1]].getText().toString();
            String text3 = buttons[winCombo[2]].getText().toString();


            if (!text1.isEmpty() && text1.equals(text2) && text2.equals(text3) && text1.equals(player)) {
                result.setText(getResources().getString(R.string.msg_winner, player));
                return true;
            }

        }
        return false;
    }

    private void checkWinner(String player) {
        if (checkPlayerWin(player)) {
            gameOver = true;
            return;
        }

        if (isBoardFull()) {
            gameOver = true;
        }
    }

    private boolean isBoardFull() { // проверка на ничью
        for (Button button : buttons) {
            if (button.getText().toString().isEmpty()) {
                return false;
            }
        }
        result.setText(R.string.msg_draw);
        return true;
    }

    public void clickRestart(View view) {
        result.setText("");
        gameOver=false;
        for (int i = 0; i < buttons.length; i++)
            buttons[i].setText("");
        }
    }
