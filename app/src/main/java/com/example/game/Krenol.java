package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Krenol extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private static final String CROSS = "X",ZERO = "0";

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
        clickedButton.setText(CROSS);
        isWinner();
    }
    public void isWinner() {

        if (checkWinCondition(0, 1, 2) ||
                checkWinCondition(3, 4, 5) ||
                checkWinCondition(6, 7, 8) ||
                checkWinCondition(0, 3, 6) ||
                checkWinCondition(1, 4, 7) ||
                checkWinCondition(2, 5, 8) ||
                checkWinCondition(0, 4, 8) ||
                checkWinCondition(2, 4, 6)) {
            result.setText("Поздравляем!");
        }
    }

    private boolean checkWinCondition(int pos1, int pos2, int pos3) {
        String text1 = buttons[pos1].getText().toString();
        String text2 = buttons[pos2].getText().toString();
        String text3 = buttons[pos3].getText().toString();

        return !text1.isEmpty() && text1.equals(text2) && text2.equals(text3);
    }
}
