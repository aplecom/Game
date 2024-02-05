package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Krenol extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private static final String CROSS = "X",ZERO = "0";
    private boolean playerTurn = true; //  чей ход , true - игрок
    private boolean gameOver = false;
    private int[] winningCombination; // поле для хранения победной комбинации

    private TextView result,pointsPC,pointsPlayer;
    private int pointsOfPC,pointsOfPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krenol);

        for (int i = 0; i < buttons.length; i++) {
            int buttonId = getResources().getIdentifier("button" + (i + 1), "id", getPackageName());
            buttons[i] = findViewById(buttonId);
        }

        result = findViewById(R.id.result);
        pointsPC = findViewById(R.id.pointsPC);
        pointsPlayer = findViewById(R.id.pointsPlayer);
        updatePointsTextViews();
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
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // горизонтальные
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // вертикальные
                {0, 4, 8}, {2, 4, 6}             // диагональные
        };

        for (int[] winCombo : WIN_COMBINATIONS) {
            String text1 = buttons[winCombo[0]].getText().toString();
            String text2 = buttons[winCombo[1]].getText().toString();
            String text3 = buttons[winCombo[2]].getText().toString();


            if (!text1.isEmpty() && text1.equals(text2) && text2.equals(text3) && text1.equals(player)) {
                result.setText(getResources().getString(R.string.msg_winner, player));
                winningCombination = winCombo;
                updatePoints(player);
                return true;
            }

        }
        return false;
    }

    private void checkWinner(String player) {
        if (checkPlayerWin(player)) {
            gameOver = true;
            endGameEffects();
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

    private void updatePoints(String player) {
        if (player.equals(CROSS)) {
            pointsOfPlayer++;
        } else if (player.equals(ZERO)) {
            pointsOfPC++;
        }
        updatePointsTextViews();
    }

    private void updatePointsTextViews() {
        pointsPlayer.setText(getString(R.string.points_player, pointsOfPlayer));
        pointsPC.setText(getString(R.string.points_pc, pointsOfPC));
    }

    private void endGameEffects() {
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);


            for (int index : winningCombination) {
                buttons[index].startAnimation(fadeAnimation);

        }
    }

}
