package com.teljstedt.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.teljstedt.math.model.Quiz;

public class quizActivity extends AppCompatActivity {

    private static final String TAG = "quizActivity";

    public static Integer cnt_correct=0;
    public static Integer cnt_wrong=0;
    TextView term1;
    TextView term2;
    TextView operation;
    TextView tvQuizType;
    TextView answer;
    TextView total;
    TextView correct;
    TextView wrong;
    String quizType;


    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initElements();
        clearQuiz();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        quizType = intent.getStringExtra(MainActivity.QUIZTYPE);

        tvQuizType.setText(quizType);

        getQuiz();
    }

    private void initElements() {
        Log.i(TAG, "initElements");
        answer = (TextView) findViewById(R.id.answer);
        term1 = (TextView) findViewById(R.id.term1);
        term2 = (TextView) findViewById(R.id.term2);
        operation = (TextView) findViewById(R.id.operation);
        tvQuizType = findViewById(R.id.quizType);
        total = findViewById(R.id.total);
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
    }

    private void clearQuiz() {
        Log.i(TAG, "clearQuiz");
        answer.setText("");
        term1.setText("");
        term2.setText("");
        operation.setText("");
    }

    private void getQuiz() {
        Log.i(TAG, "getQuiz");
        quiz = new Quiz(quizType);
        term1.setText(quiz.getTerm1().toString());
        term2.setText(quiz.getTerm2().toString());
        operation.setText(quiz.getOper().toString());
    }

    private boolean checkAnswer() {
        Integer ans = new Integer(answer.getText().toString());
        if (quiz.answer(ans)) {
            // correct answer,
            Toast.makeText(this, getString(R.string.answer_correct), Toast.LENGTH_SHORT).show();
            cnt_correct++;
            return true;
        } else {
            // wrong answer,
            Toast.makeText(this, getString(R.string.answer_wrong) + quiz.getFacit().toString(), Toast.LENGTH_SHORT).show();
            cnt_wrong++;
            return false;
        }
    }

    public void nextQuiz(View view) {
        Log.i(TAG, "nextQuiz");
        checkAnswer();
        total.setText("Totalt: " + Quiz.getQuizId().toString());
        correct.setText(", varav rätt: "+ cnt_correct.toString());
        wrong.setText(", och fel: "+cnt_wrong.toString());
        clearQuiz();
        getQuiz();
    }

}
