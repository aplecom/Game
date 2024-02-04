package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Krenol extends AppCompatActivity {

    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;


    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krenol);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        result = findViewById(R.id.result);

        cross = "X";
        zero = "0";
    }

    public void clickButton1(View view) {
        button1.setText(cross);
        isWinner();
    }

    public void clickButton2(View view) {
        button2.setText(cross);
        isWinner();
    }

    public void clickButton3(View view) {
        button3.setText(cross);
        isWinner();
    }

    public void clickButton4(View view) {
        button4.setText(cross);
        isWinner();
    }

    public void clickButton5(View view) {
        button5.setText(cross);
        isWinner();
    }
    public void clickButton6(View view) {
        button6.setText(cross);
        isWinner();
    }
    public void clickButton7(View view) {
        button7.setText(cross);
        isWinner();
    }
    public void clickButton8(View view) {
        button8.setText(cross);
        isWinner();
    }
    public void clickButton9(View view) {
        button9.setText(cross);
        isWinner();
    }

    public void isWinner(){
        if(button1.getText() == cross && button2.getText() == cross && button3.getText() == cross){
            result.setText("Поздравляем победа");
        }
    }
}
