package com.shubham.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private TextView sumText;
    private TextView resultText;
    private TextView pointsText;
    private TextView timerText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ArrayList<Integer> answers = new ArrayList<Integer>();
    private int locationOfCorrectAnswer;
    private int score = 0;
    private int numQuestions = 0;
    Button playAgainButton;
    RelativeLayout gameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumText = (TextView) findViewById(R.id.sumText);
        startButton = (Button) findViewById(R.id.startButton);
        timerText = (TextView) findViewById(R.id.timerText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultText = (TextView) findViewById(R.id.resultText);
        pointsText = (TextView) findViewById(R.id.pointsText);
        playAgainButton = (Button) findViewById(R.id.button6);
        gameLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    }

    public void generate() {
        Random rand = new Random();
        int a = rand.nextInt(101);
        int b = rand.nextInt(101);
        sumText.setText(Integer.toString(a) + " + " + Integer.toString(b));
        startButton = (Button) findViewById(R.id.startButton);
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(202);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = rand.nextInt(202);
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button6));

    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultText.setText("Correct!");
        } else {
            resultText.setText("Wrong!");
        }
        numQuestions++;
        pointsText.setText(Integer.toString(score) + " / " + Integer.toString(numQuestions));
        generate();
    }

    public void playAgain(View view) {
        generate();
        score = 0;
        numQuestions = 0;
        timerText.setText("30s");
        pointsText.setText("0/0");
        resultText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished / 1000) +"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                resultText.setText("Your score: " + Integer.toString(score) + " / " + Integer.toString(numQuestions));
            }
        }.start();
    }
}
