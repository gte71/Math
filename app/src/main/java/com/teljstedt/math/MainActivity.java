package com.teljstedt.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String QUIZTYPE = "com.teljstedt.math.quiztype";
    public static final String USERTYPE = "com.teljstedt.math.usertype";
    public static final String WEEKTYPE = "com.teljstedt.math.weektype";
    public static String quiz = "";
    private String user;
    private String week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> quizList = getQuizList();
        Spinner quizSpinner = (Spinner) findViewById(R.id.quizspinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this
                , R.layout.support_simple_spinner_dropdown_item
                , quizList);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        quizSpinner.setAdapter(spinnerAdapter);
        quizSpinner.setOnItemSelectedListener(this);
        if (!quiz.isEmpty()) {
            quizSpinner.setSelection(quizList.indexOf(quiz));
        } else {
            quiz = (String) quizSpinner.getItemAtPosition(0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quiz = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ArrayList<String> getQuizList() {
        ArrayList<String> quizList = new ArrayList<>();
        quizList.add("Addition");
        quizList.add("Subtraktion");
        quizList.add("Multiplikation");
        quizList.add("Blandat");
        return quizList;
    }

    public void goQuiz(View view) {
        Intent intent = new Intent(this, quizActivity.class);
        intent.putExtra(QUIZTYPE, quiz);
        intent.putExtra(USERTYPE,user);
        intent.putExtra(WEEKTYPE,week);
        startActivity(intent);
    }

    public void goSettings(View view) {
        Intent intent = new Intent(this, settingsActivity.class);
        startActivity(intent);
    }
}
